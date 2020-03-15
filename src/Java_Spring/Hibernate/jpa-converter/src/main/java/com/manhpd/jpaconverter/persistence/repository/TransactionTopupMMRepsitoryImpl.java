package com.manhpd.jpaconverter.persistence.repository;


import com.manhpd.jpaconverter.domain.business_object.TransactionTopupMM;
import com.manhpd.jpaconverter.domain.repository.ITransactionTopupMMRepository;
import com.manhpd.jpaconverter.persistence.entity.TransactionTopupMMEntity;
import com.manhpd.jpaconverter.shared.db.DataConnection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionTopupMMRepsitoryImpl implements ITransactionTopupMMRepository {

    @Autowired
    private DataConnection connection;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TransactionTopupMM> findByTimeout() {
        String query = "SELECT e FROM TransactionTopupMMEntity e"
                        + " WHERE e.errorMessage = :errorMessage";
        Query sqlQuery = connection.createQuery(query);
        sqlQuery.setParameter("errorMessage", "Timeout");
        List<TransactionTopupMMEntity> select = sqlQuery.getResultList();
        if (!select.isEmpty()) {
            return this.toDomain(select);
        }

        return Collections.emptyList();
    }

    public void update(TransactionTopupMM domain) {
//        TransactionTopupMMEntity entity = this.toEntity(domain);
//        this.connection.updateEntity(entity);
    }

    public void insert(TransactionTopupMM domain) {
        TransactionTopupMMEntity entity = this.toEntity(domain);
        this.connection.insertEntity(entity);
    }

    private List<TransactionTopupMM> toDomain(List<TransactionTopupMMEntity> entities) {
        return entities.stream()
                       .map(entity -> this.toDomain(entity))
                       .collect(Collectors.toList());
    }

    private TransactionTopupMM toDomain(TransactionTopupMMEntity entity) {
        return this.modelMapper.map(entity, TransactionTopupMM.class);
    }

//    private Function<TransactionTopupMMEntity, TransactionTopupMM> toDomain() {
//        return entity -> {
//            TransactionTopupMM transaction = TransactionTopupMM.builder()
//                    .id(entity.getId())
//                    .tranId(entity.getTranId())
//                    .amount(entity.getAmount())
//                    .calling(entity.getCalling())
//                    .cttId(entity.getCttId())
//                    .channel(entity.getChannel())
//                    .description(entity.getDescription())
//                    .errorCode(entity.getErrorCode())
//                    .errorMessage(entity.getErrorMessage())
//                    .build();
//
//            return transaction;
//        };
//    }

    private TransactionTopupMMEntity toEntity(TransactionTopupMM domain) {
        return this.modelMapper.map(domain, TransactionTopupMMEntity.class);
    }

//    private TransactionTopupMMEntity toEntity(TransactionTopupMM domain) {
//        TransactionTopupMMEntity entity = new TransactionTopupMMEntity();
//        entity.setId(domain.getId());
//        entity.setCttId(domain.getCttId());
//        entity.setTranId(domain.getTranId());
//        entity.setAmount(domain.getAmount());
//        entity.setErrorCode(domain.getErrorCode());
//        entity.setErrorMessage(domain.getErrorMessage());
//        entity.setCalling(domain.getCalling());
//        entity.setContent(domain.getContent());
//        entity.setIsdn(domain.getIsdn());
//        entity.setRetry(domain.getRetry());
//        entity.setStatus(domain.getStatus());
//        entity.setIsRefund(domain.getIsRefund());
//
//        return entity;
//    }
}
