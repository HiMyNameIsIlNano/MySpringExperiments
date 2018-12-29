package pizza.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizza.Pizza;
import pizza.data.PizzaRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/*
 * @RestController is a stereotype annotation like @Controller and @Service that marks a class for discovery by component
 * scanning. But most relevant to the discussion of REST, the @RestController annotation tells Spring that all handler
 * methods in the controller should have their return value written directly to the body of the response, rather than being
 * carried in the model to a view for rendering. Alternatively, one could have annotated DesignTacoController with @Controller,
 * just like with any Spring MVC controller but then one would need to also annotate all of the handler methods with
 * @ResponseBody to achieve the same result.
 * */
@RestController
/*
 * It allows for another controller to handle requests with the same paths, so long as those requests do not require JSON output.
 * */
@RequestMapping(path = "/design", produces = "application/json")
/*
 * Because the Angular portion of the application will be running on a separate host and/or port from the API, the web browser
 * will prevent the Angular client from consuming the API. As applied here, @CrossOrigin allows clients from any domain to
 * consume the API.
 * */
@CrossOrigin(origins = "*")
public class DesignPizzaController {

    private PizzaRepository pizzaRepository;

    @Autowired
    EntityLinks entityLinks;

    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/recent")
    public Iterable<Pizza> recentPizzas() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return pizzaRepository.findAll(page).getContent();
    }

    @GetMapping("/recenth")
    public Resources<PizzaResource> recentPizzasH() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Pizza> pizzas = pizzaRepository.findAll(page).getContent();

        /*
        // It builds the link to the rest Interface by avoiding an hardcoded URL
        Link recentsLink = ControllerLinkBuilder
                .linkTo(DesignPizzaController.class)
                .slash("recent")
                .withRel("recents");
        */

        List<PizzaResource> pizzaResources = new PizzaResourceAssembler().toResources(pizzas);
        Resources<PizzaResource> recentResources = new Resources<>(pizzaResources);
        // It derives the base URL from both the controller’s base path (i.e. methodOn(DesignPizzaController.class)) and the
        // method’s mapped path (i.e. recentPizzas()).
        recentResources.add(
                linkTo(methodOn(DesignPizzaController.class).recentPizzas())
                        .withRel("recents"));

        return recentResources;
    }

    /**
     * @RequestBody indicates that the body of the request should be converted to a Pizza object and bound to the parameter.
     */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza postPizza(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> pizzaById(@PathVariable("id") Long id) {
        Optional<Pizza> optionalPizza = pizzaRepository.findById(id);
        return optionalPizza
                .map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}