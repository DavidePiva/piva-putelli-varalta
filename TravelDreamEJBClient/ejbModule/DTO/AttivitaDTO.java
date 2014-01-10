package DTO;

import java.util.GregorianCalendar;

public class AttivitaDTO {
	
	int id;
	GregorianCalendar inizio;
	String titolo;
	String descrizione;
	String citta;
	float prezzo;
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
	public GregorianCalendar getInizio() {
		return inizio;
	}
	public void setInizio(GregorianCalendar inizio) {
		this.inizio = inizio;
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
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
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
	
	
}
