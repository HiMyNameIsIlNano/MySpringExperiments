package X11_Bean_Scopes;

import X11_Bean_Scopes.config.BeanScopesConfig;
import X11_Bean_Scopes.impl.Prototype;
import X11_Bean_Scopes.model.DummyBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanScopesConfig.class)
public class BeanPrototypeScopesTest {

    @Autowired
    @Prototype
    private DummyBean dummyPrototypeBean1;

    @Autowired
    @Prototype
    private DummyBean dummyPrototypeBean2;

    @Test
    public void beansHaveDifferentIds() {
        assertNotNull(dummyPrototypeBean1);
        assertNotNull(dummyPrototypeBean2);

        assertNotEquals(dummyPrototypeBean1.getBeanId(), dummyPrototypeBean2.getBeanId());
        assertTrue(dummyPrototypeBean1.getBeanId().contains("PROTOYPE"));
    }
}
