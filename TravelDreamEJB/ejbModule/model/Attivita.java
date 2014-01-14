package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Attivita database table.
 * 
 */
@Entity
@Table(name="Attivita")
@NamedQuery(name="Attivita.findAll", query="SELECT a FROM Attivita a")
public class Attivita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAttivita;

	private String citta;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Lob
	private String descrizione;

	private String foto1;

	private String foto2;

	private String foto3;

	private Time ora;

	private BigDecimal prezzo;

	private boolean selezionabile;

	private String titolo;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="attivitas")
	private List<Utente> utentes;

	//bi-directional many-to-many association to Pacchetto
	@ManyToMany(mappedBy="attivitas")
	private List<Pacchetto> pacchettos;

	//bi-directional many-to-one association to Viaggio_Attivita
	@OneToMany(mappedBy="attivita")
	private List<Viaggio_Attivita> viaggioAttivitas;

	public Attivita() {
	}

	public int getIdAttivita() {
		return this.idAttivita;
	}

	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getFoto1() {
		return this.foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return this.foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return this.foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public Time getOra() {
		return this.ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public boolean getSelezionabile() {
		return this.selezionabile;
	}

	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

	public List<Pacchetto> getPacchettos() {
		return this.pacchettos;
	}

	public void setPacchettos(List<Pacchetto> pacchettos) {
		this.pacchettos = pacchettos;
	}

	public List<Viaggio_Attivita> getViaggioAttivitas() {
		return this.viaggioAttivitas;
	}

	public void setViaggioAttivitas(List<Viaggio_Attivita> viaggioAttivitas) {
		this.viaggioAttivitas = viaggioAttivitas;
	}

	public Viaggio_Attivita addViaggioAttivita(Viaggio_Attivita viaggioAttivita) {
		getViaggioAttivitas().add(viaggioAttivita);
		viaggioAttivita.setAttivita(this);

		return viaggioAttivita;
	}

	public Viaggio_Attivita removeViaggioAttivita(Viaggio_Attivita viaggioAttivita) {
		getViaggioAttivitas().remove(viaggioAttivita);
		viaggioAttivita.setAttivita(null);

		return viaggioAttivita;
	}

}