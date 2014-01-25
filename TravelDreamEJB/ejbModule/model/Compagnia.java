package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Compagnia database table.
 * 
 */
@Entity
@Table(name="Compagnia")
@NamedQuery(name="Compagnia.findAll", query="SELECT c FROM Compagnia c")
public class Compagnia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCompagnia;

	@Lob
	private String descrizione;

	private String nome;

	//bi-directional many-to-one association to Volo
	@OneToMany(mappedBy="compagniaBean")
	private List<Volo> volos;

	public Compagnia() {
	}

	public int getIdCompagnia() {
		return this.idCompagnia;
	}

	public void setIdCompagnia(int idCompagnia) {
		this.idCompagnia = idCompagnia;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Volo> getVolos() {
		return this.volos;
	}

	public void setVolos(List<Volo> volos) {
		this.volos = volos;
	}

	public Volo addVolo(Volo volo) {
		getVolos().add(volo);
		volo.setCompagniaBean(this);

		return volo;
	}

	public Volo removeVolo(Volo volo) {
		getVolos().remove(volo);
		volo.setCompagniaBean(null);

		return volo;
	}

}