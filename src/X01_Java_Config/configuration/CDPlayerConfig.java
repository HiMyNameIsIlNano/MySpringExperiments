package X01_Java_Config.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
* In this case it is necessary to specify where the Spring Beans reside, as the Configuration Class is not
* in the same package as the Beans. Conversely, if the Configuration is in the same package, it will not be
* necessary to specify they will automatically be picked up within the same package.
**/
@ComponentScan("X01_Java_Config.soundsystem")
public class CDPlayerConfig {
}
