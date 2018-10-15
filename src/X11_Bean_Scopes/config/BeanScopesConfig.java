package X11_Bean_Scopes.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "X11_Bean_Scopes.impl")
public class BeanScopesConfig {
}
