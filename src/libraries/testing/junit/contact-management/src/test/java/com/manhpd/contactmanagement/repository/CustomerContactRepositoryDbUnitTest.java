package com.manhpd.contactmanagement.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.manhpd.contactmanagement.entity.CustomerContact;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextBeforeModesTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
@DatabaseSetup("classpath:test-datasets.xml")
public class CustomerContactRepositoryDbUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Test
    public void testFindByEmail() {
        // Find an inserted record
        CustomerContact foundContact = this.customerContactRepository.findByEmail("elaine@myemail.com");

        // Assertion
        Assert.assertThat(foundContact.getEmail(), CoreMatchers.is(CoreMatchers.equalTo("elaine@myemail.com")));
    }

    @Test
    public void testFindSpecificContactByIdBypassReposClass() {
        // Find an inserted record
        CustomerContact foundContact = entityManager.find(CustomerContact.class, new Long("2"));

        Assert.assertThat(foundContact.getEmail(), CoreMatchers.is(CoreMatchers.equalTo("elaine@myemail.com")));
    }

}

