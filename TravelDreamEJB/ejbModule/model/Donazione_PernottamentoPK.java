package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Donazione_Pernottamento database table.
 * 
 */
@Embeddable
public class Donazione_PernottamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String emailDonatore;

	@Column(insertable=false, updatable=false)
	private int idPernottamento;

	public Donazione_PernottamentoPK() {
	}
	public String getEmailDonatore() {
		return this.emailDonatore;
	}
	public void setEmailDonatore(String emailDonatore) {
		this.emailDonatore = emailDonatore;
	}
	public int getIdPernottamento() {
		return this.idPernottamento;
	}
	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Donazione_PernottamentoPK)) {
			return false;
		}
		Donazione_PernottamentoPK castOther = (Donazione_PernottamentoPK)other;
		return 
			this.emailDonatore.equals(castOther.emailDonatore)
			&& (this.idPernottamento == castOther.idPernottamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.emailDonatore.hashCode();
		hash = hash * prime + this.idPernottamento;
		
		return hash;
	}
}