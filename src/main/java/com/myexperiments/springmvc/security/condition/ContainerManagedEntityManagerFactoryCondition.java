package com.myexperiments.springmvc.security.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ContainerManagedEntityManagerFactoryCondition implements ConfigurationCondition {

    private static final String PROPERTY_NAME = "entity.manager.factory.bean.management.type.condition";

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return "CONTAINER_MANAGED".equals(conditionContext.getEnvironment().getProperty(PROPERTY_NAME));
    }
}
