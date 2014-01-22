package DTO;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class AttivitaDTO {
	
	int id;
	@Temporal(TemporalType.DATE)
	private Date data;
	private Time ora;
	String titolo;
	String descrizione;
	String citta;
	BigDecimal prezzo;
	String foto1;
	String foto2;
	String foto3;
	boolean selezionabile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public BigDecimal getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}
	public String getFoto1() {
		return foto1;
	}
	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}
	public String getFoto2() {
		return foto2;
	}
	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}
	public String getFoto3() {
		return foto3;
	}
	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}
	public boolean isSelezionabile() {
		return selezionabile;
	}
	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}
	
	public String toString(){
		return titolo;
	}
	
	
}
