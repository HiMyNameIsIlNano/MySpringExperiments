package pizza;

import lombok.Data;

@Data
public class Ingredient {

  private final String name;
  private final Type type;
  
  public static enum Type {
    DOUGH, TOPPINGS, CHEESE, SAUCE
  }
  
}
