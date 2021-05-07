package com.github.wengertj;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.commonspool2.CommonsObjectPool2Metrics;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.Duration;
import java.util.concurrent.locks.LockSupport;

@SpringJUnitConfig(AppTest.ContextConfiguration.class)
class AppTest {

    @Autowired
    private DataSource ds;

    @Test
    void testAConnection() {

        try (var conn = ds.getConnection();
             var statement = conn.createStatement()) {

            statement.execute("SELECT 1");
            LockSupport.parkNanos(Duration.ofSeconds(10L).toNanos());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        MeterRegistry meterRegistry() {
            return Metrics.globalRegistry;
        }

        @Bean
        DataSource dataSource() {
            var ds = new BasicDataSource();
            ds.setUsername("sa");
            ds.setPassword("");
            ds.setUrl("jdbc:h2:mem:testdb");
            ds.setJmxName("org.apache.commons.pool2:name=dbcp,type=GenericObjectPool");

            return ds;
        }

        @Bean
        CommonsObjectPool2Metrics commonsObjectPool2Metrics(MeterRegistry meterRegistry) {
            var commonsObjectPool2Metrics = new CommonsObjectPool2Metrics();
            commonsObjectPool2Metrics.bindTo(meterRegistry);
            return commonsObjectPool2Metrics;
        }
    }
}
