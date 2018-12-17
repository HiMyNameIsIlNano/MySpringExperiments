package com.myexperiments.MySpringExperiments.web;

import com.myexperiments.MySpringExperiments.model.Ingredient;
import com.myexperiments.MySpringExperiments.model.IngredientType;
import com.myexperiments.MySpringExperiments.model.Pizza;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* Generally, it is preferable to only use @RequestMapping at the class level to specify the base path and use the more
* specific @GetMapping, @PostMapping, and so on, on each of the handler methods.
* */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredientList = Arrays.asList(
                Ingredient.builder().id("SOURD").name("Sourdough").type(IngredientType.DOUGH).build(),
                Ingredient.builder().id("NORMD").name("Normal Dough").type(IngredientType.DOUGH).build(),
                Ingredient.builder().id("MOZZ").name("Mozzarella Cheese").type(IngredientType.CHEESE).build(),
                Ingredient.builder().id("PRC").name("Parmesan Cheese").type(IngredientType.CHEESE).build(),
                Ingredient.builder().id("SAL").name("Spicy Salami").type(IngredientType.TOPPING).build(),
                Ingredient.builder().id("EGGP").name("Eggplant").type(IngredientType.TOPPING).build(),
                Ingredient.builder().id("CHTO").name("Cherry Tomato").type(IngredientType.TOPPING).build(),
                Ingredient.builder().id("HAM").name("Ham").type(IngredientType.TOPPING).build()
        );

        IngredientType[] types = IngredientType.values();
        for (IngredientType type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
        }
        model.addAttribute("designedPizza", new Pizza());

        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredientList, IngredientType type) {
        return ingredientList.stream()
                .filter(ingredient -> type.equals(ingredient.getType()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid Pizza designedPizza, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing designed Pizza: " + designedPizza);
        return "redirect:/orders/current";
    }

}
