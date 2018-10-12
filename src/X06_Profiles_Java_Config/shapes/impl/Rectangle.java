package X06_Profiles_Java_Config.shapes.impl;

import X06_Profiles_Java_Config.shapes.ShapeEnum;
import X06_Profiles_Java_Config.shapes.model.Shape;

public class Rectangle implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.RECTANGLE;
    }
}
