package X01_Java_Config.soundsystem.impl;

import X01_Java_Config.soundsystem.model.CompactDisc;

public class WhiteAlbum implements CompactDisc {

    private String title = "The White Album";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }

}
