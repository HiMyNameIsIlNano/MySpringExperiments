package X07_Profiles_XML_Config.soundsystem.impl;

import X07_Profiles_XML_Config.soundsystem.ShapeEnum;
import X07_Profiles_XML_Config.soundsystem.model.Shape;
import org.springframework.stereotype.Component;

@Component
public class Rectangle implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.RECTANGLE;
    }
}
