package X12_Property_Injection.impl;

import X12_Property_Injection.model.Auto;

public class Fiat implements Auto {

    private String type;

    public Fiat(String type) {
        this.type = type;
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
