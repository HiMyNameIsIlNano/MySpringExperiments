package com.myexperiments.MySpringExperiments.web.controller;

import com.myexperiments.MySpringExperiments.web.domain.Ingredient;
import com.myexperiments.MySpringExperiments.web.domain.IngredientType;
import com.myexperiments.MySpringExperiments.web.domain.Order;
import com.myexperiments.MySpringExperiments.web.domain.Pizza;
import com.myexperiments.MySpringExperiments.web.repository.IngredientRepository;
import com.myexperiments.MySpringExperiments.web.repository.PizzaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
* Generally, it is preferable to only use @RequestMapping at the class level to specify the base path and use the more
* specific @GetMapping, @PostMapping, and so on, on each of the handler methods.
* */
@Slf4j
@Controller
// The order Object in the Model must outlive the redirect.
@SessionAttributes("order")
@RequestMapping("/design")
public class DesignPizzaController {

    private IngredientRepository ingredientRepository;
    private PizzaRepository pizzaRepository;

    /**
     * The @ModelAttribute annotation on order() ensures that an Order object will be created in the Model.
     */
    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    /**
     * The @ModelAttribute annotation on pizza() ensures that a Pizza object will be created in the Model.
     */
    @ModelAttribute(name = "designedPizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepository, PizzaRepository pizzaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredientList::add);

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

    /**
     * The Order parameter is annotated with @ModelAttribute to indicate that its value should come from the model and
     * that Spring MVC should not attempt to bind request parameters to it.
     */
    @PostMapping
    public String processDesign(@Valid Pizza designedPizza, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }

        log.info("Processing designed Pizza: " + designedPizza);
        Pizza savedPizza = pizzaRepository.save(designedPizza);
        order.addDesignedPizza(savedPizza);

        return "redirect:/orders/current";
    }

}
