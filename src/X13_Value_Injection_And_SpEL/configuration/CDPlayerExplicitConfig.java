package X13_Value_Injection_And_SpEL.configuration;

import X13_Value_Injection_And_SpEL.soundsystem.impl.WhiteAlbum;
import X13_Value_Injection_And_SpEL.soundsystem.model.CompactDisc;
import X13_Value_Injection_And_SpEL.soundsystem.model.MediaPlayer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
/*
* In this case the classes that need to be injected are not annotated with any specific Annotation (e.g. Component, Service).
* When this Configuration Class is scanned, then a Bean called "whiteAlbumBeatles" and "samsungMediaPlayer" will be
* created.
*
*/
@PropertySource(value = "classpath:X13_Value_Injection_And_SpEL/configuration/beans-property.properties")
public class CDPlayerExplicitConfig {

    /**
    * The bean name is by default the method name
    * */
    @Bean(name = "whiteAlbumBeatles")
    public CompactDisc whiteAlbum() {
        return new WhiteAlbum();
    }

}
