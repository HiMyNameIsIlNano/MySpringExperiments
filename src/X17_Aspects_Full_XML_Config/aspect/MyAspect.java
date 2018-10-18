package X17_Aspects_Full_XML_Config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {

    private void logMessage(String when) {
        System.out.println("I was executed " + when + " calling the method");
    }

    public void logBefore() {
        logMessage("before");
    }

    public void logAfter() {
        logMessage("after");
    }

    public void logAround(ProceedingJoinPoint joinPoint) {
        try {
            logMessage("before around");
            joinPoint.proceed();
            logMessage("after around");
        } catch (Throwable throwable) {
            logMessage("after exception");
        }
    }

}
