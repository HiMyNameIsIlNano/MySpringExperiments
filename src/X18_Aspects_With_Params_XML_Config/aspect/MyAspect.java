package X18_Aspects_With_Params_XML_Config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class MyAspect {

    private Map<MessageTypeEnum, Integer> map = new HashMap<>();

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
