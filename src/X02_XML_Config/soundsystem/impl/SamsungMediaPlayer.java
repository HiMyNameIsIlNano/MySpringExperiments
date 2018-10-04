package X02_XML_Config.soundsystem.impl;

import X02_XML_Config.soundsystem.model.CompactDisc;
import X02_XML_Config.soundsystem.model.MediaPlayer;

public class SamsungMediaPlayer implements MediaPlayer {

    private CompactDisc compactDisc;

    //@Autowired
    public SamsungMediaPlayer(CompactDisc compactDisc) {
        System.out.println("Inserting: " + compactDisc.toString() + " into " + this.getClass().getSimpleName());
        this.compactDisc = compactDisc;
    }

    @Override
    public void playCompactDisc() {
        compactDisc.play();
    }
}
