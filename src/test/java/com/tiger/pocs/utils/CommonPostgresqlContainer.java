package com.tiger.pocs.utils;

import org.testcontainers.containers.PostgreSQLContainer;

public class CommonPostgresqlContainer extends PostgreSQLContainer<CommonPostgresqlContainer> {
    private static final String VERSION = "postgres:13";
    private static CommonPostgresqlContainer container;
    private CommonPostgresqlContainer() {
        super(VERSION);
    }
    public static CommonPostgresqlContainer getInstance() {
        if (container == null) {
            container = new CommonPostgresqlContainer();
        }
        return container;
    }
    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
        System.setProperty("DB_DRIVER_CLASS_NAME", container.getDriverClassName());
    }
    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}