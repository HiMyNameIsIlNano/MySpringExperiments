package X10_Wiring_Multiple_Beans_Cust_Annot;

import X10_Wiring_Multiple_Beans_Cust_Annot.config.MultipleBeansConfig;
import X10_Wiring_Multiple_Beans_Cust_Annot.dessert.impl.Cold;
import X10_Wiring_Multiple_Beans_Cust_Annot.dessert.impl.Soft;
import X10_Wiring_Multiple_Beans_Cust_Annot.dessert.model.Dessert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MultipleBeansConfig.class)
public class MultipleBeansTest {

    @Autowired
    @Cold
    private Dessert iceCream;

    @Autowired
    @Soft
    private Dessert cake;

    @Test
    public void doTestPrimaryAnnotationWorks() {
        assertNotNull(iceCream);
        assertNotNull(cake);
    }

}

