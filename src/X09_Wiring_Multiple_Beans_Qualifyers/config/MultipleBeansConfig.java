package X09_Wiring_Multiple_Beans_Qualifyers.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "X09_Wiring_Multiple_Beans_Qualifyers.dessert")
public class MultipleBeansConfig {
}
