package pizza.kitchen.messaging.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pizza.Order;
import pizza.kitchen.KitchenUI;

@Profile("kafka-listener")
@Component
@Slf4j
public class OrderListener {
  
  private KitchenUI ui;

  @Autowired
  public OrderListener(KitchenUI ui) {
    this.ui = ui;
  }

  @KafkaListener(topics="pizzacloud.orders.topic")
  public void handle(Order order, ConsumerRecord<String, Order> record) {
    log.error("Received from partition {} with timestamp {}",
        record.partition(), record.timestamp());
    
    ui.displayOrder(order);
  }
  
}
