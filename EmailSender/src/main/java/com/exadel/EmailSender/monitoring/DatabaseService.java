package com.exadel.EmailSender.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseService implements HealthIndicator {
    private final DataSource dataSource;

    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Health health() {
        String databaseService = "DatabaseService";
        if (isDatabaseHealthGood()){
            return Health.up().withDetail(databaseService, ": service is running").build();
        }
        return Health.up().withDetail(databaseService, ": service is not running").build();
    }

    private boolean isDatabaseHealthGood(){
        return dataSource != null;
    }

}
