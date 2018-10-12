package X07_Profiles_XML_Config.shapes.impl;

import X07_Profiles_XML_Config.shapes.ShapeEnum;
import X07_Profiles_XML_Config.shapes.model.Shape;

public class Rectangle implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.RECTANGLE;
    }
}
