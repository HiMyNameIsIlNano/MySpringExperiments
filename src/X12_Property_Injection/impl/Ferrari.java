package X12_Property_Injection.impl;

import X12_Property_Injection.model.Auto;

public class Ferrari implements Auto {

    private String type;

    public Ferrari(String value) {
        this.type = value;
    }

    @Override
    public String accelerate() {
        return this.type + "...is accelerating (hear me roar)!";
    }

    @Override
    public String slowDown() {
        return this.type + "...is slowing down!";
    }

}
