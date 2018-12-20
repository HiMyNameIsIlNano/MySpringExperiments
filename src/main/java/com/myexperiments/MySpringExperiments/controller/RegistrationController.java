package com.myexperiments.MySpringExperiments.controller;

import com.myexperiments.MySpringExperiments.domain.RegistrationForm;
import com.myexperiments.MySpringExperiments.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserAccountRepository userAccountRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserAccountRepository userAccountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, Errors errors) {
        if (errors.hasErrors()) {
            return "registration";
        }

        if (!form.getPassword().equals(form.getConfirmedPassword())) {
            return "registration";
        }

        userAccountRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}