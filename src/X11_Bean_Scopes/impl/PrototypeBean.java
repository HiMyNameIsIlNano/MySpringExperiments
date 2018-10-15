package X11_Bean_Scopes.impl;


import X11_Bean_Scopes.model.DummyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Prototype
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrototypeBean implements DummyBean {

    private String id;

    @Autowired
    public PrototypeBean() {
        this.id = "PROTOYPE_" + UUID.randomUUID().toString();
    }

    @Override
    public String getBeanId() {
        return this.id;
    }
}
