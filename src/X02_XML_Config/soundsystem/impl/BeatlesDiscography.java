package X02_XML_Config.soundsystem.impl;

import X02_XML_Config.soundsystem.model.CompactDisc;
import X02_XML_Config.soundsystem.model.Discography;

import java.util.List;

public class BeatlesDiscography implements Discography {

    private List<CompactDisc> discography;

    public BeatlesDiscography() {
    }

    public BeatlesDiscography(List<CompactDisc> discography) {
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
