package X13_Value_Injection_And_SpEL.soundsystem.impl;

import X13_Value_Injection_And_SpEL.soundsystem.model.CompactDisc;
import org.springframework.beans.factory.annotation.Value;

public class LetItBe implements CompactDisc {

    private String title;

    private String artist;

    public LetItBe(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }

}
