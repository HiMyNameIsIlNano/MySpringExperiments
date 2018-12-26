package pizza.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import pizza.Pizza;


public interface PizzaRepository
         extends PagingAndSortingRepository<Pizza, Long> {

}
