package X04_Mixed_Configuration_Global_Java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(CDPlayerConfig.class)
@ImportResource("classpath:X04_Mixed_Configuration_Global_Java/config/beans-definition.xml")
public class SoundSystemConfig {
}
