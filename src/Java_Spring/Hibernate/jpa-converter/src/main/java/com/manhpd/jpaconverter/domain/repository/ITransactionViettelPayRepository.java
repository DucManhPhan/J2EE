package com.manhpd.jpaconverter.domain.repository;


import com.manhpd.jpaconverter.domain.business_object.TransactionViettelpay;

import java.util.List;
import java.util.Optional;

public interface ITransactionViettelPayRepository {

    List<TransactionViettelpay> findByTimeout(int retryTimeMs, int timeoutThresholdMs);

    Optional<TransactionViettelpay> findByTransactionId(String transId);

    List<TransactionViettelpay> findByRefundTransaction();

    void updateVtpayTransaction(TransactionViettelpay vtpay);

}
