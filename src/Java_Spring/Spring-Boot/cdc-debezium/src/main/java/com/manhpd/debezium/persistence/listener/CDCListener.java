package com.manhpd.debezium.persistence.listener;

import com.manhpd.debezium.domain.service.impl.EmployeeRedisService;
import com.manhpd.debezium.utils.Operation;
import io.debezium.config.Configuration;
import io.debezium.embedded.EmbeddedEngine;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.*;
import static java.util.stream.Collectors.toMap;

@Component
public class CDCListener {

    /**
     * Single thread pool which will run the Debezium engine asynchronously.
     */
    private final Executor executor = Executors.newSingleThreadExecutor();

    /**
     * The Debezium engine which needs to be loaded with the configurations, Started and Stopped - for the
     * CDC to work.
     */
    private final EmbeddedEngine engine;

    /**
     * Handle to the Service layer, which interacts with ElasticSearch.
     */
    private final EmployeeRedisService employeeRedisService;

    /**
     * Constructor which loads the configurations and sets a callback method 'handleEvent', which is invoked when
     * a DataBase transactional operation is performed.
     *
     * @param employeeConnector
     * @param employeeRedisService
     */
    @Autowired
    public CDCListener(Configuration employeeConnector, EmployeeRedisService employeeRedisService) {
        this.engine = EmbeddedEngine
                .create()
                .using(employeeConnector)
                .notifying(this::handleEvent)
                .build();

        this.employeeRedisService = employeeRedisService;
        System.out.println("Initialize CDC Listener with engine and service");
    }

    /**
     * The method is called after the Debezium engine is initialized and started asynchronously using the Executor.
     */
    @PostConstruct
    private void start() {
        System.out.println("Start engine of Debezium");
        this.executor.execute(engine);
    }

    /**
     * This method is called when the container is being destroyed. This stops the debezium, merging the Executor.
     */
    @PreDestroy
    private void stop() {
        if (this.engine != null) {
            this.engine.stop();
        }
    }

    /**
     * This method is invoked when a transactional action is performed on any of the tables that were configured.
     *
     * @param sourceRecord
     */
    private void handleEvent(SourceRecord sourceRecord) {
        try {
            Struct sourceRecordValue = (Struct) sourceRecord.value();

            if (sourceRecordValue != null) {
                Operation operation = Operation.forCode((String) sourceRecordValue.get(OPERATION));

                //Only if this is a transactional operation.
                if (operation != Operation.READ) {

                    Map<String, Object> message;
                    String record = AFTER; //For Update & Insert operations.

                    if (operation == Operation.DELETE) {
                        record = BEFORE; //For Delete operations.
                    }

                    //Build a map with all row data received.
                    Struct struct = (Struct) sourceRecordValue.get(record);
                    message = struct.schema().fields().stream()
                            .map(Field::name)
                            .filter(fieldName -> struct.get(fieldName) != null)
                            .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                            .collect(toMap(Pair::getKey, Pair::getValue));

                    //Call the service to handle the data change.
                    this.employeeRedisService.interactWithRedisBasedOn(message, operation);
                    System.out.println(String.format("Data Changed: %s with Operation: %s", message, operation.name()));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}