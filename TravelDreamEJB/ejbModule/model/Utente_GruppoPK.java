package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Utente_Gruppo database table.
 * 
 */
@Embeddable
public class Utente_GruppoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String email;

	@Column(insertable=false, updatable=false)
	private String idGruppo;

	public Utente_GruppoPK() {
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdGruppo() {
		return this.idGruppo;
	}
	public void setIdGruppo(String idGruppo) {
		this.idGruppo = idGruppo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Utente_GruppoPK)) {
			return false;
		}
		Utente_GruppoPK castOther = (Utente_GruppoPK)other;
		return 
			this.email.equals(castOther.email)
			&& this.idGruppo.equals(castOther.idGruppo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.email.hashCode();
		hash = hash * prime + this.idGruppo.hashCode();
		
		return hash;
	}
}