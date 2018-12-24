package com.myexperiments.MySpringExperiments.web.repository;

import com.myexperiments.MySpringExperiments.web.domain.Order;
import com.myexperiments.MySpringExperiments.web.domain.UserAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    /**
     * The method is called findByUser and not findByUserAccount because in the Order Class the field UserAccount
     * is called user.
     * */
    List<Order> findByUserOrderByPlacedAtDesc(UserAccount userAccount, Pageable pageable);

}
