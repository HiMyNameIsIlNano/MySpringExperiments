package pizza.kitchen.messaging.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import pizza.Order;
import pizza.kitchen.KitchenUI;

@Profile("rabbitmq-listener")
@Component
public class OrderListener {
  
  private KitchenUI ui;

  @Autowired
  public OrderListener(KitchenUI ui) {
    this.ui = ui;
  }

  @RabbitListener(queues = "pizzacloud.order.queue")
  public void receiveOrder(Order order) {
    ui.displayOrder(order);
  }
  
}
