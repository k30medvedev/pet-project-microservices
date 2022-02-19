package com.app.orderservice;

import com.app.orderservice.integration.PostgresContainer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest extends PostgresContainer {


    @Test
    void contextLoads() {
    }

}
