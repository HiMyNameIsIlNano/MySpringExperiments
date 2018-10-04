package X03_XML_Property_Injection.soundsystem;

import X03_XML_Property_Injection.soundsystem.impl.WhiteAlbum;
import X03_XML_Property_Injection.soundsystem.model.CompactDisc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:X03_XML_Property_Injection/config/beans-definition.xml"})
public class CDPlayerTest {

    @Autowired
    private CompactDisc sgtPepper;

    /*
    * If inside the beans-definition.xml a bean with name whiteAlbumz would exist, then we would get an exception
    * as Spring would not know which bean to inject here as there are two beans "compactDisc" and "whiteAlbumz" in the
    * context, but none of them would fit here.
    * */
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
