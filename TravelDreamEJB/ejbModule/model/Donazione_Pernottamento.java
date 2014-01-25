package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Donazione_Pernottamento database table.
 * 
 */
@Entity
@NamedQuery(name="Donazione_Pernottamento.findAll", query="SELECT d FROM Donazione_Pernottamento d")
public class Donazione_Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Donazione_PernottamentoPK id;

	private byte donato;

	//bi-directional many-to-one association to Pernottamento
	@ManyToOne
	@JoinColumn(name="idPernottamento")
	private Pernottamento pernottamento;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="emailDonatore")
	private Utente utente;

	public Donazione_Pernottamento() {
	}

	public Donazione_PernottamentoPK getId() {
		return this.id;
	}

	public void setId(Donazione_PernottamentoPK id) {
		this.id = id;
	}

	public byte getDonato() {
		return this.donato;
	}

	public void setDonato(byte donato) {
		this.donato = donato;
	}

	public Pernottamento getPernottamento() {
		return this.pernottamento;
	}

	public void setPernottamento(Pernottamento pernottamento) {
		this.pernottamento = pernottamento;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}