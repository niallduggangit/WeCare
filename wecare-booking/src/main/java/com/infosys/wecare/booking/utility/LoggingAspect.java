package com.infosys.wecare.booking.utility;


import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.infosys.wecare.booking.exceptions.WeCareException;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAspect {

    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @Before ("execution(* *..*.*CoachRestController.*(..))")
    public void startLog(JoinPoint joinPoint){
        LOGGER.info("Controller start: " + joinPoint.getSourceLocation());
    }


    @AfterThrowing(pointcut = "execution( * *..*.*CoachRestController.*(..))",  throwing = "exception")
    public void afterThrowing(WeCareException exception) throws WeCareException {
        LOGGER.error(exception.getMessage(), exception);
    }

}
