package pizza.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import pizza.Ingredient;
import pizza.Pizza;

import java.net.URI;
import java.util.List;

@SpringBootConfiguration
@ComponentScan
@Slf4j
public class RestExamples {

  public static void main(String[] args) {
    SpringApplication.run(RestExamples.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public CommandLineRunner fetchIngredients(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      log.info("----------------------- GET -------------------------");
      log.info("GETTING INGREDIENT BY IDE");
      log.info("Ingredient:  " + pizzaCloudClient.getIngredientById("MOZZ"));
      log.info("GETTING ALL INGREDIENTS");
      List<Ingredient> ingredients = pizzaCloudClient.getAllIngredients();
      log.info("All ingredients:");
      for (Ingredient ingredient : ingredients) {
        log.info("   - " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner putAnIngredient(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      log.info("----------------------- PUT -------------------------");
      Ingredient before = pizzaCloudClient.getIngredientById("EGGP");
      log.info("BEFORE:  " + before);
      pizzaCloudClient.updateIngredient(new Ingredient("EGGPR", "Roman Eggplant", Ingredient.Type.TOPPINGS));
      Ingredient after = pizzaCloudClient.getIngredientById("EGGPR");
      log.info("AFTER:  " + after);
    };
  }
  
  @Bean
  public CommandLineRunner addAnIngredient(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      log.info("----------------------- POST -------------------------");
      Ingredient pepper = new Ingredient("BPPR", "Belly Peppers", Ingredient.Type.TOPPINGS);
      Ingredient pepperAfter = pizzaCloudClient.createIngredient(pepper);
      log.info("AFTER=1:  " + pepperAfter);
      Ingredient ricottaCheese = new Ingredient("RICC", "Ricotta Cheese", Ingredient.Type.CHEESE);
      URI uri = pizzaCloudClient.createIngredientWithURI(ricottaCheese);
      log.info("AFTER-2:  " + uri);
      Ingredient bacon = new Ingredient("BACN", "Bacon", Ingredient.Type.TOPPINGS);
      Ingredient baconAfter = pizzaCloudClient.createIngredientWithResponseEntity(bacon);
      log.info("AFTER-3:  " + baconAfter);
    };
  }

  @Bean
  public CommandLineRunner deleteAnIngredient(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      log.info("----------------------- DELETE -------------------------");
      // start by adding a few ingredients so that we can delete them later...
      Ingredient eggplant = new Ingredient("EGGPR", "Roman Eggplant", Ingredient.Type.TOPPINGS);
      pizzaCloudClient.createIngredient(eggplant);
      Ingredient bacon = new Ingredient("BACN", "Bacon", Ingredient.Type.TOPPINGS);
      pizzaCloudClient.createIngredient(bacon);

      
      Ingredient before = pizzaCloudClient.getIngredientById("MOZZ");
      log.info("BEFORE:  " + before);
      pizzaCloudClient.deleteIngredient(before);
      Ingredient after = pizzaCloudClient.getIngredientById("MOZZ");
      log.info("AFTER:  " + after);
      before = pizzaCloudClient.getIngredientById("EGGPR");
      log.info("BEFORE:  " + before);
      pizzaCloudClient.deleteIngredient(before);
      after = pizzaCloudClient.getIngredientById("EGGPR");
      log.info("AFTER:  " + after);
      before = pizzaCloudClient.getIngredientById("BACN");
      log.info("BEFORE:  " + before);
      pizzaCloudClient.deleteIngredient(before);
      after = pizzaCloudClient.getIngredientById("BACN");
      log.info("AFTER:  " + after);
    };
  }
  
  //
  // Traverson examples
  //
  @Bean
  public Traverson traverson() {
    // HAL-style hyperlinks
    Traverson traverson = new Traverson(URI.create("http://localhost:8080/api"), MediaTypes.HAL_JSON);
    return traverson;
  }
  
  @Bean
  public CommandLineRunner traversonGetIngredients(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      Iterable<Ingredient> ingredients = pizzaCloudClient.getAllIngredientsWithTraverson();
      log.info("----------------------- GET INGREDIENTS WITH TRAVERSON -------------------------");
      for (Ingredient ingredient : ingredients) {
        log.info("   -  " + ingredient);
      }
    };
  }
  
  @Bean
  public CommandLineRunner traversonSaveIngredient(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      Ingredient pico = pizzaCloudClient.addIngredient(
          new Ingredient("PICO", "Pico de Gallo", Ingredient.Type.SAUCE));
      List<Ingredient> allIngredients = pizzaCloudClient.getAllIngredients();
      log.info("----------------------- ALL INGREDIENTS AFTER SAVING PICO -------------------------");
      for (Ingredient ingredient : allIngredients) {
        log.info("   -  " + ingredient);
      }
      pizzaCloudClient.deleteIngredient(pico);
    };
  }
  
  @Bean
  public CommandLineRunner traversonRecentPizzas(PizzaCloudClient pizzaCloudClient) {
    return args -> {
      Iterable<Pizza> recentPizzas = pizzaCloudClient.getRecentPizzasWithTraverson();
      log.info("----------------------- GET RECENT PIZZAS WITH TRAVERSON -------------------------");
      for (Pizza taco : recentPizzas) {
        log.info("   -  " + taco);
      }
    };
  }

}
