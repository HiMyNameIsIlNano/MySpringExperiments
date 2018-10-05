package X04_Mixed_Configuration_Global_Java.config;

import X04_Mixed_Configuration_Global_Java.soundsystem.impl.WhiteAlbum;
import X04_Mixed_Configuration_Global_Java.soundsystem.model.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
* In this case the classes that need to be injected are not annotated with any specific Annotation (e.g. Component, Service).
* When this Configuration Class is scanned, then a Bean called "whiteAlbumBeatles" and "samsungMediaPlayer" will be
* created.
*
*/
public class CDPlayerConfig {

    /**
    * The bean name is by default the method name
    * */
    @Bean(name = "whiteAlbumBeatles")
    public CompactDisc whiteAlbum() {
        return new WhiteAlbum("White Album","The Beatles");
    }

}
