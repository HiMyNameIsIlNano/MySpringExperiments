package pizza;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Pizza {

  private String name;
  
  private Date createdAt;

  private List<Ingredient> ingredients;

}
