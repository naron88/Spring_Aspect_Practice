# AOP 실행 시간 측정 및 로깅

Spring Boot와 AOP(Aspect-Oriented Programming)를 사용하여 **메서드 실행 시간을 측정하고 로깅하는 기능**을 구현했습니다.

## 구현 개요

**Spring AOP**를 활용하여 특정 어노테이션이 적용된 메서드의 실행 시간을 측정하고, 실행 전후에 로그를 출력합니다.

- `@LogExecutionTime` 어노테이션이 붙은 메서드의 실행 시간을 측정 (`@Around` 사용)
- 메서드 실행 전/후 로깅 (`@Before`, `@After` 사용)
- JUnit 테스트를 통해 동작 검증

---
## 프로젝트 구조
```bash
src/main/java/com/practice/aspectpractice
│── annotation
│   ├── LogExecutionTime.java   # 실행 시간 측정을 위한 어노테이션
│── aspect
│   ├── ExecutionTimeAspect.java # 실행 시간 측정 AOP
│   ├── LoggingAspect.java       # 실행 전후 로깅 AOP
│── service
│   ├── SampleService.java       # 테스트용 서비스 클래스
---
## 주요 기능 설명

### 1. 실행 시간 측정 (AOP 적용)
**`ExecutionTimeAspect.java`에서 `@Around`를 사용하여 특정 어노테이션이 붙은 메서드의 실행 시간을 측정**
```java
@Aspect
@Component
public class ExecutionTimeAspect {

    // 특정 어노테이션이 있는 메서드에서만 호출
    @Around("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")  // 패키지 경로 추가
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable { // 모든 종류의 예외 처리
        long startTime = System.currentTimeMillis();

        // 원래 메서드 실행하고 값 반환
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        System.out.println(joinPoint.getSignature().getName() + " 메서드 실행 시간: " + (endTime - startTime) + "ms");

        return result;
    }
}
```
### 2. 실행 전후 로깅 (`@Before`, `@After`)
**메서드 실행 전후에 로깅을 추가 (`LoggingAspect.java`)**
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")
    public void beforeMethodExecution() {
        System.out.println("메서드 실행 전 수행");
    }

    @After("@annotation(com.practice.aspectpractice.annotation.LogExecutionTime)")
    public void afterMethodExecution() {
        System.out.println("메서드 실행 후 수행");
    }
}
```
### 3. 테스트 코드 (`SampleServiceTest.java`)
**JUnit을 사용하여 실행 시간 측정이 정상적으로 동작하는지 검증**
```java
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
```
## 실행 결과 (스크린샷)
![AspectResult](https://github.com/user-attachments/assets/b8a2ec31-141b-4932-b2f2-d9c1dc52c79b)
---

## 결론
- **Spring AOP를 활용하여 메서드 실행 시간을 측정하고 로그를 남길 수 있음**
- **특정 어노테이션(`@LogExecutionTime`)을 활용하여 필요한 메서드에만 적용 가능**
- **JUnit 테스트를 통해 AOP가 정상 동작하는지 검증 가능**
