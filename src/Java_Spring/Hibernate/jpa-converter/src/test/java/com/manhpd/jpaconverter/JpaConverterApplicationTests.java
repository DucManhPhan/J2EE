package com.manhpd.jpaconverter;

import com.manhpd.jpaconverter.domain.business_object.TransactionTopupMM;
import com.manhpd.jpaconverter.persistence.entity.TransactionTopupMMEntity;
import com.manhpd.jpaconverter.shared.db.DataConnection;
import com.manhpd.jpaconverter.shared.utils.DataUtils;
import com.viettel.security.PassTranformer;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;

@SpringBootTest
@PropertySource("classpath:database.properties")
class JpaConverterApplicationTests {

    private final String PROPERTY_PASSWORD = "password";

    private final String PROPERTY_USERNAME = "username";

    @Autowired
    private Environment environment;

    @Autowired
    private DataConnection connection;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JpaTransactionManager transactionManager;

    @Test
    void testPasswordDatabase() {
        String realPassword = this.environment.getProperty(PROPERTY_PASSWORD);
        String username = "root";
        String hashPassword = PassTranformer.encrypt(username);
        System.out.println("Hash password is: " + hashPassword);

        String rPassword = PassTranformer.decrypt(hashPassword);
        System.out.println("Real password is: " + rPassword);
    }

    @Test
    void testUpdateLocalDateTimeConverter() {
        TransactionStatus transactionStatus = DataUtils.createTransStatus(this.transactionManager);

        try {
            String tranId = "OMNI_5c93df4ab7af6";
            LocalDateTime now = LocalDateTime.now();
            String sql = "UPDATE TransactionTopupMMEntity e"
                       + " SET e.createdAt = :createdAt,"
                       + " e.errorCode = :errorCode,"
                       + " e.errorMessage = :errorMessage"
                       + " WHERE e.tranId = :tranId";

            Query query = this.connection.createQuery(sql);
            query.setParameter("createdAt", now.plusDays(10));
            query.setParameter("errorCode", "44");
            query.setParameter("errorMessage", "Timeout");
            query.setParameter("tranId", tranId);
            query.executeUpdate();
            this.transactionManager.commit(transactionStatus);
        } catch (Exception ex) {
            System.out.println(ex);
            this.transactionManager.rollback(transactionStatus);
        }
    }

    @Test
    void findTopupMmWithTranId() {
        String tranId = "OMNI_5c93df4ab7af6";
        String sql = "SELECT e FROM TransactionTopupMMEntity e"
                   + " WHERE e.tranId = :tranId";
        TypedQuery<TransactionTopupMMEntity> query = this.connection.createQuery(sql, TransactionTopupMMEntity.class);
        query.setParameter("tranId", tranId);
        TransactionTopupMMEntity entity = query.getSingleResult();

        TransactionTopupMM domain = this.modelMapper.map(entity, TransactionTopupMM.class);
        System.out.println(domain.toString());
    }

}
