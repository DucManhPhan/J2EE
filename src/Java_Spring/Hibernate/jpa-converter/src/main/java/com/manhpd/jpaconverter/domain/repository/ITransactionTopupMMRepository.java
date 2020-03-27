package com.manhpd.jpaconverter.domain.repository;


import com.manhpd.jpaconverter.domain.business_object.TransactionTopupMM;

import java.util.List;

public interface ITransactionTopupMMRepository {

    List<TransactionTopupMM> findByTimeout();

    void update(TransactionTopupMM domain);

    void insert(TransactionTopupMM domain);

}
