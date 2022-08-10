package jana60.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jana60.model.Image;
import jana60.model.Pizza;

public interface ImageRepository extends CrudRepository<Image, Integer>{
	
	public List<Image> findByPizza(Pizza pizza);

}
