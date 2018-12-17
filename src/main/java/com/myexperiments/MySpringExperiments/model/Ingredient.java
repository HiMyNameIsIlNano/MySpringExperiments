package com.myexperiments.MySpringExperiments.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

    private String id;
    private String name;
    private IngredientType type;

}
