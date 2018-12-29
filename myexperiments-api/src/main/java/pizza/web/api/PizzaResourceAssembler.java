package pizza.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import pizza.Pizza;

public class PizzaResourceAssembler extends ResourceAssemblerSupport<Pizza, PizzaResource> {

  public PizzaResourceAssembler() {
    super(DesignPizzaController.class, PizzaResource.class);
  }

  /**
   * instantiateResource() is intended to only instantiate a Resource object.
   */
  @Override
  protected PizzaResource instantiateResource(Pizza pizza) {
    return new PizzaResource(pizza);
  }

  /**
   * Creates a TacoResource object from a Taco, and to automatically give it a self link with the URL being derived
   * from the Taco object’s id property.
   *
   * toResource() is intended not only to create the Resource object, but also to populate it with links. Under the
   * covers, toResource() will call instantiateResource() .
   */
  @Override
  public PizzaResource toResource(Pizza pizza) {
    return createResourceWithId(pizza.getId(), pizza);
  }

}
