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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder encoder,
                                        PizzaRepository pizzaRepository) { // user ingredientRepository for ease of testing with a built-in user
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Ingredient> ingredientList = new ArrayList<>();
                ingredientList.add(new Ingredient("SOURD", "Sourdough", Type.DOUGH));
                ingredientList.add(new Ingredient("NORMD", "Normal Dough", Type.DOUGH));
                ingredientList.add(new Ingredient("MOZZ", "Mozzarella Cheese", Type.CHEESE));
                ingredientList.add(new Ingredient("PARMC", "Parmesan Cheese", Type.CHEESE));
                ingredientList.add(new Ingredient("SMOKC", "Smoked Cheese", Type.CHEESE));
                ingredientList.add(new Ingredient("EGGP", "Eggplants", Type.TOPPINGS));
                ingredientList.add(new Ingredient("ZUCCH", "Zucchini", Type.TOPPINGS));
                ingredientList.add(new Ingredient("HAM", "Ham", Type.TOPPINGS));
                ingredientList.add(new Ingredient("SSAL", "Spicy Salami", Type.TOPPINGS));
                ingredientList.add(new Ingredient("PHAM", "Parma Ham", Type.TOPPINGS));
                ingredientList.add(new Ingredient("TOMS", "Tomato Sauce", Type.SAUCE));
                ingredientList.add(new Ingredient("NOSAUCE", "No Sauce", Type.SAUCE));
                for (Ingredient ingredient : ingredientList) {
                    ingredientRepository.save(ingredient);
                }

                userRepository.save(new User("user", encoder.encode("user"),
                        "A User 1", "A Street 1", "City 1", "IT",
                        "999", "123-123-1234"));
                userRepository.save(new User("admin", encoder.encode("admin"),
                        "An Admin 2", "A Street 2", "City 2", "IT",
                        "999", "123-123-1234"));

                List<Pizza> pizzas = new ArrayList<>();
                Pizza bestPizza = new Pizza();
                bestPizza.setName("Best Pizza");
                bestPizza.setIngredients(Arrays.asList(
                        new Ingredient("SOURD", "Sourdough", Type.DOUGH),
                        new Ingredient("MOZZ", "Mozzarella Cheese", Type.CHEESE),
                        new Ingredient("PARMC", "Parmesan Cheese", Type.CHEESE),
                        new Ingredient("EGGP", "Eggplants", Type.TOPPINGS),
                        new Ingredient("TOMS", "Tomato Sauce", Type.SAUCE)));
                pizzas.add(bestPizza);

                Pizza normalPizza = new Pizza();
                normalPizza.setName("Normal Pizza");
                normalPizza.setIngredients(Arrays.asList(
                        new Ingredient("NORMD", "Normal Dough", Type.DOUGH),
                        new Ingredient("MOZZ", "Mozzarella Cheese", Type.CHEESE),
                        new Ingredient("SSAL", "Spicy Salami", Type.TOPPINGS),
                        new Ingredient("TOMS", "Tomato Sauce", Type.SAUCE)));
                pizzas.add(normalPizza);

                Pizza margherita = new Pizza();
                margherita.setName("Margherita");
                margherita.setIngredients(Arrays.asList(
                        new Ingredient("NORMD", "Normal Dough", Type.DOUGH),
                        new Ingredient("MOZZ", "Mozzarella Cheese", Type.CHEESE),
                        new Ingredient("TOMS", "Tomato Sauce", Type.SAUCE)));
                pizzas.add(margherita);

                for (Pizza pizza : pizzas) {
                    pizzaRepository.save(pizza);
                }

            }
        };
    }

}
