package com.infosys.wecare.coach.utility;

import javassist.NotFoundException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.infosys.wecare.coach.exceptions.WeCareException;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @Before ("execution(* *..*.*CoachRestController.*(..))")
    public void startLog(JoinPoint joinPoint){
        LOGGER.info("Controller start: " + joinPoint.getSignature());
    }


    @AfterThrowing(pointcut = "execution( * *..*.*CoachRestController.*(..))",  throwing = "exception")
    public void afterThrowing(WeCareException exception) throws Exception {
        LOGGER.error(exception.getMessage(), exception);
    }

}
