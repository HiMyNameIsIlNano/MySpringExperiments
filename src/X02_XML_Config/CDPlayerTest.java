package X02_XML_Config;

import X02_XML_Config.soundsystem.impl.WhiteAlbum;
import X02_XML_Config.soundsystem.model.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:X02_XML_Config/config/beans-definition.xml"})
public class CDPlayerTest {

    @Autowired
    private CompactDisc compactDisc;

    /*
    * If inside the beans-definition.xml a bean with name whiteAlbumz would exist, then we would get an exception
    * as Spring would not know which bean to inject here as there are two beans "compactDisc" and "whiteAlbumz" in the
    * context, but none of them would fit here.
    * */
    @Autowired
    private CompactDisc whiteAlbum;

    @Test
    public void compactDiscShouldNotBeNull() {
        assertNotNull(compactDisc);
    }

    @Test
    public void compactDiscIsWhiteAlbum() {
        assertNotNull(whiteAlbum);
        assertEquals("White Album", ((WhiteAlbum) whiteAlbum).getTitle());
        assertEquals("The Beatles", ((WhiteAlbum) whiteAlbum).getArtist());
    }

}
