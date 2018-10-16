package X13_Value_Injection_And_SpEL.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
/*
* In this case it is necessary to specify where the Spring Beans reside, as the Configuration Class is not
* in the same package as the Beans. Conversely, if the Configuration is in the same package, it will not be
* necessary to specify they will automatically be picked up within the same package.
**/
@PropertySource(value = "classpath:X13_Value_Injection_And_SpEL/configuration/beans-property.properties")
@ComponentScan("X13_Value_Injection_And_SpEL.soundsystem")
public class CDPlayerConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
