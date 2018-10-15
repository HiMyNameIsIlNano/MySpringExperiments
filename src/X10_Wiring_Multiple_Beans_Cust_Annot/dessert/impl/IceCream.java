package X10_Wiring_Multiple_Beans_Cust_Annot.dessert.impl;

import X10_Wiring_Multiple_Beans_Cust_Annot.dessert.model.Dessert;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
/*
* Without this injection would not work as two beans of the same type are defined and Spring would not know
* which one to pick up.
*
* This annotation can also be used when defining a bean explicitly with @Bean and it is also available with XML
* configuration.
* */
@Cold
public class IceCream implements Dessert {
}
