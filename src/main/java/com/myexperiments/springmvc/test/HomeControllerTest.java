package com.myexperiments.springmvc.test;

import com.myexperiments.springmvc.controller.HomeController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class HomeControllerTest {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void testHomePage() {
        HomeController homeController = new HomeController();
        MockMvc mockMvc = standaloneSetup(homeController).build();

        try {
            mockMvc.perform(get("/"))
                    .andExpect(view().name("home"));

            mockMvc.perform(get("/homepage"))
                    .andExpect(view().name("home"));

        } catch (Exception e) {
            logger.info("An Error occurred while testing the Home Controller");
        }
    }

}
