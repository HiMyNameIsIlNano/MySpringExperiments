package com.myexperiments.springmvc.configuration;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * If one adds @Conditional to a config class then the condition class should implement the ConfigurationCondition.
 * Moreover the @PropertySource annotation should be added to the config class where the condition is used.
 */
public class InMemoryCondition implements ConfigurationCondition {

    // Properties will have been loaded in ConfigurationPhase.REGISTER_BEAN phase
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return "IN_MEMORY".equals(conditionContext.getEnvironment().getProperty("store.type"));
    }
}
