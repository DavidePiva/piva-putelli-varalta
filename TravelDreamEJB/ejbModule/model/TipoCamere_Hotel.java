package model;

import java.io.Serializable;

import javax.persistence.*;

import DTO.HotelDTO;
import DTO.TipoCamere_HotelDTO;

import java.math.BigDecimal;


/**
 * The persistent class for the TipoCamere_Hotel database table.
 * 
 */
@Entity
@Table(name="TipoCamere_Hotel")
@NamedQuery(name="TipoCamere_Hotel.findAll", query="SELECT t FROM TipoCamere_Hotel t")
public class TipoCamere_Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TipoCamere_HotelPK id;

	private BigDecimal prezzo;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="idHotel")
	private Hotel hotel;

	public TipoCamere_Hotel() {
	}
	public TipoCamere_Hotel(TipoCamere_HotelDTO t,HotelDTO h){
		
		this.prezzo=t.getPrezzo();
		this.id=new TipoCamere_HotelPK();
		this.id.setIdHotel(t.getId());
		this.id.setTipoCamera(t.getTipo().getString(t.getTipo()));
		this.hotel=new Hotel(h);

	}
	public TipoCamere_HotelPK getId() {
		return this.id;
	}

	public void setId(TipoCamere_HotelPK id) {
		this.id = id;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

}