package X02_XML_Config.soundsystem.impl;

import X02_XML_Config.soundsystem.model.CompactDisc;

public class WhiteAlbum implements CompactDisc {

    private String title;
    private String artist;

    public WhiteAlbum(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }

}
