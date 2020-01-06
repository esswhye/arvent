package com.arvent.Aspect;

import com.arvent.Exception.CustomerException.CustomerNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.OnCommittedResponseWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAdvice {

    Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.arvent.Controller..*(..))")
    public void myPointCut() {
    }

    //@Around("execution(* com.arvert.Service..*(..)))")
    @Around("myPointCut()")
    public Object applicationControllerLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof OnCommittedResponseWrapper) {
                array[i] = "";
            }
        }

        log.info("Method Invoked " + className + ": " + methodName + "()" + " arguments :" + mapper.writeValueAsString(array));
        Object object = pjp.proceed();

        log.info(className + ": " + methodName + "()" + " Response :" + mapper.writeValueAsString(object));
        return object;
    }

    @Around("execution(* com.arvent.Service.*..*(..))")
    public Object applicationServiceLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();

        log.info("Method Invoked " + className + ": " + methodName + "()");
        Object object = pjp.proceed();
        log.info(className + ": " + methodName + "()" + " Response :" + mapper.writeValueAsString(object));
        return object;
    }


    //AOP expression for which methods shall be intercepted
    //@Around("execution(* com.arvent.*..*(..))")
    //@Around("execution(* com.arvert.Service..*(..)))")
    @Around("myPointCut()")
    public Object profileAllMethods(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = pjp.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

    //@AfterThrowing(pointcut = "execution(* com.arvent.*..*(..))", throwing = "ex")
    @AfterThrowing(pointcut = "execution(* com.arvent.Controller..*(..))", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
        log.info("LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }
}
