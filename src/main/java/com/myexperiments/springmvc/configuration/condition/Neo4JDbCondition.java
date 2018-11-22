package com.myexperiments.springmvc.configuration.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Neo4JDbCondition implements ConfigurationCondition {

    private static final String DB_TYPE = "db.type";

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return "NEO4J".equals(conditionContext.getEnvironment().getProperty(DB_TYPE));
    }
    
}
