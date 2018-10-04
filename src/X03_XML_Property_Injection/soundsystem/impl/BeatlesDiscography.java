package X03_XML_Property_Injection.soundsystem.impl;

import X03_XML_Property_Injection.soundsystem.model.CompactDisc;
import X03_XML_Property_Injection.soundsystem.model.Discography;

import java.util.List;

public class BeatlesDiscography implements Discography {

    private String artist;

    private List<CompactDisc> discography;

    public BeatlesDiscography() {
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setDiscography(List<CompactDisc> discography) {
        this.discography = discography;
    }

    @Override
    public List<CompactDisc> getDiscography() {
        return discography;
    }

    @Override
    public String toString() {
        return "BeatlesDiscography{" +
                "discography=" + discography.stream().map(Object::toString) + '}';
    }
}
