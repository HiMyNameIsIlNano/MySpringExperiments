package X15_Aspects_XML_Config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
/*
@Component: this annotation needs to be left here if the Configuration is not making use of the @ComponentScan
**/
public class MyAspect {

    @Pointcut("execution(* X15_Aspects_XML_Config.service.DummyService.aMethodIWantToTest(..))")
    public void testPointcut() {}

    private void logMessage(String when) {
        System.out.println("I was executed " + when + " calling the method");
    }

    @Before("testPointcut()")
    public void logBefore() {
        logMessage("before");
    }

    @After("testPointcut()")
    public void logAfter() {
        logMessage("after");
    }

    @Around("testPointcut()")
    public void logAround(ProceedingJoinPoint proceedingJoinPoint) {
        try {
            logMessage("before around");
            /*
            * It’s crucial to include a call to the proceed() method.
            * Without that, the advice will effectively block access to the advised method.
            **/
            proceedingJoinPoint.proceed();
            logMessage("after around");
        } catch (Throwable throwable) {
            logMessage("after exception");
        }
    }

}
