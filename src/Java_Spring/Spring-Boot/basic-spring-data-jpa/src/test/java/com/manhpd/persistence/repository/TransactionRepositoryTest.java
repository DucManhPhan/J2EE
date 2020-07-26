package com.manhpd.persistence.repository;

import com.manhpd.domain.domain_object.Transaction;
import com.manhpd.domain.repository.ITransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class TransactionRepositoryTest {

    @Autowired
    private ITransactionRepository repo;

    @Test
    void findByTransactionId() {
        String transId = "1";
        Optional<Transaction> optTransaction = this.repo.findByTransactionId(transId);
        System.out.println(optTransaction.get().toString());
    }

    @Test
    void findByCardNumber() {
    }
}