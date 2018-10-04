package X01_Java_Config.soundsystem.impl;

import X01_Java_Config.soundsystem.model.CompactDisc;
import X01_Java_Config.soundsystem.model.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;

public class SamsungMediaPlayer implements MediaPlayer {

    private CompactDisc compactDisc;

    @Autowired
    public SamsungMediaPlayer(CompactDisc compactDisc) {
        System.out.println("Inserting: " + compactDisc.toString() + " into " + this.getClass().getSimpleName());
        this.compactDisc = compactDisc;
    }

    @Override
    public void playCompactDisc() {
        compactDisc.play();
    }
}
