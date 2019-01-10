package pizza.restclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pizza.Ingredient;
import pizza.Pizza;

import java.net.URI;
import java.util.List;

@Service
@Slf4j
public class PizzaCloudClient {

    private RestTemplate rest;
    private Traverson traverson;

    public PizzaCloudClient(RestTemplate rest, Traverson traverson) {
        this.rest = rest;
        this.traverson = traverson;
    }

    //
    // GET examples
    //

    /*
     * Specify parameter as varargs argument
     */
    public Ingredient getIngredientById(String ingredientId) {
        /*
         * For all the methods, the name of the Ingredient.class specifies the domain type that the response body should
         * be bound to (i.e. the return type of the request)
         */
        return rest.getForObject("http://localhost:8080/ingredients/api/{id}", Ingredient.class, ingredientId);
    }

    /*
     * Alternate implementations...
     */
    /*
     * Specify parameters with a map
     */
    // public Ingredient getIngredientById(String ingredientId) {
    //   Map<String, String> urlVariables = new HashMap<>();
    //   urlVariables.put("id", ingredientId);
    //   return rest.getForObject("http://localhost:8080/ingredients/api/{id}",
    //       Ingredient.class, urlVariables);
    // }

    /*
     * Request with URI instead of String
     */
    // public Ingredient getIngredientById(String ingredientId) {
    //   Map<String, String> urlVariables = new HashMap<>();
    //   urlVariables.put("id", ingredientId);
    //   URI url = UriComponentsBuilder
    //             .fromHttpUrl("http://localhost:8080/ingredients/api/{id}")
    //             .build(urlVariables);
    //   return rest.getForObject(url, Ingredient.class);
    // }

    /*
     * Use getForEntity() instead of getForObject()
     */
    public Ingredient getIngredientByIdWithResponseBody(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                rest.getForEntity("http://localhost:8080/ingredients/api/{id}",
                        Ingredient.class, ingredientId);

        log.info("Fetched time: " + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public List<Ingredient> getAllIngredients() {
        return rest.exchange("http://localhost:8080/ingredients",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Ingredient>>() {})
                .getBody();
    }

    //
    // PUT examples
    //
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/api/{id}",
                ingredient, ingredient.getId());
    }

    //
    // POST examples
    //
    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

    /**
     * This Method returns a URI of the newly created resource instead of the resource object itself.
     */
    public URI createIngredientWithURI(Ingredient ingredient) {
        return rest.postForLocation("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

    public Ingredient createIngredientWithResponseEntity(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                rest.postForEntity("http://localhost:8080/ingredients",
                        ingredient,
                        Ingredient.class);
        log.info("New resource created at " +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    //
    // DELETE examples
    //
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/api/{id}",
                ingredient.getId());
    }

    //
    // Traverson with RestTemplate examples
    //
    public Iterable<Ingredient> getAllIngredientsWithTraverson() {
        /*
        * Java type erasure makes it difficult to provide type information for a generic type. Creating a
        * ParameterizedTypeReference helps avoid deserialization issues.
        * */
        ParameterizedTypeReference<Resources<Ingredient>> ingredientType =
                new ParameterizedTypeReference<Resources<Ingredient>>() {};

        /*
         * By calling the follow() method on the Traverson object, you can navigate to the resource whose link’s
         * relation name is ingredients. The result of the Rest Function must be stored in an object of type Resource
         */
        Resources<Ingredient> ingredientRes =
                traverson
                        .follow("ingredients")
                        .toObject(ingredientType);

        return ingredientRes.getContent();
    }

    /**
     * When one needs to both navigate an API and update or delete resources, it is necessary to use RestTemplate and
     * Traverson together.
     *
     * Traverson can still be used to navigate to the link where a new resource will be created. Then RestTemplate can
     * be given that link to do a POST, PUT, DELETE or any other HTTP request one needs.
     * */
    public Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();

        return rest.postForObject(ingredientsUrl,
                ingredient,
                Ingredient.class);
    }

    public Iterable<Pizza> getRecentPizzasWithTraverson() {
        ParameterizedTypeReference<Resources<Pizza>> pizzaType = new ParameterizedTypeReference<Resources<Pizza>>() {};

        Resources<Pizza> pizzaResource =
                traverson
                        .follow("pizzas")
                        .follow("recents")
                        .toObject(pizzaType);

        // Alternatively, list the two paths in the same call to follow()
//    Resources<Pizza> pizzaResource =
//        traverson
//          .follow("tacos", "recents")
//          .toObject(pizzaType);

        return pizzaResource.getContent();
    }

}
