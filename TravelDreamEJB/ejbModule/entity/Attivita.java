package entity;

import java.util.GregorianCalendar;

public class Attivita {
	
	int id;
	GregorianCalendar inizio;
	String titolo;
	String descrizione;
	String citta;
	float prezzo;
	
	public Attivita() {
		super();
		this.id = 0;
		this.inizio = null;
		this.titolo = null;
		this.descrizione = null;
		this.citta = null;
		this.prezzo = 0;
	}
	
	public Attivita(int id, int giorno, int mese, int anno, int ora, int minuto, int secondo, String titolo, String descrizione, String citta, float prezzo) {
		super();
		this.id = id;
		this.inizio = new GregorianCalendar(anno,mese,giorno,ora,minuto,secondo);
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.citta = citta;
		this.prezzo = prezzo;
	}
	
	public Attivita(int id, String titolo, float prezzo){
		this.id = id;
		this.titolo = titolo;
		this.prezzo = prezzo;
	}

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
	
}
