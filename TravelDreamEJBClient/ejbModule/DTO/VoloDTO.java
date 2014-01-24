package DTO;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class VoloDTO {

	int idVolo;
	int idAeroportoPartenza;
	int idAeroportoArrivo;
	BigDecimal prezzo;
	int idCompagnia;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Time oraArrivo;
	private Time oraPartenza;
	public int getIdVolo() {
		return idVolo;
	}
	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}
	public int getIdAeroportoPartenza() {
		return idAeroportoPartenza;
	}
	public void setIdAeroportoPartenza(int idAeroportoPartenza) {
		this.idAeroportoPartenza = idAeroportoPartenza;
	}
	public int getIdAeroportoArrivo() {
		return idAeroportoArrivo;
	}
	public void setIdAeroportoArrivo(int idAeroportoArrivo) {
		this.idAeroportoArrivo = idAeroportoArrivo;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public int getIdCompagnia() {
		return idCompagnia;
	}
	public void setIdCompagnia(int idCompagnia) {
		this.idCompagnia = idCompagnia;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOraArrivo() {
		return oraArrivo;
	}
	public void setOraArrivo(Time oraArrivo) {
		this.oraArrivo = oraArrivo;
	}
	public Time getOraPartenza() {
		return oraPartenza;
	}
	public void setOraPartenza(Time oraPartenza) {
		this.oraPartenza = oraPartenza;
	}
	
	public String getDataFormattata(){
		return data.toString().substring(8, 10)+"/"+data.toString().substring(4, 7)+"/"+(data.getYear()+1900)+" ore "+oraPartenza.getHours()+":"+(oraPartenza.getMinutes()<10 ? "0" : "")+oraPartenza.getMinutes();
	}
}
