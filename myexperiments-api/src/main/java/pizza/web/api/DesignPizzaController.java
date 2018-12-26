//tag::recents[]
package pizza.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//end::recents[]
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//tag::recents[]
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pizza.Pizza;
import pizza.data.PizzaRepository;

@RestController
@RequestMapping(path="/design",                      // <1>
                produces="application/json")
@CrossOrigin(origins="*")        // <2>
public class DesignPizzaController {

  private PizzaRepository pizzaRepository;
  
  @Autowired
  EntityLinks entityLinks;

  public DesignPizzaController(PizzaRepository pizzaRepository) {
    this.pizzaRepository = pizzaRepository;
  }

  @GetMapping("/recent")
  public Iterable<Pizza> recentPizzas() {                 //<3>
    PageRequest page = PageRequest.of(
            0, 12, Sort.by("createdAt").descending());
    return pizzaRepository.findAll(page).getContent();
  }
  //end::recents[]

//  @GetMapping("/recenth")
//  public Resources<PizzaResource> recentPizzasH() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Pizza> pizzas = pizzaRepository.findAll(page).getContent();
//    
//    List<PizzaResource> pizzaResources =
//        new PizzaResourceAssembler().toResources(pizzas);
//    Resources<PizzaResource> recentResources =
//        new Resources<PizzaResource>(pizzaResources);
//    recentResources.add(
//        linkTo(methodOn(DesignPizzaController.class).recentPizzas())
//        .withRel("recents"));
//    return recentResources;
//  }

  
  
//ControllerLinkBuilder.linkTo(DesignPizzaController.class)
//.slash("recent")
//.withRel("recents"));

  
  
  
//  @GetMapping("/recenth")
//  public Resources<PizzaResource> recenthPizzas() {
//    PageRequest page = PageRequest.of(
//            0, 12, Sort.by("createdAt").descending());
//    List<Pizza> pizzas = pizzaRepository.findAll(page).getContent();
//
//    List<PizzaResource> pizzaResources = new PizzaResourceAssembler().toResources(pizzas);
//    
//    Resources<PizzaResource> pizzasResources = new Resources<>(pizzaResources);
////    Link recentsLink = ControllerLinkBuilder
////        .linkTo(DesignPizzaController.class)
////        .slash("recent")
////        .withRel("recents");
//
//    Link recentsLink = 
//        linkTo(methodOn(DesignPizzaController.class).recentPizzas())
//        .withRel("recents");
//    pizzasResources.add(recentsLink);
//    return pizzasResources;
//  }
  
  //tag::postPizza[]
  @PostMapping(consumes="application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Pizza postPizza(@RequestBody Pizza pizza) {
    return pizzaRepository.save(pizza);
  }
  //end::postPizza[]
  
  
  @GetMapping("/{id}")
  public Pizza pizzaById(@PathVariable("id") Long id) {
    Optional<Pizza> optPizza = pizzaRepository.findById(id);
    if (optPizza.isPresent()) {
      return optPizza.get();
    }
    return null;
  }
  
//  @GetMapping("/{id}")
//  public ResponseEntity<Pizza> pizzaById(@PathVariable("id") Long id) {
//    Optional<Pizza> optPizza = pizzaRepository.findById(id);
//    if (optPizza.isPresent()) {
//      return new ResponseEntity<>(optPizza.get(), HttpStatus.OK);
//    }
//    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//  }

  
//tag::recents[]
}
//end::recents[]

