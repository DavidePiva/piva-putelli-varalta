package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Donazione_Attivita database table.
 * 
 */
@Embeddable
public class Donazione_AttivitaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idAttivita;

	@Column(insertable=false, updatable=false)
	private String emailDonatore;

	public Donazione_AttivitaPK() {
	}
	public int getIdAttivita() {
		return this.idAttivita;
	}
	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}
	public String getEmailDonatore() {
		return this.emailDonatore;
	}
	public void setEmailDonatore(String emailDonatore) {
		this.emailDonatore = emailDonatore;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Donazione_AttivitaPK)) {
			return false;
		}
		Donazione_AttivitaPK castOther = (Donazione_AttivitaPK)other;
		return 
			(this.idAttivita == castOther.idAttivita)
			&& this.emailDonatore.equals(castOther.emailDonatore);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idAttivita;
		hash = hash * prime + this.emailDonatore.hashCode();
		
		return hash;
	}
}