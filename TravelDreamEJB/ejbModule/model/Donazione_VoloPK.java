package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Donazione_Volo database table.
 * 
 */
@Embeddable
public class Donazione_VoloPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String emailDonatore;

	@Column(insertable=false, updatable=false)
	private int idVolo;

	public Donazione_VoloPK() {
	}
	public String getEmailDonatore() {
		return this.emailDonatore;
	}
	public void setEmailDonatore(String emailDonatore) {
		this.emailDonatore = emailDonatore;
	}
	public int getIdVolo() {
		return this.idVolo;
	}
	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Donazione_VoloPK)) {
			return false;
		}
		Donazione_VoloPK castOther = (Donazione_VoloPK)other;
		return 
			this.emailDonatore.equals(castOther.emailDonatore)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.emailDonatore.hashCode();
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}