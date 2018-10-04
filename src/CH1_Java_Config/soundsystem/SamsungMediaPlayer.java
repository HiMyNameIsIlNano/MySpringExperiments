package CH1_Java_Config.soundsystem;

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
