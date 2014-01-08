package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Aeroporto database table.
 * 
 */
@Entity
@NamedQuery(name="Aeroporto.findAll", query="SELECT a FROM Aeroporto a")
public class Aeroporto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAeroporto;

	private String citta;

	private String nome;

	//bi-directional many-to-one association to Volo
	@OneToMany(mappedBy="aeroporto1")
	private List<Volo> volos1;

	//bi-directional many-to-one association to Volo
	@OneToMany(mappedBy="aeroporto2")
	private List<Volo> volos2;

	public Aeroporto() {
	}

	public int getIdAeroporto() {
		return this.idAeroporto;
	}

	public void setIdAeroporto(int idAeroporto) {
		this.idAeroporto = idAeroporto;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Volo> getVolos1() {
		return this.volos1;
	}

	public void setVolos1(List<Volo> volos1) {
		this.volos1 = volos1;
	}

	public Volo addVolos1(Volo volos1) {
		getVolos1().add(volos1);
		volos1.setAeroporto1(this);

		return volos1;
	}

	public Volo removeVolos1(Volo volos1) {
		getVolos1().remove(volos1);
		volos1.setAeroporto1(null);

		return volos1;
	}

	public List<Volo> getVolos2() {
		return this.volos2;
	}

	public void setVolos2(List<Volo> volos2) {
		this.volos2 = volos2;
	}

	public Volo addVolos2(Volo volos2) {
		getVolos2().add(volos2);
		volos2.setAeroporto2(this);

		return volos2;
	}

	public Volo removeVolos2(Volo volos2) {
		getVolos2().remove(volos2);
		volos2.setAeroporto2(null);

		return volos2;
	}

}