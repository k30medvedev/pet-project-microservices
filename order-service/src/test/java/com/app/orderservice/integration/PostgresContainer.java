package com.app.orderservice.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostgresContainer {

    public static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:13.0")
                    .withPassword("example")
                    .withUsername("user")
                    .withDatabaseName("test")
                    .withInitScript("db/changelog/testData.sql");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        postgreSQLContainer.start();
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }


    public void startSqlContainer() {
        postgreSQLContainer.start();
        boolean postgreSQLContainerRunning = postgreSQLContainer.isRunning();
        System.out.println(postgreSQLContainerRunning);
    }

    public void stopContainer() {
        postgreSQLContainer.stop();
    }
}
