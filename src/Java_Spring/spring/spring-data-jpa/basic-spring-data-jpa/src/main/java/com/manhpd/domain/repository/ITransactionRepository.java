package com.manhpd.domain.repository;

import com.manhpd.domain.domain_object.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository {

    Optional<Transaction> findByTransactionId(String transactionId);

    List<Transaction> findByCardNumber(String cardNumber);

}
