package X04_Mixed_Configuration_Global_Java;

import X04_Mixed_Configuration_Global_Java.config.SoundSystemConfig;
import X04_Mixed_Configuration_Global_Java.soundsystem.impl.WhiteAlbum;
import X04_Mixed_Configuration_Global_Java.soundsystem.model.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class CDPlayerTest {

    @Autowired
    private CompactDisc sgtPepper;

    @Autowired
    private CompactDisc whiteAlbum;

    @Test
    public void compactDiscShouldNotBeNull() {
        assertNotNull(sgtPepper);
    }

    @Test
    public void compactDiscIsWhiteAlbum() {
        assertNotNull(whiteAlbum);
        assertEquals("White Album", ((WhiteAlbum) whiteAlbum).getTitle());
        assertEquals("The Beatles", ((WhiteAlbum) whiteAlbum).getArtist());
    }

}
