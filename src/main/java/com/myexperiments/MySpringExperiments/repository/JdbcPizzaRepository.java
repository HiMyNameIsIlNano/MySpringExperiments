package com.myexperiments.MySpringExperiments.repository;

import com.myexperiments.MySpringExperiments.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {

    private static final String INSERT_SQL = "insert into Pizza (name, createdAt) values (?, ?)";
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcPizzaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Pizza save(Pizza pizza) {
        Optional<Long> pizzaId = savePizzaInfo(pizza);

        if (pizzaId.isPresent()) {
            Long id = pizzaId.get();
            pizza.setId(id);
            for (String ingredient : pizza.getIngredients()) {
                saveIngredientToPizza(ingredient, id);
            }
        }

        return pizza;
    }

    private Optional<Long> savePizzaInfo(Pizza pizza) {
        pizza.setCreatedAt(new Date());

        PreparedStatementCreator psc =
                new PreparedStatementCreatorFactory(
                        "insert into Pizza (name, createdAt) values (?, ?)",
                        Types.VARCHAR, Types.TIMESTAMP
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                pizza.getName(),
                                new Timestamp(pizza.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        Number key = keyHolder.getKey();

        return key != null ? Optional.of(key.longValue()) : Optional.empty();
    }

    private void saveIngredientToPizza(String ingredientId, long pizzaId) {
        jdbc.update(
                "insert into Pizza_Ingredients (pizzaId, ingredientId) " +
                        "values (?, ?)",
                pizzaId, ingredientId);
    }

}
