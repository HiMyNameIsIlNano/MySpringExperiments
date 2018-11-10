package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class InMemoryCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String storeType = conditionContext.getEnvironment().getProperty("store.type");
        return storeType == null || "IN_MEMORY".equals(storeType);
    }

}
