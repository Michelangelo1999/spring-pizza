package jana60.model;

import org.springframework.web.multipart.MultipartFile;

public class ImageForm {

	private Integer Id;
	
	private Pizza pizza;
	
	private MultipartFile contentMultipart;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public MultipartFile getContentMultipart() {
		return contentMultipart;
	}

	public void setContentMultipart(MultipartFile contentMultipart) {
		this.contentMultipart = contentMultipart;
	}
	
	
}
