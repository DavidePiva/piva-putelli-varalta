package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Pacchetto_Attivita database table.
 * 
 */
@Entity
@NamedQuery(name="Pacchetto_Attivita.findAll", query="SELECT p FROM Pacchetto_Attivita p")
public class Pacchetto_Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Pacchetto_AttivitaPK id;

	//bi-directional many-to-one association to Attivita
	@ManyToOne
	@JoinColumn(name="idAttivita")
	private Attivita attivita;

	public Pacchetto_Attivita() {
	}

	public Pacchetto_AttivitaPK getId() {
		return this.id;
	}

	public void setId(Pacchetto_AttivitaPK id) {
		this.id = id;
	}

	public Attivita getAttivita() {
		return this.attivita;
	}

	public void setAttivita(Attivita attivita) {
		this.attivita = attivita;
	}

}