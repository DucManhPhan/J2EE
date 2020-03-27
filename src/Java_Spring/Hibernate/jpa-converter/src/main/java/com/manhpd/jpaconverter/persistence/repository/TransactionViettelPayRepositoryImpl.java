package com.manhpd.jpaconverter.persistence.repository;

import com.manhpd.jpaconverter.domain.business_object.TransactionViettelpay;
import com.manhpd.jpaconverter.domain.repository.ITransactionViettelPayRepository;
import com.manhpd.jpaconverter.persistence.entity.TransactionViettelpayEntity;
import com.manhpd.jpaconverter.shared.db.DataConnection;
import com.manhpd.jpaconverter.shared.utils.DataUtils;
import com.viettel.mobilemoney.shared.utils.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TransactionViettelPayRepositoryImpl implements ITransactionViettelPayRepository {

    private static final Logger logger = LogManager.getLogger(TransactionViettelPayRepositoryImpl.class);

    @Autowired
    private DataConnection connection;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public List<TransactionViettelpay> findByTimeout(int retryTimeMs, int timeoutThresholdMs) {
        long startTime = System.currentTimeMillis();
        List<TransactionViettelpay> domains = new ArrayList<>();

        try {
            String query = "SELECT e FROM TransactionViettelpayEntity e"
                    + " WHERE e.status IS NULL"
                    + " AND e.createdAt >= (NOW() - INTERVAL :retryTimeMs MINUTE)"
                    + " AND e.createdAt <  (NOW() - INTERVAL :timeoutThresholdMs MINUTE)";
            Query sqlQuery = this.connection.createQuery(query);
            sqlQuery.setParameter("retryTimeMs", retryTimeMs);
            sqlQuery.setParameter("timeoutThresholdMs", timeoutThresholdMs);
            List<TransactionViettelpayEntity> entities = sqlQuery.getResultList();
            if (!entities.isEmpty()) {
                domains = this.toDomain(entities);
            }
        } catch (Exception ex) {
            logger.error("Error in findByTimeout() method: ", ex);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Time process: " + duration + " ms");

            return domains;
        }
    }

    @Override
    public Optional<TransactionViettelpay> findByTransactionId(String transId) {
        long startTime = System.currentTimeMillis();
        TransactionViettelpay transactionViettelpay = null;

        try {
            String queryById = "SELECT e FROM TransactionViettelpayEntity e" + " WHERE e.tranId = :transId";
            Query sqlQuery = this.connection.getEntityManager().createQuery(queryById);
            sqlQuery.setParameter("transId", transId);
            List<TransactionViettelpayEntity> select = sqlQuery.getResultList();
            if (!select.isEmpty()) {
                transactionViettelpay = this.toDomain(select.get(0));
            }
        } catch (Exception ex) {
            logger.error("Find transactions based on id have error: ", ex);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Time process: " + duration + " ms");

            return Optional.ofNullable(transactionViettelpay);
        }
    }

    @Override
    public List<TransactionViettelpay> findByRefundTransaction() {
        long startTime = System.currentTimeMillis();
        List<TransactionViettelpay> domains = new ArrayList<>();

        try {
            String refundErrorCode = "01";
            String query = "SELECT e FROM TransactionViettelpayEntity e"
                    + " WHERE e.refundErrorCode = :refundErrorCode";
            Query sqlQuery = this.connection.createQuery(query);
            sqlQuery.setParameter(Constant.REFUND_ERROR_CODE_FIELD, refundErrorCode);
            List<TransactionViettelpayEntity> entities = sqlQuery.getResultList();
            if (!entities.isEmpty()) {
                domains = this.toDomain(entities);
            }
        } catch (Exception ex) {
            logger.error("Exception when finding the refund transaction: ", ex);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Time process: " + duration + " ms");

            return domains;
        }
    }

    @Override
    public void updateVtpayTransaction(TransactionViettelpay vtpay) {
        long startTime = System.currentTimeMillis();
        TransactionStatus transactionStatus = DataUtils.createTransStatus(this.transactionManager);

        try {
            String sql = "UPDATE TransactionViettelpayEntity e"
                       + " SET e.status = :status, e.description = :description,"
                       + " e.refundErrorCode = :refundErrorCode, e.updateAt = NOW(),"
                       + " e.retry = :retry"
                       + " WHERE e.id = :id";
            Query sqlQuery = this.connection.createQuery(sql);
            sqlQuery.setParameter("status", vtpay.getStatus());
            sqlQuery.setParameter("description", vtpay.getDescription());
            sqlQuery.setParameter("refundErrorCode", vtpay.getRefundErrorCode());
            sqlQuery.setParameter("retry", vtpay.getRetry());
            sqlQuery.setParameter("id", vtpay.getId());

            sqlQuery.executeUpdate();
            this.transactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            logger.error("Update transactions based on id have error: ", ex);
            this.transactionManager.rollback(transactionStatus);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            logger.info("Time process: " + duration + " ms");
        }
    }

    private List<TransactionViettelpay> toDomain(List<TransactionViettelpayEntity> entities) {
        return entities.stream()
                .map(entity -> this.toDomain(entity))
                .collect(Collectors.toList());
    }

    private TransactionViettelpay toDomain(TransactionViettelpayEntity entity) {
        return this.modelMapper.map(entity, TransactionViettelpay.class);
    }

    private TransactionViettelpayEntity toEntity(TransactionViettelpay domain) {
        return this.modelMapper.map(domain, TransactionViettelpayEntity.class);
    }

//    private Function<TransactionViettelpayEntity, TransactionViettelpay> toDomain() {
//        return entity -> {
//            TransactionViettelpay transaction = new TransactionViettelpay();
//
//            transaction.setCalling(entity.getCalling());
//            transaction.setIsdn(entity.getIsdn());
//            transaction.setTranId(entity.getTranId());
//            transaction.setTransAmount(entity.getAmount());
//            transaction.setDescription(entity.getDescription());
//            transaction.setServicePay(entity.getServicePay());
//            transaction.setCttPackage(entity.getCttPackage());
//            transaction.setCommandCode(entity.getCommandCode());
//            transaction.setContent(entity.getContent());
//            transaction.setOmniOrderCode(entity.getOmniOrderCode());
//            transaction.setOmniErrorCode(entity.getOmniErrorCode());
//            transaction.setOmniOrderMessage(entity.getOmniOrderMessage());
//            transaction.setCttId(entity.getCttId());
//            transaction.setStatus(entity.getStatus());
//            transaction.setSource(entity.getSource());
//            transaction.setOrderType(entity.getOrderType());
//            transaction.setCttPackageName(entity.getCttPackageName());
//            transaction.setSimNumber(entity.getSimNumber());
//            transaction.setCreatedAt(entity.getCreatedAt());
//            transaction.setUpdatedAt(entity.getUpdatedAt());
//            transaction.setRefundErrorCode(entity.getRefundErrorCode());
//            transaction.setServiceIndicator(entity.getServiceIndicator());
//
//
//            transaction.setRetry(entity.getRetry());
//
//            return transaction;
//        };
//    }
}
