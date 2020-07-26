package com.manhpd.persistence.repository;

import com.manhpd.domain.domain_object.Transaction;
import com.manhpd.domain.repository.ITransactionRepository;
import com.manhpd.persistence.entity.TransactionEntity;
import com.manhpd.persistence.mapper.TransactionMapper;
import com.manhpd.shared.db.DataConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TransactionRepository implements ITransactionRepository {

    @Value("${hibernate.timeout.sec}")
    private String timeoutSec;

    @Autowired
    private DataConnection connection;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public Optional<Transaction> findByTransactionId(String transactionId) {
        Objects.requireNonNull(transactionId);
        String sqlQuery = "SELECT entity FROM TransactionEntity entity WHERE entity.transactionId = :transactionId";
        Query hQuery = this.connection.createQuery(sqlQuery);
        hQuery.setParameter("transactionId", transactionId);

        try {
            List<TransactionEntity> entities = hQuery.getResultList();
            return Optional.ofNullable(this.transactionMapper.toTransaction(entities.get(0)));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return Optional.empty();
    }

    @Override
    public List<Transaction> findByCardNumber(String cardNumber) {
        return null;
    }
}
