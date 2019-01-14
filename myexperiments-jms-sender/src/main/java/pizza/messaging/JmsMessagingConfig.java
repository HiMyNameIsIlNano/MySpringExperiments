package pizza.messaging;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import pizza.Order;

import javax.jms.Destination;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JmsMessagingConfig {

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("pizzacloud.outgoing.order.queue");
    }

    /**
     * At the receiving application, a similar message converter will have been configured, mapping order to its own
     * understanding of what an order is.
     * */
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        /*
         * Instead of the fully qualified classname being sent in the message’s _typeId property, the value order will
         * be sent
         */
        typeIdMappings.put("order", Order.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }

}
