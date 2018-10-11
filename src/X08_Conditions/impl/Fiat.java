package X08_Conditions.impl;

import X08_Conditions.model.Auto;
import org.springframework.stereotype.Component;

@Component
public class Fiat implements Auto{

    private String type;

    public Fiat() {
        this.type = "Fiat";
    }

    @Override
    public String accelerate() {
        return this.type + "...is accelerating (not too fast)!";
    }

    @Override
    public String slowDown() {
        return this.type + "...is slowing down!";
    }
}
