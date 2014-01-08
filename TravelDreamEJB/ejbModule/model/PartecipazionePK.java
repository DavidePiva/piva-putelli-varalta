package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Partecipazione database table.
 * 
 */
@Embeddable
public class PartecipazionePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idViaggio;

	@Column(insertable=false, updatable=false)
	private String emailPartecipante;

	public PartecipazionePK() {
	}
	public int getIdViaggio() {
		return this.idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
	}
	public String getEmailPartecipante() {
		return this.emailPartecipante;
	}
	public void setEmailPartecipante(String emailPartecipante) {
		this.emailPartecipante = emailPartecipante;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PartecipazionePK)) {
			return false;
		}
		PartecipazionePK castOther = (PartecipazionePK)other;
		return 
			(this.idViaggio == castOther.idViaggio)
			&& this.emailPartecipante.equals(castOther.emailPartecipante);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idViaggio;
		hash = hash * prime + this.emailPartecipante.hashCode();
		
		return hash;
	}
}