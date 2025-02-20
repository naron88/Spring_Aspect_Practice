package com.practice.aspectpractice.service;

import com.practice.aspectpractice.annotation.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @LogExecutionTime
    public String doSomething() throws InterruptedException { // 스레드가 중단될 가능성이 있는 경우
        Thread.sleep(500); // 0.5초 동안 실행되는 작업
        return "작업 완료!";
    }
}
