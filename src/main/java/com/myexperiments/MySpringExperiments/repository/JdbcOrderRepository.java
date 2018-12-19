package com.myexperiments.MySpringExperiments.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myexperiments.MySpringExperiments.domain.Order;
import com.myexperiments.MySpringExperiments.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderPizzaInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Pizza_Order")
                .usingGeneratedKeyColumns("id");

        this.orderPizzaInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Pizza_Order_Pizzas");

        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = saveOrderDetails(order);
        order.setId(orderId);

        List<Pizza> pizzas = order.getDesignedPizza();
        for (Pizza pizza : pizzas) {
            savePizzaToOrder(pizza, orderId);
        }
        return order;
    }

    private long saveOrderDetails(Order order) {
        // Convert an Order into a Map
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt());

        long orderId = orderInserter
                .executeAndReturnKey(values)
                .longValue();

        return orderId;
    }

    private void savePizzaToOrder(Pizza pizza, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("orderId", orderId);
        values.put("pizzaId", pizza.getId());
        orderPizzaInserter.execute(values);
    }
}
