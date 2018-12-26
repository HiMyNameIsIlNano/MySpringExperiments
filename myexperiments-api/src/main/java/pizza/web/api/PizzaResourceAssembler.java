package pizza.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import pizza.Pizza;

public class PizzaResourceAssembler
       extends ResourceAssemblerSupport<Pizza, PizzaResource> {

  public PizzaResourceAssembler() {
    super(DesignPizzaController.class, PizzaResource.class);
  }
  
  @Override
  protected PizzaResource instantiateResource(Pizza pizza) {
    return new PizzaResource(pizza);
  }

  @Override
  public PizzaResource toResource(Pizza pizza) {
    return createResourceWithId(pizza.getId(), pizza);
  }

}
