package pizza.web.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import pizza.Pizza;
import pizza.data.PizzaRepository;

@RepositoryRestController
public class RecentPizzasController {

  private PizzaRepository pizzaRepository;

  public RecentPizzasController(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  @GetMapping(path="/pizzas/recent", produces="application/hal+json")
  public ResponseEntity<Resources<PizzaResource>> recentPizzas() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    List<Pizza> pizzas = pizzaRepository.findAll(page).getContent();

    List<PizzaResource> pizzaResources = new PizzaResourceAssembler().toResources(pizzas);
    Resources<PizzaResource> recentResources = new Resources<>(pizzaResources);
    
    recentResources.add(
        linkTo(methodOn(RecentPizzasController.class).recentPizzas())
            .withRel("recents"));

    return new ResponseEntity<>(recentResources, HttpStatus.OK);
  }

}
