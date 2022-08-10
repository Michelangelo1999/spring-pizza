package jana60.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	private ImageService service;
	
	/* creo la view con la lista delle immagini collegate a una pizza e la form per aggiungerne una */
	
	@GetMapping("/{pizzaId}")
	public String pizzaImage(@PathVariable("pizzaId") Integer pizzaId, Model model) {
		List<Image> images = service.getImageByPizzaId(pizzaId);
		
		ImageForm imageForm = service.createImageForm(pizzaId); //chiedo al service di istanziare una imgform istanziata con quaella pizza
		
		model.addAttribute("imageList", images);
		model.addAttribute("imageForm", imageForm);
		return "/images/list";
	}
	
	//controller che riceve il post della form e salva sul db l'immagine
	
	@PostMapping("/save")
	public String saveImage(@ModelAttribute("imageForm") ImageForm imageForm) {
		
		//devo convertire il content di image (lo fa il service con un metodo apposito) form e salvare l'immagine
		try {
			Image savedImage = service.createImage(imageForm);
			return "redirect:/image/" + savedImage.getPizza().getId();
		} catch(IOException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to save image");
		}
	}
	
	@RequestMapping(value="/{imageId}/content", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageContent(@PathVariable("imageId") Integer imageId) {
		//recupero il content del database
    	byte[] content = service.getImageContent(imageId);
		// preparo gli headers della response con il tipo di contenuto
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.IMAGE_JPEG);
		//ritorno i lcontenuto di headers e lo stato http -> l'headers è una parte della risposta, guarda teoria e ispeziona elemento
    	return new ResponseEntity<byte[]>(content, headers, HttpStatus.OK);
	}
}
