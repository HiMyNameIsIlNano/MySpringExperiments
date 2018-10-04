package CH1_Java_Config.soundsystem;

import CH1_Java_Config.configuration.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTest {

    @Autowired
    private CompactDisc compactDisc;

    @Autowired
    private MediaPlayer mediaPlayer;

    @Test
    public void compactDiscShouldNotBeNull() {
        assertNotNull(compactDisc);
    }

    @Test
    public void insertedDiscShouldNotBeNull() {
        assertNotNull(mediaPlayer);
        mediaPlayer.playCompactDisc();
    }
}
