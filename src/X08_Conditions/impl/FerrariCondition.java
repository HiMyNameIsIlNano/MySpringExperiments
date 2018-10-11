package X08_Conditions.impl;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class FerrariCondition implements Condition{

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String autoType = conditionContext.getEnvironment().getProperty("auto.type");
        return "Ferrari".equalsIgnoreCase(autoType);
    }
}
