package com.learning;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.learning.annotations.LogRecord;

@SpringBootTest
class LearningApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(LearningApplicationTests.class);

    @LogRecord
    @Test
    void contextLoads() {
        System.out.println("hello");
    }

}
