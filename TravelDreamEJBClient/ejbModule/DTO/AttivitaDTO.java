package DTO;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;



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
	
	public String getOraFormattata(){
		return (ora.getHours() < 10 ? "0" : "")+ora.getHours()+":"+(ora.getMinutes() < 10 ? "0" : "")+ora.getMinutes();
	}
	
	public String getDataFormattata(){
		String giornoSettimana, meseStringa;
		switch(data.getDay()){
		case 0:
			giornoSettimana="Domenica";
			break;
		case 1:
			giornoSettimana="Lunedì";
			break;
		case 2:
			giornoSettimana="Martedì";
			break;
		case 3:
			giornoSettimana="Mercoledì";
			break;
		case 4:
			giornoSettimana="Giovedì";
			break;
		case 5:
			giornoSettimana="Venerdì";
			break;
		case 6:
			giornoSettimana="Sabato";
			break;
		default:
			giornoSettimana="";
		}
		
		switch(data.getMonth()){
		case 0:
			meseStringa="Gennaio";
			break;
		case 1:
			meseStringa="Febbraio";
			break;
		case 2:
			meseStringa="Marzo";
			break;
		case 3:
			meseStringa="Aprile";
			break;
		case 4:
			meseStringa="Maggio";
			break;
		case 5:
			meseStringa="Giugno";
			break;
		case 6:
			meseStringa="Luglio";
			break;
		case 7:
			meseStringa="Agosto";
			break;
		case 8:
			meseStringa="Settembre";
			break;
		case 9:
			meseStringa="Ottobre";
			break;
		case 10:
			meseStringa="Novembre";
			break;
		case 11:
			meseStringa="Dicembre";
			break;
		default:
			meseStringa="";
		}
		
		return giornoSettimana+" "+data.getDate()+" "+meseStringa+" "+(data.getYear()+1900);
	}
	
}
