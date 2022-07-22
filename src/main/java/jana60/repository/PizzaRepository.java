package jana60.repository;
import java.util.List;
import jana60.model.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Integer> {

	List<Pizza> findAllByOrderByPriceAsc();
	
	List<Pizza> findByNameContainsIgnoreCase(String query);
	
	public Integer countByName(String name);

}
