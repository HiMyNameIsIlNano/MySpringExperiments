package pizza.messaging;

import pizza.Order;

public interface OrderMessagingService {

  void sendOrder(Order order);
  
}
