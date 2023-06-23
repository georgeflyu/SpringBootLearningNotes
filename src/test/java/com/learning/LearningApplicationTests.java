package com.learning;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.annotations.Log;

@SpringBootTest
class LearningApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(LearningApplicationTests.class);

    @Log
    @Test
    void contextLoads() {
        System.out.println("hello");
    }

}
