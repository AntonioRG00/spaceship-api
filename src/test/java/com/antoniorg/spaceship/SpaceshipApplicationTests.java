package com.antoniorg.spaceship;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class SpaceshipApplicationTests {

    private @Autowired ApplicationContext context;

    @Test
    void contextLoads() {
        assertNotNull(context, "The application context should have loaded.");
    }

    @Test
    void testRequiredBeansExist() {
        assertNotNull(context.getBean("spaceshipRestController"), "SpaceshipRestController bean should be present in the context.");
        assertNotNull(context.getBean("spaceshipServiceImpl"), "SpaceshipServiceImpl bean should be present in the context.");
        assertNotNull(context.getBean("jwtRequestFilter"), "JwtRequestFilter bean should be present in the context.");
    }
}