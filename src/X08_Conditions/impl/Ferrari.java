package X08_Conditions.impl;

import X08_Conditions.model.Auto;

public class Ferrari implements Auto {

    private String type;

    public Ferrari() {
        this.type = "Ferrari";
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
