package pizza.kitchen.messaging.jms;

import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import pizza.Order;
import pizza.kitchen.OrderReceiver;

@Profile("jms-template")
@Component("templateOrderReceiver")
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jms;

    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
        /**
         * The type ID property in the message will guide the converter in converting the Message to an Order, but
         * as the result of the conversion is an Object, that requires casting before you can return it.
         */
        return (Order) jms.receiveAndConvert("pizzacloud.incoming.order.queue");
    }

}
