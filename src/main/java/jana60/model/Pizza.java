package jana60.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity //devo mettere solo questa quando la tabella a cui appoggiarmi deve essere creata e non la tengo già
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@UniqueElements(message="le pizze devono avere nomi diversi")
	@NotEmpty(message="la pizza deve avere un nome")
	@Column(nullable = false)
	private String name;
	
	@Lob
	@NotNull(message="Aggiungi una descrizione")
	private String description;
	
	@NotNull(message="Devi scrivere il prezzo")
	@Min(value = 4, message = "Il prezzo di una pizza deve essere almeno €4.00")
	@Column(nullable = false)
	private BigDecimal price;

	
	//getters and setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
