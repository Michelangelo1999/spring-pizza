package jana60.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Ordinazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate dataOrdinazione;
	
	private LocalDate dataConsegna;
	
	@Lob
	private String note;
	
	@ManyToOne
	private Utente utente;
	
	@OneToOne(mappedBy = "ordinazione")
	private Pizza pizza;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataOrdinazione() {
		return dataOrdinazione;
	}

	public void setDataOrdinazione(LocalDate dataOrdinazione) {
		this.dataOrdinazione = dataOrdinazione;
	}

	public LocalDate getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(LocalDate dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	public Integer getPizzaId() {
		return this.pizza.getId();
	}
	
	public void setPizzaId(Integer id) {
		this.pizza.setId(id);
	}

	
	

}
