package entity;

import java.util.GregorianCalendar;

public class Volo {
	int id;
	Aeroporto aeroportoPartenza;
	Aeroporto aeroportoArrivo;
	float prezzo;
	Compagnia compagnia;
	//Contengono sia l'ora che la data
	GregorianCalendar partenza;
	GregorianCalendar arrivo;
	
	public Volo() {
		super();
		this.id = 0;
		this.aeroportoPartenza = null;
		this.aeroportoArrivo = null;
		this.prezzo = 0;
		this.compagnia = null;
		this.partenza = null;
		this.arrivo = null;
	}

	public Volo(int id, Aeroporto aeroportoPartenza, Aeroporto aeroportoArrivo,
			float prezzo, Compagnia compagnia, int anno, int mese, int giorno, 
			int oraPartenza, int minutoPartenza, int secondoPartenza,int oraArrivo, 
			int minutoArrivo, int secondoArrivo ) {
		
		super();
		this.id = id;
		this.aeroportoPartenza = aeroportoPartenza;
		this.aeroportoArrivo = aeroportoArrivo;
		this.prezzo = prezzo;
		this.compagnia = compagnia;
		this.partenza = new GregorianCalendar(anno,mese,giorno,oraPartenza,minutoPartenza,secondoPartenza);
		this.arrivo = new GregorianCalendar(anno,mese,giorno,oraArrivo,minutoArrivo,secondoArrivo);
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aeroporto getAeroportoPartenza() {
		return aeroportoPartenza;
	}

	public void setAeroportoPartenza(Aeroporto aeroportoPartenza) {
		this.aeroportoPartenza = aeroportoPartenza;
	}

	public Aeroporto getAeroportoArrivo() {
		return aeroportoArrivo;
	}

	public void setAeroportoArrivo(Aeroporto aeroportoArrivo) {
		this.aeroportoArrivo = aeroportoArrivo;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Compagnia getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(Compagnia compagnia) {
		this.compagnia = compagnia;
	}

	public GregorianCalendar getPartenza() {
		return partenza;
	}

	public void setPartenza(GregorianCalendar partenza) {
		this.partenza = partenza;
	}



	public GregorianCalendar getArrivo() {
		return arrivo;
	}

	public void setArrivo(GregorianCalendar arrivo) {
		this.arrivo = arrivo;
	}
	
	
}
