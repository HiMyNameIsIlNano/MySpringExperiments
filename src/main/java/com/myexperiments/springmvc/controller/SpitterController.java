package com.myexperiments.springmvc.controller;

import com.myexperiments.springmvc.data.SpitterRepository;
import com.myexperiments.springmvc.model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(path = "/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(path = "/register-old", method = GET)
    public String register() {
        return "registerForm";
    }

    @RequestMapping(path = "/{username}")
    public String getProfile(@PathVariable(value = "username") String user, Model model) {
        Spitter spitter = spitterRepository.findByUserName(user);
        model.addAttribute(spitter);
        return "profile";
    }

    /*
    * In addition to 'redirect:', InternalResourceViewResolver also recognizes 'forward:'
    *
    * As the validation API has been added to each field of the Spitter, it is necessary to activate
    * the Validation at the Controller Level. Adding the validation in the Spitter Class would have not
    * been enough.
    **/
    @RequestMapping(value="/register", method=POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors) {
        if(errors.hasErrors()) {
            return "registerForm";
        }

        spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value="/register", method=GET)
    public String showRegistrationForm(Model model) {
        // This tells Spring to add an attribute called 'spitter' to the model object
        model.addAttribute(new Spitter());
        return "registerForm";
    }

}
