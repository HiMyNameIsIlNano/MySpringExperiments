package com.myexperiments.MySpringExperiments.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters")
    private String name;

    @ManyToMany(targetEntity=Ingredient.class)
    @Size(min = 1, message = "You must choose at least one ingredient")
    @JoinTable(name = "PIZZA_INGREDIENTS",
            joinColumns = @JoinColumn(name = "PIZZA_ID"),
            inverseJoinColumns = @JoinColumn(name = "INGREDIENT_ID")
    )
    private List<Ingredient> ingredients;

    @Column(name = "CREATED_AT")
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

}
