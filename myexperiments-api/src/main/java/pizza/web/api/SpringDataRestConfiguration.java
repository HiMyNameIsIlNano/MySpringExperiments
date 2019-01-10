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

    /*
    * By declaring a resource processor bean, one can add links to the list of links that Spring Data REST automatically
    * includes when calling the localhost:8080/api. This way we add a recents link to any resource of type
    * PagedResources<Resource<Pizza>> (the type returned for the /api/pizzas endpoint)
    *
    @Bean
    public ResourceProcessor<PagedResources<Resource<Pizza>>> pizzaProcessor(EntityLinks links) {
        return resource -> {
            resource.add(
                    links.linkFor(Pizza.class)
                            .slash("recent")
                            .withRel("recents"));
            return resource;
        };
    }
    */

}
