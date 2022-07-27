package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Ingrediente;
import jana60.model.Pizza;

public interface IngredientiRepository extends CrudRepository<Ingrediente, Integer> {

	List<Ingrediente> findAllByOrderByName();

}
