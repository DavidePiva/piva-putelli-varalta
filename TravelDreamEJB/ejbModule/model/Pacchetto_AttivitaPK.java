package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Pacchetto_Attivita database table.
 * 
 */
@Embeddable
public class Pacchetto_AttivitaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idPacchetto;

	@Column(insertable=false, updatable=false)
	private int idAttivita;

	public Pacchetto_AttivitaPK() {
	}
	public int getIdPacchetto() {
		return this.idPacchetto;
	}
	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}
	public int getIdAttivita() {
		return this.idAttivita;
	}
	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Pacchetto_AttivitaPK)) {
			return false;
		}
		Pacchetto_AttivitaPK castOther = (Pacchetto_AttivitaPK)other;
		return 
			(this.idPacchetto == castOther.idPacchetto)
			&& (this.idAttivita == castOther.idAttivita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idPacchetto;
		hash = hash * prime + this.idAttivita;
		
		return hash;
	}
}