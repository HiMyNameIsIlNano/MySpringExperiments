package X09_Wiring_Multiple_Beans_Qualifyers.dessert.impl;

import X09_Wiring_Multiple_Beans_Qualifyers.dessert.model.Shape;
import X09_Wiring_Multiple_Beans_Qualifyers.dessert.ShapeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("rounded")
public class Circle implements Shape {

    @Override
    public ShapeEnum whoAmI() {
        return ShapeEnum.CIRCLE;
    }
}
