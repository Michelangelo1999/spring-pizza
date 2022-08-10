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
		model.addAttribute("newIngrediente", new Ingrediente());
		
		return "/ingredienti/list";
	}
	

	  @PostMapping("/save")
	  public String saveIngrediente(@Valid @ModelAttribute("newIngrediente") Ingrediente formIngrediente,
	      BindingResult br, Model model) {
	    if (br.hasErrors()) {
	      // ricarico la pagina
	      model.addAttribute("ingredienti", repo.findAllByOrderByName());
	      return "ingredienti/list";

	    } else {
	      // salvo l'ingrediente
	      repo.save(formIngrediente);
	      return "redirect:/ingredienti";
	    }

	  }


}
