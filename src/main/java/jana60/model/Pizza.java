package jana60.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity //devo mettere solo questa quando la tabella a cui appoggiarmi deve essere creata e non la tengo già
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
//	@UniqueElements(message="le pizze devono avere nomi diversi")
	@NotEmpty(message="la pizza deve avere un nome")
	@Column(nullable = false, unique=true)
	private String name;
	
	@Lob
	@NotNull(message="Aggiungi una descrizione")
	private String description;
	
	@NotNull(message="Devi scrivere il prezzo")
	@Min(value = 4, message = "Il prezzo di una pizza deve essere almeno €4.00")
	@Column(nullable = false)
	private BigDecimal price;
	
	@ManyToMany
	@JsonBackReference
	@JoinTable(name="", joinColumns = {}, inverseJoinColumns = {})
	List<Ingrediente> ingredienti;

	@OneToOne
	private Ordinazione ordinazione;
	
	//getters and setters
	
	public Ordinazione getOrdinazione() {
		return ordinazione;
	}

	public void setOrdinazione(Ordinazione ordinazione) {
		this.ordinazione = ordinazione;
	}

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

	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
	
	
	
	
	
	

}
