package X11_Bean_Scopes;

import X11_Bean_Scopes.impl.LoginAction;
import X11_Bean_Scopes.impl.Request;
import X11_Bean_Scopes.impl.RequestBean;
import X11_Bean_Scopes.model.DummyBean;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:X11_Bean_Scopes/config/beans-definition.xml")
@WebAppConfiguration
public class BeanRequestScopeTest {

    @Autowired
    @Request
    private DummyBean userService;

    @Autowired
    //private MockHttpServletRequest request;

    @Ignore("This one needs a bit more Config to work!!!")
    @Test
    public void requestScope() {
        //request.setParameter("user", "enigma");
        //request.setParameter("pswd", "$pr!ng");

        LoginAction loginAction = ((RequestBean) userService).getLoginAction();

        assertNotNull(loginAction);
    }
}