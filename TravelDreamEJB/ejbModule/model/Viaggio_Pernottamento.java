package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Viaggio_Pernottamento database table.
 * 
 */
@Entity
@NamedQuery(name="Viaggio_Pernottamento.findAll", query="SELECT v FROM Viaggio_Pernottamento v")
public class Viaggio_Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Viaggio_PernottamentoPK id;

	private byte regalabile;

	//bi-directional many-to-one association to Pernottamento
	@ManyToOne
	@JoinColumn(name="idPernottamento")
	private Pernottamento pernottamento;

	//bi-directional many-to-one association to Viaggio
	@ManyToOne
	@JoinColumn(name="idViaggio")
	private Viaggio viaggio;

	public Viaggio_Pernottamento() {
	}

	public Viaggio_PernottamentoPK getId() {
		return this.id;
	}

	public void setId(Viaggio_PernottamentoPK id) {
		this.id = id;
	}

	public byte getRegalabile() {
		return this.regalabile;
	}

	public void setRegalabile(byte regalabile) {
		this.regalabile = regalabile;
	}

	public Pernottamento getPernottamento() {
		return this.pernottamento;
	}

	public void setPernottamento(Pernottamento pernottamento) {
		this.pernottamento = pernottamento;
	}

	public Viaggio getViaggio() {
		return this.viaggio;
	}

	public void setViaggio(Viaggio viaggio) {
		this.viaggio = viaggio;
	}

}