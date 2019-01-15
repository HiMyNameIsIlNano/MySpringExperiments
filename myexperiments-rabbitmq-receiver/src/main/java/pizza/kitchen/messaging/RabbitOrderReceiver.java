package pizza.kitchen.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import pizza.Order;
import pizza.kitchen.OrderReceiver;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements OrderReceiver {

    private RabbitTemplate rabbit;

    public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public Order receiveOrder() {
        return (Order) rabbit.receiveAndConvert("pizzacloud.order.queue");
    }

    /**
     * The only requirement to using a ParameterizedTypeReference with receiveAndConvert() is that the message
     * converter must be an implementation of SmartMessageConverter; Jackson2JsonMessageConverter is the only
     * out-of-the-box implementation to choose from.
     * */
    public Order receiveOrderWithoutCast() {
        return rabbit.receiveAndConvert("pizzacloud.order.queue", new ParameterizedTypeReference<Order>() {
        });
    }

}
