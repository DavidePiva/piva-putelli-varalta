package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Utente_Gruppo database table.
 * 
 */
@Entity
@NamedQuery(name="Utente_Gruppo.findAll", query="SELECT u FROM Utente_Gruppo u")
public class Utente_Gruppo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Utente_GruppoPK id;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="email")
	private Utente utente;

	public Utente_Gruppo() {
	}

	public Utente_GruppoPK getId() {
		return this.id;
	}

	public void setId(Utente_GruppoPK id) {
		this.id = id;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}