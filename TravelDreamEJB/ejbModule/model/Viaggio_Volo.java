package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Viaggio_Volo database table.
 * 
 */
@Entity
@Table(name="Viaggio_Volo")
@NamedQuery(name="Viaggio_Volo.findAll", query="SELECT v FROM Viaggio_Volo v")
public class Viaggio_Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Viaggio_VoloPK id;

	private boolean regalabile;

	//bi-directional many-to-one association to Viaggio
	@ManyToOne
	@JoinColumn(name="idViaggio")
	private Viaggio viaggio;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="idVolo")
	private Volo volo;

	public Viaggio_Volo() {
	}

	public Viaggio_VoloPK getId() {
		return this.id;
	}

	public void setId(Viaggio_VoloPK id) {
		this.id = id;
	}

	public boolean getRegalabile() {
		return this.regalabile;
	}

	public void setRegalabile(boolean regalabile) {
		this.regalabile = regalabile;
	}

	public Viaggio getViaggio() {
		return this.viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

	public Volo getVolo() {
		return this.volo;
	}

	public void setVolo(Volo volo) {
		this.volo = volo;
	}

}