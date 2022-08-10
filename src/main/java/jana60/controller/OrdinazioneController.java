package jana60.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Ordinazione;
import jana60.model.Pizza;
import jana60.repository.OrdinazioneRepository;
import jana60.repository.PizzaRepository;
import jana60.repository.UserRepository;

@Controller
@RequestMapping("/ordinazione")
public class OrdinazioneController {
	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	@Autowired
	private OrdinazioneRepository ordinazioneRepo;
	
	@Autowired
	private UserRepository utenteRepo;
	
	//per l'ordinazione
		@GetMapping("/{pizzaId}")
		public String orderPizza(@PathVariable("pizzaId") Integer pizzaId, Model model) {
			Ordinazione formOrdinazione = new Ordinazione();
			Pizza pizzaOrdinata = pizzaRepo.findById(pizzaId).get();
			
			formOrdinazione.setPizza(pizzaOrdinata);
			//formOrdinazione.setPizzaId(pizzaId);
			formOrdinazione.setDataOrdinazione(LocalDate.now());
			
			model.addAttribute("ordinazione", formOrdinazione);
			
			model.addAttribute("userList", utenteRepo.findAll());
			
			return "/ordinazione/edit";
		}
		
	@PostMapping("/save")
	public String doSave(@Valid @ModelAttribute("ordinazione") Ordinazione formOrdinazione, BindingResult br, Model model) {
		
		//se ho errori torno alla form
		if(br.hasErrors()) {
			model.addAttribute("userList", utenteRepo.findAll()); 
			return "/ordinazione/edit";
		}else {      //se non ho errori ìsalvo l'ordinazione
			ordinazioneRepo.save(formOrdinazione);
			return "redirect:/ordinazione/edit/" + formOrdinazione.getPizzaId();
		}
	}
	
	
	
	

}
