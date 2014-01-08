package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Viaggio_Pernottamento database table.
 * 
 */
@Embeddable
public class Viaggio_PernottamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idViaggio;

	@Column(insertable=false, updatable=false)
	private int idPernottamento;

	public Viaggio_PernottamentoPK() {
	}
	public int getIdViaggio() {
		return this.idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
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
		if (!(other instanceof Viaggio_PernottamentoPK)) {
			return false;
		}
		Viaggio_PernottamentoPK castOther = (Viaggio_PernottamentoPK)other;
		return 
			(this.idViaggio == castOther.idViaggio)
			&& (this.idPernottamento == castOther.idPernottamento);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idViaggio;
		hash = hash * prime + this.idPernottamento;
		
		return hash;
	}
}