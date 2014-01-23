package DTO;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class TipoCamere_HotelDTO {
	
	private int id;

	private BigDecimal prezzo;
	
	TipoCamera tipo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public TipoCamera getTipo() {
		return tipo;
	}
	public void setTipo(TipoCamera a) {
		this.tipo = a;
	}
	
	
}
