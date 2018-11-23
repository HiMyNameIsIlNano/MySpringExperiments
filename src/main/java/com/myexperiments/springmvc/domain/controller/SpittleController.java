package com.myexperiments.springmvc.domain.controller;

import com.myexperiments.springmvc.domain.service.SpittleRepository;
import com.myexperiments.springmvc.domain.exceptions.SpittleNotFoundException;
import com.myexperiments.springmvc.domain.model.Spittle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    // If one prefers to work with a non-Spring type, a java.util.Map can be used instead of Model.
    @RequestMapping(path = "/spittles", method = RequestMethod.GET)
    public String spittles(Model model) {
        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }


    @RequestMapping(path = "/spittles2", method = RequestMethod.GET)
    public String spittles(Map model) {
        model.put("spittleList",
                spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    /*
    * In this case the logical view name is inferred from the request path, that is, spittles3
    * */
    @RequestMapping(path = "/spittles3", method = RequestMethod.GET)
    public List<Spittle> spittles() {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }

    /*
    * Query Parameters are always Strings
    * */
    @RequestMapping(path = "spittle-paged", method = RequestMethod.GET)
    public List<Spittle> spittles(@RequestParam(value = "max", defaultValue = "100") long max,
                                  @RequestParam(value = "count", defaultValue = "20") int count ) {
        return spittleRepository.findSpittles(max, count);
    }

    /*
     * In this case the Value of the Path Variable is explicitly set, and the spittleId is the variable id.
     * */
    @RequestMapping(path = "/spittles/{spittleId}", method = RequestMethod.GET)
    public String spittle(@PathVariable(value = "spittleId") long id, Model model) {
        Spittle spittle = spittleRepository.findOne(id);

        if(spittle == null) {
            throw new SpittleNotFoundException();
        }

        model.addAttribute("spittle", spittle);
        return "spittle";
    }

    /*
    * In this case the Value of the Path Variable is not explicitly set, but its name attribute is matching those
    * of the variable in the Path.
    * */
    @RequestMapping(path = "/spittles2/{spittleId}", method = RequestMethod.GET)
    public String spittle2(@PathVariable Long spittleId, Model model) {
        // The model key will be spittle , inferred by the type passed in to addAttribute()
        model.addAttribute(spittleRepository.findOne(spittleId));
        return "spittle";
    }

}
