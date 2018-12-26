package pizza.web.api;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;
import pizza.Pizza;

@Relation(value="pizza", collectionRelation="pizzas")
public class PizzaResource extends ResourceSupport {

  private static final IngredientResourceAssembler 
            ingredientAssembler = new IngredientResourceAssembler();
  
  @Getter
  private final String name;

  @Getter
  private final Date createdAt;

  @Getter
  private final List<IngredientResource> ingredients;
  
  public PizzaResource(Pizza pizza) {
    this.name = pizza.getName();
    this.createdAt = pizza.getCreatedAt();
    this.ingredients = 
        ingredientAssembler.toResources(pizza.getIngredients());
  }
  
}
