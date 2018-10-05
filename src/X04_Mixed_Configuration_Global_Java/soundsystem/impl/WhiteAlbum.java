package X04_Mixed_Configuration_Global_Java.soundsystem.impl;

import X04_Mixed_Configuration_Global_Java.soundsystem.model.CompactDisc;

public class WhiteAlbum implements CompactDisc {

    private String title;
    private String artist;

    public WhiteAlbum() {
    }

    public WhiteAlbum(String title, String artist) {
        this.title = title;
        this.artist = artist;
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
