package com.myexperiments.springmvc.controller;

import com.myexperiments.springmvc.data.SpitterRepository;
import com.myexperiments.springmvc.exceptions.DuplicateSpitterException;
import com.myexperiments.springmvc.model.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.io.IOException;
import java.util.Base64;

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
            RedirectAttributes model,
            Errors errors) throws IOException {
        if(errors.hasErrors()) {
            return "registerForm";
        }

        spitter.setMultipartFile(profilePicture);
        spitter.setImage(Base64.getEncoder().encodeToString(profilePicture.getBytes()));
        spitterRepository.save(spitter);

        // It is dangerous to use String concatenation to perform a redirect
        //return "redirect:/spitter/" + spitter.getUsername();

        // A much better way...
        model.addAttribute("username", spitter.getUsername());
        // The Id of the User will also be shown as a Query Parameter
        model.addAttribute("id", spitter.getId());
        model.addFlashAttribute("flashAttribute", "An attribute that survived the redirect");

        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value="/register", method=GET)
    public String showRegistrationForm(Model model) {
        // This tells Spring to add an attribute called 'spitter' to the model object
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    /**
     * The exception handler can handle the exceptions from all the methods within the same Controller.
     * */
    @ExceptionHandler(value = DuplicateSpitterException.class)
    public String handleDuplicateSpitter() {
        return "error/duplicate";
    }

}
