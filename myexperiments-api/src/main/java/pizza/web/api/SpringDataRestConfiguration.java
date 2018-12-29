package pizza.web.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import pizza.Pizza;

@Configuration
public class SpringDataRestConfiguration {

    @Bean
    public ResourceProcessor<PagedResources<Resource<Pizza>>>
    pizzaProcessor(EntityLinks links) {

        return new ResourceProcessor<PagedResources<Resource<Pizza>>>() {
            @Override
            public PagedResources<Resource<Pizza>> process(PagedResources<Resource<Pizza>> resource) {
                resource.add(
                        links.linkFor(Pizza.class)
                                .slash("recent")
                                .withRel("recents"));
                return resource;
            }
        };
    }

}
