package X01_Java_Config.soundsystem.impl;

import X01_Java_Config.soundsystem.model.CompactDisc;
import X01_Java_Config.soundsystem.model.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SonyMediaPlayer implements MediaPlayer {

    private CompactDisc compactDisc;

    /**
     * This is sort of Setter Injection. Spring does not bind to any specific Method Name when injecting a property.
     * The assumption is that there exists one and only one bean of type CompactDisc.
    */
    @Autowired
    public void insertCompactDisc(CompactDisc compactDisc) {
        System.out.println("Inserting: " + compactDisc.toString() + " into " + this.getClass().getSimpleName());
        this.compactDisc = compactDisc;
    }

    @Override
    public void playCompactDisc() {
        compactDisc.play();
    }
}
