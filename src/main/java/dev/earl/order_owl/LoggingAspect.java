package dev.earl.order_owl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution( * dev.earl.order_owl.service.CustomerService.*(..))", throwing = "exception")
    public void logServiceException(RuntimeException exception){
        LOGGER.error(exception.getMessage(), exception);
    }
}
