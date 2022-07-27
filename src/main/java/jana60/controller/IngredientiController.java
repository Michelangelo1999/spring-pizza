package jana60.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Ingrediente;
import jana60.repository.IngredientiRepository;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {
	
	@Autowired
	IngredientiRepository repo;
	
	@GetMapping
	public String ingredienti(Model model) {
		List<Ingrediente> ingredienti = (List<Ingrediente>)repo.findAllByOrderByName();
		model.addAttribute("ingredienti", ingredienti);
		
		return "/ingredienti/list";
	}
	
	

}
