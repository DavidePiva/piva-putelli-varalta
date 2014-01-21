package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Viaggio_Attivita database table.
 * 
 */
@Entity
@Table(name="Viaggio_Attivita")
@NamedQuery(name="Viaggio_Attivita.findAll", query="SELECT v FROM Viaggio_Attivita v")
public class Viaggio_Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Viaggio_AttivitaPK id;

	private boolean regalabile;

	//bi-directional many-to-one association to Attivita
	@ManyToOne
	@JoinColumn(name="idAttivita")
	private Attivita attivita;

	//bi-directional many-to-one association to Viaggio
	@ManyToOne
	@JoinColumn(name="idViaggio")
	private Viaggio viaggio;

	public Viaggio_Attivita() {
	}

	public Viaggio_AttivitaPK getId() {
		return this.id;
	}

	public void setId(Viaggio_AttivitaPK id) {
		this.id = id;
	}

	public boolean getRegalabile() {
		return this.regalabile;
	}

	public void setRegalabile(boolean regalabile) {
		this.regalabile = regalabile;
	}

	public Attivita getAttivita() {
		return this.attivita;
	}

	public void setAttivita(Attivita attivita) {
		this.attivita = attivita;
	}

	public Viaggio getViaggio() {
		return this.viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

}