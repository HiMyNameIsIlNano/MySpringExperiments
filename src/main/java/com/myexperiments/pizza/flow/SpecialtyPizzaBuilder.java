package com.myexperiments.pizza.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.myexperiments.pizza.domain.Pizza;
import com.myexperiments.pizza.domain.Topping;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

public class SpecialtyPizzaBuilder implements Action {

   private static final Logger LOGGER = Logger.getLogger(SpecialtyPizzaBuilder.class.getName());

   public Event execute(RequestContext request) throws Exception {
      String type = request.getRequestParameters().get("pizzaType");

      LOGGER.info("BUILDING A SPECIALTY PIZZA:  " + type);

      Pizza pizza = (Pizza) request.getFlowScope().get("pizza");

      if ("MEAT".equals(type)) {
         LOGGER.info("BUILDING A CARNIVORE");

         List<Topping> meats = new ArrayList<Topping>();

         meats.add(Topping.CANADIAN_BACON);
         meats.add(Topping.HAMBURGER);
         meats.add(Topping.PEPPERONI);
         meats.add(Topping.SAUSAGE);

         pizza.setToppings(meats);
      } else if ("VEGGIE".equals(type)) {
         LOGGER.info("BUILDING A HERBIVORE");
         List<Topping> meats = new ArrayList<Topping>();

         meats.add(Topping.GREEN_PEPPER);
         meats.add(Topping.MUSHROOM);
         meats.add(Topping.PINEAPPLE);
         meats.add(Topping.TOMATO);

         pizza.setToppings(meats);
      } else if ("THEWORKS".equals(type)) {
         LOGGER.info("BUILDING AN OMNIVORE");

         List<Topping> meats = new ArrayList<Topping>();
         System.out.println("THE WORKS!");

         meats.add(Topping.CANADIAN_BACON);
         meats.add(Topping.HAMBURGER);
         meats.add(Topping.PEPPERONI);
         meats.add(Topping.SAUSAGE);
         meats.add(Topping.GREEN_PEPPER);
         meats.add(Topping.MUSHROOM);
         meats.add(Topping.PINEAPPLE);
         meats.add(Topping.TOMATO);
         meats.add(Topping.EXTRA_CHEESE);
         meats.add(Topping.ONION);
         meats.add(Topping.JALAPENO);

         pizza.setToppings(meats);
      }

      request.getFlowScope().put("pizza", pizza);

      return new Event(this, "success");
   }
}
