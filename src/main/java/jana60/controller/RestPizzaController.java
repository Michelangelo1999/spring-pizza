package jana60.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Pizza;
import jana60.repository.PizzaRepository;

@RestController   //fara la conversione java -> json
@CrossOrigin
@RequestMapping("/api/pizza")
public class RestPizzaController {
	
	@Autowired
	private PizzaRepository repo;
	
	//posso mostrarti la lista di tutte le pizze in json -> riprendo esempi su slides
	//nB che ora non devo restituire una stringa, quinid una pagina, ma una lista di pizze 
	@GetMapping
	public List<Pizza> get(){
		return (List<Pizza>) repo.findAll();
	}
	
	@GetMapping("/{id}")
	public Pizza getPizzaId(@PathVariable("id") Integer id) {
		Optional<Pizza> result = repo.findById(id);
		if(result.isPresent()) {
			return result.get();
		} else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza not found");
		}
	}

}
