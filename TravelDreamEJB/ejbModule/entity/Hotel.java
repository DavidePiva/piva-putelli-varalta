package entity;

import java.util.ArrayList;

import enums.TipoCamera;


public class Hotel {
	int id;
	String nome;
	String citta;
	String indirizzo;
	String telefono;
	String descrizione;
	ArrayList<TipoCamera> camere;

	public Hotel() {
		super();
		this.id = 0;
		this.nome = null;
		this.citta = null;
		this.indirizzo = null;
		this.telefono = null;
		this.descrizione = null;
		this.camere= null;
	}
	
	public Hotel(int id, String nome, String citta, String indirizzo, String telefono, String descrizione, ArrayList<TipoCamera> camere) {
		super();
		this.id = id;
		this.nome = nome;
		this.citta = citta;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.descrizione = descrizione;
		this.camere= camere;
	}

	public ArrayList<TipoCamera> getCamere() {
		return camere;
	}

	public void setCamere(ArrayList<TipoCamera> camere) {
		this.camere = camere;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
}
