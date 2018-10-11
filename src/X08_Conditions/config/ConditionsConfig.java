package X08_Conditions.config;

import X08_Conditions.impl.Ferrari;
import X08_Conditions.impl.Fiat;
import X08_Conditions.impl.FerrariCondition;
import X08_Conditions.impl.FiatCondition;
import X08_Conditions.model.Auto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionsConfig {

    @Bean
    @Conditional(FerrariCondition.class)
    public Auto ferrari() {
        return new Ferrari();
    }

    @Bean
    @Conditional(FiatCondition.class)
    public Auto fiat() {
        return new Fiat();
    }

}
