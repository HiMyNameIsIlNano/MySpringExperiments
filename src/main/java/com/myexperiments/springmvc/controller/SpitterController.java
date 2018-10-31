package com.myexperiments.springmvc.controller;

import com.myexperiments.springmvc.data.SpitterRepository;
import com.myexperiments.springmvc.model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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
    * If one writes a controller handler method to accept file uploads via a Part parameter instead of a
    * MultipartFile, there is no need to configure the StandardServletMultipartResolver bean.
    *
    * StandardServletMultipartResolver is required only when one is working with MultipartFile.
    **/
    @RequestMapping(value="/register", method=POST)
    public String processRegistration(
            @RequestPart("profilePicture") MultipartFile profilePicture,
            @Valid Spitter spitter,
            Errors errors) throws IOException {
        if(errors.hasErrors()) {
            return "registerForm";
        }

        spitter.setProfilePicture(profilePicture);

        // https://www.baeldung.com/spring-file-upload
        File destination = new File("/home/user/Development/Eclipse/MySpringExperiments/uploads",
                UUID.randomUUID().toString());
        profilePicture.transferTo(destination);
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
