package com.myexperiments.MySpringExperiments.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Getter
@Setter
public class Pizza {

    private Long id;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters")
    private String name;

    @Size(min = 1, message = "You must choose at least one ingredient")
    private List<String> ingredients;

    private Date createdAt;

}
