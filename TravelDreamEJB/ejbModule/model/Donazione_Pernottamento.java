package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Donazione_Pernottamento database table.
 * 
 */
@Entity
@Table(name="Donazione_Pernottamento")
@NamedQuery(name="Donazione_Pernottamento.findAll", query="SELECT d FROM Donazione_Pernottamento d")
public class Donazione_Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Donazione_PernottamentoPK id;

	private boolean donato;

	//bi-directional many-to-one association to Pernottamento
	@ManyToOne
	@JoinColumn(name="idPernottamento")
	private Pernottamento pernottamento;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="emailDonatore")
	private Utente utente;
	
	//bi-directional many-to-one association to Viaggio
	@ManyToOne
	@JoinColumn(name="idViaggio")
	private Viaggio viaggio;

	public Donazione_Pernottamento() {
	}

	public Donazione_PernottamentoPK getId() {
		return this.id;
	}

	public void setId(Donazione_PernottamentoPK id) {
		this.id = id;
	}

	public boolean getDonato() {
		return this.donato;
	}

	public void setDonato(boolean donato) {
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

	public Viaggio getViaggio() {
		return viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

}