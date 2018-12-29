package pizza.web.api;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;
import pizza.Pizza;

/*
* The @Relation annotation can help break the coupling between the JSON field name and the resource type class names
* as defined in Java. By annotating PizzaResource with @Relation, you can specify how Spring HATEOAS should name the
* field in the resulting JSON.
*
* Here one specified that when a list of PizzaResource objects is used in a Resources object, it should be named pizzas.
* And a single PizzaResource object should be referred to in JSON as pizza.
*/
@Relation(value="pizza", collectionRelation="pizzas")
public class PizzaResource extends ResourceSupport {

  private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();
  
  @Getter
  private final String name;

  @Getter
  private final Date createdAt;

  @Getter
  private final List<IngredientResource> ingredients;
  
  public PizzaResource(Pizza pizza) {
    this.name = pizza.getName();
    this.createdAt = pizza.getCreatedAt();
    this.ingredients = ingredientAssembler.toResources(pizza.getIngredients());
  }
  
}
