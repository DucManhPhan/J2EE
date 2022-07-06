package com.manhpd.contactmanagement;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DatastoreSystemsHealthTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void dbPrimaryIsOk() {
        try {
            DatabaseMetaData metaData = this.dataSource.getConnection().getMetaData();
            String catalogName = this.dataSource.getConnection().getCatalog();

            Assert.assertThat(metaData, CoreMatchers.is(CoreMatchers.notNullValue()));
            Assert.assertThat(catalogName, CoreMatchers.is(CoreMatchers.equalTo("contact-management".toUpperCase())));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
