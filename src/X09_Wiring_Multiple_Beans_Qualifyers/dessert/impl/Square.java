package X09_Wiring_Multiple_Beans_Qualifyers.dessert.impl;

import X09_Wiring_Multiple_Beans_Qualifyers.dessert.model.Shape;
import X09_Wiring_Multiple_Beans_Qualifyers.dessert.ShapeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("pointy")
public class Square implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.SQUARE;
    }
}
