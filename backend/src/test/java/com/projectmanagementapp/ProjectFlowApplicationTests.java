package com.projectmanagementapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.sql.init.mode=never",
        "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres",
        "spring.datasource.username=test",
        "spring.datasource.password=test"
})
class ProjectFlowApplicationTests {

    @Test
    void contextLoads() {
    }
}

