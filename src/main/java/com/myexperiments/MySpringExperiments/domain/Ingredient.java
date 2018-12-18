package com.myexperiments.MySpringExperiments.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ingredient {

    private String id;
    private String name;
    private IngredientType type;

}
