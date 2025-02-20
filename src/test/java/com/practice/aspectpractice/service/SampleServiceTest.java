package com.practice.aspectpractice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Test
    void testExecutionService() throws InterruptedException {
        String result = sampleService.doSomething();
        assertEquals("작업 완료!", result);
    }
}