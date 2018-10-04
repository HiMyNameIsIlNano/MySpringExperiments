package X03_XML_Property_Injection.soundsystem.impl;

import X03_XML_Property_Injection.soundsystem.model.CompactDisc;

public class WhiteAlbum implements CompactDisc {

    private String title;
    private String artist;

    public WhiteAlbum() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
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
