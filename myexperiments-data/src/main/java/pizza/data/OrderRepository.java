package pizza.data;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import pizza.Order;
import pizza.User;

public interface OrderRepository 
         extends CrudRepository<Order, Long> {

  List<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);

}
