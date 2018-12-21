package com.myexperiments.MySpringExperiments.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Component
@Data
@Validated
@ConfigurationProperties(prefix="pizza.orders")
public class OrderProperties {

    @Min(value=5, message="must be greater than 5")
    @Max(value=25, message="must be less than 25")
    private int pageSize = 20;

}
