package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Viaggio_Volo database table.
 * 
 */
@Embeddable
public class Viaggio_VoloPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idViaggio;

	@Column(insertable=false, updatable=false)
	private int idVolo;

	public Viaggio_VoloPK() {
	}
	public int getIdViaggio() {
		return this.idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
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
		if (!(other instanceof Viaggio_VoloPK)) {
			return false;
		}
		Viaggio_VoloPK castOther = (Viaggio_VoloPK)other;
		return 
			(this.idViaggio == castOther.idViaggio)
			&& (this.idVolo == castOther.idVolo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idViaggio;
		hash = hash * prime + this.idVolo;
		
		return hash;
	}
}