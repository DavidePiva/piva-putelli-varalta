package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the Gruppo database table.
 * 
 */
@Entity
@Table(name="Gruppo")
@NamedQuery(name="Gruppo.findAll", query="SELECT g FROM Gruppo g")
public class Gruppo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idGruppo;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="gruppos")
	private List<Utente> utentes;

	public Gruppo() {
	}

	public String getIdGruppo() {
		return this.idGruppo;
	}

	public void setIdGruppo(String idGruppo) {
		this.idGruppo = idGruppo;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

}