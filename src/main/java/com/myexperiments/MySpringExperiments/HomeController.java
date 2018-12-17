package com.myexperiments.MySpringExperiments;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * By default with SpringBoot the template name is derived from the logical view name by prefixing it with
     * /templates/ and postfixing it with .html
     * */
    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

}
