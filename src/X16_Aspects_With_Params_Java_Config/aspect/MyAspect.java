package X16_Aspects_With_Params_Java_Config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
/*
@Component: this annotation needs to be left here if the Configuration is not making use of the @ComponentScan
**/
public class MyAspect {

    private Map<MessageTypeEnum, Integer> map = new HashMap<>();

    @Pointcut("execution(* X16_Aspects_With_Params_Java_Config.service.DummyService.aMethodIWantToTest(MessageTypeEnum)) " +
            "&& args(messageType)")
    public void testPointcut(MessageTypeEnum messageType) {}

    @Before("testPointcut(messageType)")
    public void logBefore(MessageTypeEnum messageType) {
        int counter = 1;

        if (map.containsKey(messageType)) {
            counter = map.get(messageType) + 1;
        }

        map.put(messageType, counter);
    }

    public int getMessageCount(MessageTypeEnum messageTypeEnum) {
        return map.get(messageTypeEnum);
    }

}
