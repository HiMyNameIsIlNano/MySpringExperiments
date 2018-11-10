package com.myexperiments.springmvc.test;

import com.myexperiments.springmvc.domain.controller.SpitterController;
import com.myexperiments.springmvc.domain.service.SpitterRepository;
import com.myexperiments.springmvc.domain.model.Spitter;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class SpitterControllerTest {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Test
    public void shouldShowRegistration() {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        SpitterController spitterController = new SpitterController(mockRepository);
        MockMvc mockMvc = standaloneSetup(spitterController).build();

        try {
            mockMvc.perform(get("/spitter/register"))
                    .andExpect(view().name("registerForm"));

        } catch (Exception e) {
            logger.info("An Error occurred while testing the Spitter Controller");
        }
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        SpitterRepository mockRepository = mock(SpitterRepository.class);
        Spitter unsaved = new Spitter("jbauer","24hours", "test@test.com","Jack", "Bauer" );
        Spitter saved = new Spitter(24L, "jbauer","24hours", "test@test.com","Jack", "Bauer");
        when(mockRepository.save(unsaved)).thenReturn(saved);
        SpitterController controller = new SpitterController(mockRepository);

        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(post("/spitter/register")
                        .param("firstName", "Jack")
                        .param("lastName", "Bauer")
                        .param("email", "test@test.com")
                        .param("username", "jbauer")
                        .param("password", "24hours"))
                .andExpect(redirectedUrl("/spitter/jbauer"));
        verify(mockRepository, atLeastOnce()).save(unsaved);
    }
}
