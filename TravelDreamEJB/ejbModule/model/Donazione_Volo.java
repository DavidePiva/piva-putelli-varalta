package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Donazione_Volo database table.
 * 
 */
@Entity
@Table(name="Donazione_Volo")
@NamedQuery(name="Donazione_Volo.findAll", query="SELECT d FROM Donazione_Volo d")
public class Donazione_Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Donazione_VoloPK id;

	private boolean donato;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="emailDonatore")
	private Utente utente;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="idVolo")
	private Volo volo;

	public Donazione_Volo() {
	}

	public Donazione_VoloPK getId() {
		return this.id;
	}

	public void setId(Donazione_VoloPK id) {
		this.id = id;
	}

	public boolean getDonato() {
		return this.donato;
	}

	public void setDonato(boolean donato) {
		this.donato = donato;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Volo getVolo() {
		return this.volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

}