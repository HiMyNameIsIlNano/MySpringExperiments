package CH1_XML_Config.soundsystem;

import org.springframework.stereotype.Component;

@Component
public class SgtPepper implements CompactDisc {

    private String title = "Sgt. Pepper's Lonely Heart Club Band";
    private String artist = "The Beatles";

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }


}
