package X06_Profiles_Java_Config.config;

import X06_Profiles_Java_Config.soundsystem.impl.Rectangle;
import X06_Profiles_Java_Config.soundsystem.model.Shape;
import X06_Profiles_Java_Config.soundsystem.impl.Square;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
// Eventually the @Profile("XXX") can also be configured at class level
public class DevelopmentProfileConfig {

    @Bean
    @Profile("Square")
    public Shape squareShape() {
        return new Square();
    }

    @Bean
    @Profile("Rectangle")
    public Shape rectangleShape() {
        return new Rectangle();
    }
}
