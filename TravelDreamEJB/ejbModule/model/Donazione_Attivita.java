package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Donazione_Attivita database table.
 * 
 */
@Entity
@NamedQuery(name="Donazione_Attivita.findAll", query="SELECT d FROM Donazione_Attivita d")
public class Donazione_Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Donazione_AttivitaPK id;

	private byte donato;

	//bi-directional many-to-one association to Attivita
	@ManyToOne
	@JoinColumn(name="idAttivita")
	private Attivita attivita;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="emailDonatore")
	private Utente utente;

	public Donazione_Attivita() {
	}

	public Donazione_AttivitaPK getId() {
		return this.id;
	}

	public void setId(Donazione_AttivitaPK id) {
		this.id = id;
	}

	public byte getDonato() {
		return this.donato;
	}

	public void setDonato(byte donato) {
		this.donato = donato;
	}

	public Attivita getAttivita() {
		return this.attivita;
	}

	public void setAttivita(Attivita attivita) {
		this.attivita = attivita;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}