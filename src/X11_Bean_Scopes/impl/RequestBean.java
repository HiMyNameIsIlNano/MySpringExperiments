package X11_Bean_Scopes.impl;


import X11_Bean_Scopes.model.DummyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@Component
@Request
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class RequestBean implements DummyBean{

    private String id;

    private LoginAction loginAction;

    @Autowired
    public RequestBean(LoginAction loginAction) {
        this.id = "SESSION_" + UUID.randomUUID().toString();
        this.loginAction = loginAction;
    }

    public LoginAction getLoginAction() {
        return loginAction;
    }

    @Override
    public String getBeanId() {
        return id;
    }
}
