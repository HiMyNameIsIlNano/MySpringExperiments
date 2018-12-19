package com.myexperiments.MySpringExperiments.domain;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Ingredient {

    @Id
    private final String id;
    private final String name;

    @Enumerated(EnumType.STRING)
    private final IngredientType type;

}
