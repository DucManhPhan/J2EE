package com.manhpd.saxXml;

import com.manhpd.dto.LstParameter;
import com.manhpd.utils.Constant;
import org.apache.commons.math3.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class SaxXmlHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger(SaxXmlHandler.class);

    private BlockingQueue<LstParameter> data;

    private LstParameter lstParameter;

    private Map<String, Integer> lstParameterFieldIndexes = new HashMap<>() {{
        put(Constant.LST_PARAMETER_NODE, 0);
        put(Constant.PARAM_ID_FIELD, 1);
        put(Constant.SYSTEM_ID_FIELD, 2);
        put(Constant.APP_CODE_FIELD, 3);
        put(Constant.PARAM_NAME_FIELD, 4);
        put(Constant.PARAM_VALUE_FIELD, 5);
        put(Constant.PARAM_MEANING_FIELD, 6);
    }};

    private Map<Integer, Pair<Predicate<String>, Consumer<String>>> condActions = new HashMap<>() {{
        put(0, new Pair<>(Constant.LST_PARAMETER_NODE::equals,
                          lstParameterNode -> lstParameter = new LstParameter()));
        put(1, new Pair<>(Constant.PARAM_ID_FIELD::equals, paramIdField -> lstParameter.setPARAM_ID(paramIdField)));
        put(2, new Pair<>(Constant.SYSTEM_ID_FIELD::equals, systemIdField -> lstParameter.setSYSTEM_ID(systemIdField)));
        put(3, new Pair<>(Constant.APP_CODE_FIELD::equals, appCodeField -> lstParameter.setAPP_CODE(appCodeField)));
        put(4, new Pair<>(Constant.PARAM_NAME_FIELD::equals, paramNameField -> lstParameter.setPARAM_NAME(paramNameField)));
        put(5, new Pair<>(Constant.PARAM_VALUE_FIELD::equals, paramValueField -> lstParameter.setPARAM_VALUE(paramValueField)));
        put(6, new Pair<>(Constant.PARAM_MEANING_FIELD::equals, paramMeaningField -> lstParameter.setPARAM_MEANING(paramMeaningField)));
    }};

    private boolean[] fieldStates = new boolean[lstParameterFieldIndexes.size()];

    public SaxXmlHandler(BlockingQueue<LstParameter> data) {
        this.data = data;
        this.lstParameter = new LstParameter();
    }

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) {
        Objects.requireNonNull(qName);

        Integer idx = this.lstParameterFieldIndexes.get(qName);
        if (Objects.isNull(idx)) {
            logger.info("This field " + qName + " is not contained in a file");
            return;
        }

        Optional.ofNullable(qName)
                .filter(condActions.get(0).getFirst())
                .ifPresent(condActions.get(0).getSecond());
        fieldStates[idx] = condActions.get(idx).getFirst()
                                      .test(qName);
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) {
        Optional.ofNullable(qName)
                .filter(Constant.LST_PARAMETER_NODE::equals)
                .ifPresent(nameNode -> this.data.offer(lstParameter));
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        IntStream.rangeClosed(0, fieldStates.length - 1)
                 .filter(idx -> fieldStates[idx])
                 .peek(System.out::println)
                 .forEach(idx -> {
                     Pair<Predicate<String>, Consumer<String>> pair = condActions.get(idx);
                     pair.getSecond().accept(new String(ch, start, length));

                     // reset a field that has already processed
                     fieldStates[idx] = false;
                 });
    }

}
