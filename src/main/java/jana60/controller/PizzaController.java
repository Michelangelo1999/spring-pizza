package jana60.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jana60.model.Ordinazione;
import jana60.model.Pizza;
import jana60.repository.IngredientiRepository;
import jana60.repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	PizzaRepository repo;
	
	@Autowired
	IngredientiRepository ingredientiRepo;
	
	@GetMapping
	public String menuPizze(Model model) {
		
		List<Pizza> menu = (List<Pizza>)repo.findAllByOrderByPriceAsc();
		
		model.addAttribute("menu", menu);
		
		return "/pizza/menu";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name="query") String query, Model model) {
		
		List<Pizza> pizzaListQuery = (List<Pizza>)repo.findByNameContainsIgnoreCase(query);
		model.addAttribute("menu", pizzaListQuery);
		System.out.println(pizzaListQuery);
		return "/pizza/menu";
	}
	
	@GetMapping("/add")
	public String pizzaForm(Model model) {
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredientiRepo.findAllByOrderByName());
		return "/pizza/edit";
	}
	
	@PostMapping("/save")
	public String doCreatePizza(@Validated @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
		Boolean validateName = true;
		if(formPizza.getId() != null) {  //cioè se la pizza non è muova ma arriva da una modifica, quindi ha un id
			Pizza pizzaBeforeUpdate = repo.findById(formPizza.getId()).get();
			if(pizzaBeforeUpdate.getName().equalsIgnoreCase(formPizza.getName())) {
				validateName = false;
			}
		}
		
		if(bindingResult.hasErrors() || repo.countByName(formPizza.getName()) > 0 && validateName) {
			bindingResult.addError(new FieldError("Pizza", "name", "Hai già una pizza con questo nome"));
			return "/pizza/edit";
		}
		repo.save(formPizza);
		return "redirect:/";
	}
	
	//controller per cancellare
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer pizzaId, RedirectAttributes redAtt) {
		Optional<Pizza> result = repo.findById(pizzaId);
		if(result.isPresent()) {
			repo.delete(result.get());
			redAtt.addFlashAttribute("successSms", "La pizza " + result.get().getName() + " è stata eliminata dal menu!");
			return "redirect:/";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la pizza con id " + pizzaId + " non è presente nel menu");
		}
	}
	
	@GetMapping("edit/{id}")
	public String edit(@PathVariable("id") Integer pizzaId, Model model) {    //devo passare al form un apizza descritta, con l'id in rif al link
		Optional<Pizza> result = repo.findById(pizzaId);
		if(result.isPresent()) {
			model.addAttribute("pizza", result.get());
			model.addAttribute("ingredienti", ingredientiRepo.findAllByOrderByName());
			return "/pizza/edit";
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "la pizza con id " + pizzaId + " non è presente nel menu");
		}
	}
	
	//per il detail
	@GetMapping("/detail/{id}")
	public String pizzaDetail(@PathVariable("id") Integer pizzaId, Model model) {
		
		Optional<Pizza> pizza = repo.findById(pizzaId);
		if(pizza.isPresent()) {
			model.addAttribute("pizza", pizza.get());
			
			return "/pizza/detail";
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + pizzaId + " is not present");
		}
		
		
	}
	
	
	
	
	
	
	
	

}
