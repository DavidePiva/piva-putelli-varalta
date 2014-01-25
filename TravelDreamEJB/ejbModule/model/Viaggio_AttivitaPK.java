package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Viaggio_Attivita database table.
 * 
 */
@Embeddable
public class Viaggio_AttivitaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idViaggio;

	@Column(insertable=false, updatable=false)
	private int idAttivita;

	public Viaggio_AttivitaPK() {
	}
	public int getIdViaggio() {
		return this.idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
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
		if (!(other instanceof Viaggio_AttivitaPK)) {
			return false;
		}
		Viaggio_AttivitaPK castOther = (Viaggio_AttivitaPK)other;
		return 
			(this.idViaggio == castOther.idViaggio)
			&& (this.idAttivita == castOther.idAttivita);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idViaggio;
		hash = hash * prime + this.idAttivita;
		
		return hash;
	}
}