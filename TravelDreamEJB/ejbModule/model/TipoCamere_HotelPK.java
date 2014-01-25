package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TipoCamere_Hotel database table.
 * 
 */
@Embeddable
public class TipoCamere_HotelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int idHotel;

	private String tipoCamera;

	public TipoCamere_HotelPK() {
	}
	public int getIdHotel() {
		return this.idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public String getTipoCamera() {
		return this.tipoCamera;
	}
	public void setTipoCamera(String tipoCamera) {
		this.tipoCamera = tipoCamera;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TipoCamere_HotelPK)) {
			return false;
		}
		TipoCamere_HotelPK castOther = (TipoCamere_HotelPK)other;
		return 
			(this.idHotel == castOther.idHotel)
			&& this.tipoCamera.equals(castOther.tipoCamera);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idHotel;
		hash = hash * prime + this.tipoCamera.hashCode();
		
		return hash;
	}
}