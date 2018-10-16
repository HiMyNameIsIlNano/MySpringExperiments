package X13_Value_Injection_And_SpEL.soundsystem.impl;

import X13_Value_Injection_And_SpEL.soundsystem.model.CompactDisc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SgtPepper implements CompactDisc {

    /*
    * beatles.sgtPepper?.title: get the title only if the previous expression is not null
    *
    * */
    @Value("#{systemProperties['beatles.sgtPepper.title'] ?: 'Not found'}")
    private String title;

    @Value("#{systemProperties['beatles.sgtPepper.band'] ?: 'Not found'}")
    private String artist;

    @Override
    public void play() {
        System.out.println("Playing " + toString());
    }

    public String toString() {
        return this.title + " by " + this.artist;
    }


}
