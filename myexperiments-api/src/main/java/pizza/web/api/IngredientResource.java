package pizza.web.api;

import org.springframework.hateoas.ResourceSupport;

import lombok.Getter;
import pizza.Ingredient;
import pizza.Ingredient.Type;

public class IngredientResource extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
