package com.manhpd.contactmanagement;

import com.manhpd.contactmanagement.controller.ContactsManagementControllerIntegrationTest;
import com.manhpd.contactmanagement.repository.CustomerContactRepositoryIntegrationTest;
import com.manhpd.contactmanagement.service.ContactsManagementServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContactsManagementServiceIntegrationTest.class,
        ContactsManagementControllerIntegrationTest.class,
        CustomerContactRepositoryIntegrationTest.class
})
public class AddContactFeatureTestSuite {
    // intentionally empty - Test Suite setup (annotations) is sufficient
}

