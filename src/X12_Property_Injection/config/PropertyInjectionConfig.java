package X12_Property_Injection.config;

import X12_Property_Injection.impl.Ferrari;
import X12_Property_Injection.impl.Fiat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:X12_Property_Injection/config/beans-property.properties")
public class PropertyInjectionConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public Ferrari ferrari() {
        return new Ferrari(environment.getProperty("ferrari.bean.type", "Ferrari Not Found"));
    }

    @Bean
    public Fiat fiat() {
        return new Fiat(environment.getProperty("fiat.bean.type", "Fiat Not Found"));
    }

}
