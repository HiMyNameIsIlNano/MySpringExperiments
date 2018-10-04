package CH1_XML_Config.soundsystem;

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
