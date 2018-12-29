package pizza.web.api;

import java.util.List;

import org.springframework.hateoas.Resources;

public class PizzaResources extends Resources<PizzaResource> {
    public PizzaResources(List<PizzaResource> pizzaResources) {
        super(pizzaResources);
    }
}