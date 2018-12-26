package pizza;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import pizza.Ingredient.Type;
import pizza.data.IngredientRepository;
import pizza.data.PizzaRepository;
import pizza.data.UserRepository;

import java.util.Arrays;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(IngredientRepository repo,
        UserRepository userRepo, PasswordEncoder encoder, PizzaRepository pizzaRepository) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
        Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
        Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
        Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
        Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
        Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
        Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
        repo.save(flourTortilla);
        repo.save(cornTortilla);
        repo.save(groundBeef);
        repo.save(carnitas);
        repo.save(tomatoes);
        repo.save(lettuce);
        repo.save(cheddar);
        repo.save(jack);
        repo.save(salsa);
        repo.save(sourCream);
        
        
        userRepo.save(new User("habuma", encoder.encode("password"), 
            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
            "76227", "123-123-1234"));
        
        Pizza pizza1 = new Pizza();
        pizza1.setName("Carnivore");
        pizza1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
        pizzaRepository.save(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setName("Bovine Bounty");
        pizza2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
        pizzaRepository.save(pizza2);

        Pizza pizza3 = new Pizza();
        pizza3.setName("Veg-Out");
        pizza3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
        pizzaRepository.save(pizza3);

      }
    };
  }
  
}
