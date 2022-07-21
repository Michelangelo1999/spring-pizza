package jana60.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jana60.model.Pizza;
import jana60.repository.PizzaRepository;

@Controller
@RequestMapping("/")
public class PizzaController {
	
	@Autowired
	PizzaRepository repo;
	
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
		return "/pizza/edit";
	}
	
	@PostMapping("/save")
	public String doCreatePizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "/pizza/edit";
		}
		repo.save(formPizza);
		return "redirect:/";
	}

}
