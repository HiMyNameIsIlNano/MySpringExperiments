package com.myexperiments.MySpringExperiments.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;

    // Must not be null and must contain at least one non-whitespace character
    @NotBlank(message = "Name must be set")
    private String name;

    @NotBlank(message = "Street must be set")
    private String street;

    @NotBlank(message = "City must be set")
    private String city;

    @NotBlank(message = "State must be set")
    private String state;

    @NotBlank(message = "Zip must be set")
    private String zip;

    @CreditCardNumber(message = "Credit Card Number must be well formed")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;

    private List<Pizza> designePizza;

    private Date placedAt;

    public void addDesignedPizza(Pizza savedPizza) {
        this.designePizza.add(savedPizza);
    }
}
