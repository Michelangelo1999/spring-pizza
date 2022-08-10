package jana60.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jana60.model.Image;
import jana60.model.ImageForm;
import jana60.model.Pizza;
import jana60.repository.ImageRepository;
import jana60.repository.PizzaRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository repo;
	
	@Autowired
	private PizzaRepository pizzaRepo;
	
	public List<Image> getImageByPizzaId(Integer pizzaId) {
		
		Pizza pizza = pizzaRepo.findById(pizzaId).get();
		
		return repo.findByPizza(pizza);
	}
	
	//ti passo pizza id, voglio una immagine associata a quella pizza
	public ImageForm createImageForm(Integer pizzaId) {
		Pizza pizza = pizzaRepo.findById(pizzaId).get();
		ImageForm img = new ImageForm();
		img.setPizza(pizza);   //serve a dire che nel mio imgform l'attributo pizza deve essere valorizzato dalla pizza che prendo (id)
		return img;
	}
	
	//a partire da un oiggetto di tipo imageForm, guarda su, devo creare un oggetto di tipo immagine e salvarlo sul db.
	public Image createImage(ImageForm imageForm) throws IOException{     //potrebbe andare in errore quinid lamcia eccezione
		Image imgToSave = new Image(); //immaine da salvare
		imgToSave.setPizza(imageForm.getPizza());    //inizializzo l'oggetto img vuoto con i dati di imageForm
		
		//converto il multipart in un array di byte
		if(imageForm.getContentMultipart() != null) {
			byte[] contentSerialized = imageForm.getContentMultipart().getBytes();   //metodo che converte:getBytes()
			imgToSave.setContent(contentSerialized);
		}
		//salvo img sul db e lo ritorno
		return repo.save(imgToSave);
	}
	
	//metodo che a partire dall'id dell'immagine restituisce il contenuto
	public byte[] getImageContent(Integer id) {
		Image img = repo.findById(id).get();
		return img.getContent();
	}
	
}
