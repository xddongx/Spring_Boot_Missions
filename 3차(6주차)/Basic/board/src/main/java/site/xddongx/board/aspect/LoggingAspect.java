package site.xddongx.board.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before(value = "@annotation(LogArguments)")
    public void logArguments(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.info("method name: [{}]", methodSignature.getName());
        logger.info("declaring class: [{}]", methodSignature.getDeclaringType());

        Object[] arguments = joinPoint.getArgs();

        if (arguments.length == 0) {
            logger.info("no arguments");
        }

        for (Object argument: arguments){
            logger.info("argument: [{}]", argument);
        }
    }

    @AfterReturning(value = "@annotation(LogReturn)", returning = "returnValue")
    public void logResults(JoinPoint joinPoint, Object returnValue) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        logger.trace("method: [{}]", methodSignature.getName());
        logger.trace("return type: [{}]", methodSignature.getReturnType());

        logger.trace("return value [{}]", returnValue);
    }
}
