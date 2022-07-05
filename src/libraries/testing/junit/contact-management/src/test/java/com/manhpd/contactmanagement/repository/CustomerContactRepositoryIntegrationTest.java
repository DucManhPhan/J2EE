package com.manhpd.contactmanagement.repository;

import com.manhpd.contactmanagement.entity.CustomerContact;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerContactRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Test
    public void testFindByEmail() {
        // setup data scenario
        CustomerContact aNewContact = new CustomerContact();
        aNewContact.setEmail("fredj@myemail.com");
        this.entityManager.persist(aNewContact);

        // Find an inserted record using repository class
        CustomerContact foundContact = this.customerContactRepository.findByEmail("fredj@myemail.com");

        // Assertion
        Assert.assertThat(foundContact.getEmail(), CoreMatchers.is(CoreMatchers.equalTo("fredj@myemail.com")));
    }

    @Test
    public void testFindSpecificContactByIdBypassReposClass() {
        // find an inserted record
        CustomerContact foundContact = this.entityManager.find(CustomerContact.class, new Long("2"));

        Assert.assertThat(foundContact.getEmail(), CoreMatchers.is(CoreMatchers.equalTo("elaine@myemail.com")));
    }

}
