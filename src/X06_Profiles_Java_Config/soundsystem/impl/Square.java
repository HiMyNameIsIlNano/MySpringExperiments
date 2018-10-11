package X06_Profiles_Java_Config.soundsystem.impl;

import X06_Profiles_Java_Config.soundsystem.ShapeEnum;
import X06_Profiles_Java_Config.soundsystem.model.Shape;
import org.springframework.stereotype.Component;

@Component
public class Square implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.SQUARE;
    }
}
