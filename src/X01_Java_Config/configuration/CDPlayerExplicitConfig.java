package X01_Java_Config.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import X01_Java_Config.soundsystem.model.CompactDisc;
import X01_Java_Config.soundsystem.model.MediaPlayer;
import X01_Java_Config.soundsystem.impl.SamsungMediaPlayer;
import X01_Java_Config.soundsystem.impl.WhiteAlbum;

@Configuration
/*
* In this case the classes that need to be injected are not annotated with any specific Annotation (e.g. Component, Service).
* When this Configuration Class is scanned, then a Bean called "whiteAlbumBeatles" and "samsungMediaPlayer" will be
* created.
*
*/
public class CDPlayerExplicitConfig {

    /**
    * The bean name is by default the method name
    * */
    @Bean(name = "whiteAlbumBeatles")
    public CompactDisc whiteAlbum() {
        return new WhiteAlbum();
    }

    /*
    * As the samsungMediaPlayer has a reference to a Bean of type CompactDisc among its fields, then Spring will intercept
    * the call to the samsungMediaPlayer Method, and set the CompactDisc to the WhiteAlbum. If a bean of name whiteAlbum
    * already exists in the ApplicationContext, then the bean is returned (singleton instance). If a bean of that type
    * does not exist, then it will be created, but not more than one time.

    @Bean

    public MediaPlayer samsungMediaPlayer() {
        return new SamsungMediaPlayer(whiteAlbum()); <--- The whiteAlbum method is not always called.
    }
    */

    @Bean
    public MediaPlayer samsungMediaPlayer(CompactDisc compactDisc) {
        return new SamsungMediaPlayer(compactDisc);
    }


}
