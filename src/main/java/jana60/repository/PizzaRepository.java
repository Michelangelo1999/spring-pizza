package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

	List<Pizza> findAllByOrderByPriceAsc();
	
	List<Pizza> findByNameContainsIgnoreCase(String query);

}