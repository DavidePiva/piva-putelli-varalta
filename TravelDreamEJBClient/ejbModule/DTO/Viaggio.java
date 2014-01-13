package DTO;

import java.math.BigDecimal;

public class Viaggio {
	
	private int idViaggio;

	private String citta;

	private int numeroPersone;

	private byte pagato;

	private BigDecimal prezzo;
	
	private int idVoloAndata;
	private int idVoloRitorno;
	private int idPernottamento;
	private int idTitolare;
	private int idPacchettoPadre;
	public int getIdViaggio() {
		return idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public int getNumeroPersone() {
		return numeroPersone;
	}
	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}
	public byte getPagato() {
		return pagato;
	}
	public void setPagato(byte pagato) {
		this.pagato = pagato;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public int getIdVoloAndata() {
		return idVoloAndata;
	}
	public void setIdVoloAndata(int idVoloAndata) {
		this.idVoloAndata = idVoloAndata;
	}
	public int getIdVoloRitorno() {
		return idVoloRitorno;
	}
	public void setIdVoloRitorno(int idVoloRitorno) {
		this.idVoloRitorno = idVoloRitorno;
	}
	public int getIdPernottamento() {
		return idPernottamento;
	}
	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}
	public int getIdTitolare() {
		return idTitolare;
	}
	public void setIdTitolare(int idTitolare) {
		this.idTitolare = idTitolare;
	}
	public int getIdPacchettoPadre() {
		return idPacchettoPadre;
	}
	public void setIdPacchettoPadre(int idPacchettoPadre) {
		this.idPacchettoPadre = idPacchettoPadre;
	}
	
}
