package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
