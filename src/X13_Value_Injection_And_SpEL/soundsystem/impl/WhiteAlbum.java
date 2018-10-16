package X13_Value_Injection_And_SpEL.soundsystem.impl;

import X13_Value_Injection_And_SpEL.soundsystem.model.CompactDisc;
import org.springframework.beans.factory.annotation.Value;

public class WhiteAlbum implements CompactDisc {

    @Value(value = "${beatles.whiteAlbum.title}")
    private String title;

    @Value(value = "${beatles.whiteAlbum.band}")
    private String artist;

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }

}
