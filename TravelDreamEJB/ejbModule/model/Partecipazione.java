package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Partecipazione database table.
 * 
 */
@Entity
@NamedQuery(name="Partecipazione.findAll", query="SELECT p FROM Partecipazione p")
public class Partecipazione implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartecipazionePK id;

	private byte pagato;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="emailPartecipante")
	private Utente utente;

	//bi-directional many-to-one association to Viaggio
	@ManyToOne
	@JoinColumn(name="idViaggio")
	private Viaggio viaggio;

	public Partecipazione() {
	}

	public PartecipazionePK getId() {
		return this.id;
	}

	public void setId(PartecipazionePK id) {
		this.id = id;
	}

	public byte getPagato() {
		return this.pagato;
	}

	public void setPagato(byte pagato) {
		this.pagato = pagato;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Viaggio getViaggio() {
		return this.viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

}