package ru.komarov.sd.mvc;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class StatisticsAspect {
    private final Map<String, Integer> methodAmount = new HashMap<>();
    private final Map<String, Long> totalTime = new HashMap<>();

    //@Around("execution(* ru.komarov.sd.mvc.dao.TaskDao.*(..))")
    @Around("execution(* ru.komarov.sd.mvc.controller.TaskController.*(..))")
    private Object aroundFindAccountsAdvice(ProceedingJoinPoint processingJoinPoint) throws Throwable {
        final long time = System.currentTimeMillis();
        Object result = processingJoinPoint.proceed(processingJoinPoint.getArgs());
        final long executionTime = System.currentTimeMillis() - time;
        String signature = processingJoinPoint.getSignature().toString();

        totalTime.put(signature, totalTime.getOrDefault(signature, (long) 0) + executionTime);
        methodAmount.put(signature, methodAmount.getOrDefault(signature, 0) + 1);
        printStatistic(signature);

        return result;
    }

    private void printStatistic(String signature) {
        Long totalTime = this.totalTime.get(signature);
        Integer amount = methodAmount.get(signature);

        System.out.println("--");
        System.out.println("method signature: " + signature);
        System.out.println("   amount: " + amount);
        System.out.println("   total execution time: " + totalTime + " ms");
        System.out.println("   average execution time: " + totalTime / amount + " ms");
        System.out.println("--");
        System.out.println();
    }
}
