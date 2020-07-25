package com.manhpd.persistence.repository;

import com.manhpd.domain.domain_object.Transaction;
import com.manhpd.domain.repository.ITransactionRepository;
import com.manhpd.persistence.entity.TransactionEntity;
import com.manhpd.shared.db.DataConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TransactionRepository implements ITransactionRepository {

    @Value("${hibernate.timeout.sec}")
    private String timeoutSec;

    @Autowired
    private DataConnection connection;

    @Override
    public Optional<Transaction> findByTransactionId(String transactionId) {
        Objects.requireNonNull(transactionId);

        TransactionEntity entity = this.connection.getEntityFromDatabase(TransactionEntity.class, transactionId);


        return Optional.empty();
    }

    @Override
    public List<Transaction> findByCardNumber(String cardNumber) {
        return null;
    }
}
