package pizza.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import pizza.Order;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;


@Service
public class JmsSenderOrderMessagingService implements OrderMessagingService {

    // Defined in @Config
    private Destination orderQueue;

    /**
     * With a JMS starter dependency (either Artemis or ActiveMQ) in the classpath, Spring
     * Boot will autoconfigure a JmsTemplate (among other things) that you one inject and
     * use to send and receive messages.
     * */
    private JmsTemplate jms;

    @Autowired
    public JmsSenderOrderMessagingService(Destination orderQueue, JmsTemplate jms) {
        this.orderQueue = orderQueue;
        this.jms = jms;
    }

    /*
    @Override
    public void sendOrder(Order order) {
        jms.send(new MessageCreator() {
                     @Override
                     public Message createMessage(Session session)
                             throws JMSException {
                         return session.createObjectMessage(order);
                     }
                 }
        );
    }

    @Override
    public void sendOrder(Order order) {
        // Sends the message to the queue as defined by the default destination in application.yml
        jms.send(orderQueue, session -> session.createObjectMessage(order));
    }
    */

    /**
     * The method uses the bean defined in the JmsMessagingConfiguration as the destination for the message.
     *
     * Using the convertAndSend method simplifies the message publication by eliminating the  need to provide a
     * MessageCreator. Under the covers, this is achieved with an implementation of MessageConverter that does the
     * dirty work of converting objects to Messages.
     *
     * In order to perform the conversion Spring offers 4 different Options. SimpleMessageConverter is the default one,
     * but it requires that the object being sent implement Serializable.
     * This may be a good idea, but you may prefer to use one of the other message converters, such as
     * MappingJackson2MessageConverter, to avoid that restriction. Alternative Converters must be defined as beans in a
     * @Config annotated class.
     * */
    @Override
    public void sendOrder(Order order) {
        // Before sending the message the method addOrderSource will be called and an extra header will be attached to
        // the message sent to the queue.
        jms.convertAndSend(orderQueue, order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

}
