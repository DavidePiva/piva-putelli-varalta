package DTO;

import java.math.BigDecimal;

public class TipoCamere_Hotel {
	
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
	public void setTipo(TipoCamera tipo) {
		this.tipo = tipo;
	}
	
	
}
