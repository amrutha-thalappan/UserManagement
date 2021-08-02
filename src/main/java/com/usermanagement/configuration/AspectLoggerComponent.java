package com.usermanagement.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * AOP configuration to log inputs and outputs of each call as well as the processing time
 */
@Aspect
@Component
public class AspectLoggerComponent {

    private static final String POINTCUT = "execution(* com.usermanagement.api..*Controller.*(..))";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This method will execute around each function execution of the controller
     * which has specified in the static variable POINTCUT
     * and logs input parameters, execution time and output of each method of controller
     * @param proceedingJoinPoint method invocation supports around advice in AJ aspects
     * @return result or output of the method execution
     * @throws Throwable raise unexpected errors
     */
    @Around(POINTCUT)
    public Object getMethodProfile(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        //Log input parameters of method
        Object[] args = proceedingJoinPoint.getArgs();
        logger.info("Input to the method: " + methodName);
        for(int index = 0; index < args.length; index++){
            logger.info("parameter " + (index+1) + " - " + args[index]);
        }

        final StopWatch stopWatch = new StopWatch();
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        logger.info("Execution time of " + className + "." + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ms");
        //Log output from the method
        logger.info("Output from the method: "+methodName+ "::" +result.toString());

        return result;
    }
}
