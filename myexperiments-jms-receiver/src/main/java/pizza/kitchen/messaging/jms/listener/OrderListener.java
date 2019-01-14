package pizza.kitchen.messaging.jms.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import pizza.Order;
import pizza.kitchen.KitchenUI;

@Profile("jms-listener")
@Component
public class OrderListener {

    private KitchenUI ui;

    @Autowired
    public OrderListener(KitchenUI ui) {
        this.ui = ui;
    }

    // Component that listens passively for messages. It is called by Spring upon the reception of a message.
    @JmsListener(destination = "pizzacloud.incoming.order.queue")
    public void receiveOrder(Order order) {
        ui.displayOrder(order);
    }

}
