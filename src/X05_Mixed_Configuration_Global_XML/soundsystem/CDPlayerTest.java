package X05_Mixed_Configuration_Global_XML.soundsystem;

import X05_Mixed_Configuration_Global_XML.soundsystem.impl.WhiteAlbum;
import X05_Mixed_Configuration_Global_XML.soundsystem.model.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:X05_Mixed_Configuration_Global_XML/config/global-beans-definition.xml")
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
