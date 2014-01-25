package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Volo database table.
 * 
 */
@Entity
@NamedQuery(name="Volo.findAll", query="SELECT v FROM Volo v")
public class Volo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idVolo;

	@Temporal(TemporalType.DATE)
	private Date data;

	private Time oraArrivo;

	private Time oraPartenza;

	private BigDecimal prezzo;

	//bi-directional many-to-one association to Donazione_Volo
	@OneToMany(mappedBy="volo")
	private List<Donazione_Volo> donazioneVolos;

	//bi-directional many-to-one association to Pacchetto
	@OneToMany(mappedBy="volo1")
	private List<Pacchetto> pacchettos1;

	//bi-directional many-to-one association to Pacchetto
	@OneToMany(mappedBy="volo2")
	private List<Pacchetto> pacchettos2;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="volos")
	private List<Utente> utentes;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="volo1")
	private List<Viaggio> viaggios1;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="volo2")
	private List<Viaggio> viaggios2;

	//bi-directional many-to-one association to Viaggio_Volo
	@OneToMany(mappedBy="volo")
	private List<Viaggio_Volo> viaggioVolos;

	//bi-directional many-to-one association to Aeroporto
	@ManyToOne
	@JoinColumn(name="aeroportoPartenza")
	private Aeroporto aeroporto1;

	//bi-directional many-to-one association to Aeroporto
	@ManyToOne
	@JoinColumn(name="aeroportoArrivo")
	private Aeroporto aeroporto2;

	//bi-directional many-to-one association to Compagnia
	@ManyToOne
	@JoinColumn(name="compagnia")
	private Compagnia compagniaBean;

	public Volo() {
	}

	public int getIdVolo() {
		return this.idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getOraArrivo() {
		return this.oraArrivo;
	}

	public void setOraArrivo(Time oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	public Time getOraPartenza() {
		return this.oraPartenza;
	}

	public void setOraPartenza(Time oraPartenza) {
		this.oraPartenza = oraPartenza;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public List<Donazione_Volo> getDonazioneVolos() {
		return this.donazioneVolos;
	}

	public void setDonazioneVolos(List<Donazione_Volo> donazioneVolos) {
		this.donazioneVolos = donazioneVolos;
	}

	public Donazione_Volo addDonazioneVolo(Donazione_Volo donazioneVolo) {
		getDonazioneVolos().add(donazioneVolo);
		donazioneVolo.setVolo(this);

		return donazioneVolo;
	}

	public Donazione_Volo removeDonazioneVolo(Donazione_Volo donazioneVolo) {
		getDonazioneVolos().remove(donazioneVolo);
		donazioneVolo.setVolo(null);

		return donazioneVolo;
	}

	public List<Pacchetto> getPacchettos1() {
		return this.pacchettos1;
	}

	public void setPacchettos1(List<Pacchetto> pacchettos1) {
		this.pacchettos1 = pacchettos1;
	}

	public Pacchetto addPacchettos1(Pacchetto pacchettos1) {
		getPacchettos1().add(pacchettos1);
		pacchettos1.setVolo1(this);

		return pacchettos1;
	}

	public Pacchetto removePacchettos1(Pacchetto pacchettos1) {
		getPacchettos1().remove(pacchettos1);
		pacchettos1.setVolo1(null);

		return pacchettos1;
	}

	public List<Pacchetto> getPacchettos2() {
		return this.pacchettos2;
	}

	public void setPacchettos2(List<Pacchetto> pacchettos2) {
		this.pacchettos2 = pacchettos2;
	}

	public Pacchetto addPacchettos2(Pacchetto pacchettos2) {
		getPacchettos2().add(pacchettos2);
		pacchettos2.setVolo2(this);

		return pacchettos2;
	}

	public Pacchetto removePacchettos2(Pacchetto pacchettos2) {
		getPacchettos2().remove(pacchettos2);
		pacchettos2.setVolo2(null);

		return pacchettos2;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

	public List<Viaggio> getViaggios1() {
		return this.viaggios1;
	}

	public void setViaggios1(List<Viaggio> viaggios1) {
		this.viaggios1 = viaggios1;
	}

	public Viaggio addViaggios1(Viaggio viaggios1) {
		getViaggios1().add(viaggios1);
		viaggios1.setVolo1(this);

		return viaggios1;
	}

	public Viaggio removeViaggios1(Viaggio viaggios1) {
		getViaggios1().remove(viaggios1);
		viaggios1.setVolo1(null);

		return viaggios1;
	}

	public List<Viaggio> getViaggios2() {
		return this.viaggios2;
	}

	public void setViaggios2(List<Viaggio> viaggios2) {
		this.viaggios2 = viaggios2;
	}

	public Viaggio addViaggios2(Viaggio viaggios2) {
		getViaggios2().add(viaggios2);
		viaggios2.setVolo2(this);

		return viaggios2;
	}

	public Viaggio removeViaggios2(Viaggio viaggios2) {
		getViaggios2().remove(viaggios2);
		viaggios2.setVolo2(null);

		return viaggios2;
	}

	public List<Viaggio_Volo> getViaggioVolos() {
		return this.viaggioVolos;
	}

	public void setViaggioVolos(List<Viaggio_Volo> viaggioVolos) {
		this.viaggioVolos = viaggioVolos;
	}

	public Viaggio_Volo addViaggioVolo(Viaggio_Volo viaggioVolo) {
		getViaggioVolos().add(viaggioVolo);
		viaggioVolo.setVolo(this);

		return viaggioVolo;
	}

	public Viaggio_Volo removeViaggioVolo(Viaggio_Volo viaggioVolo) {
		getViaggioVolos().remove(viaggioVolo);
		viaggioVolo.setVolo(null);

		return viaggioVolo;
	}

	public Aeroporto getAeroporto1() {
		return this.aeroporto1;
	}

	public void setAeroporto1(Aeroporto aeroporto1) {
		this.aeroporto1 = aeroporto1;
	}

	public Aeroporto getAeroporto2() {
		return this.aeroporto2;
	}

	public void setAeroporto2(Aeroporto aeroporto2) {
		this.aeroporto2 = aeroporto2;
	}

	public Compagnia getCompagniaBean() {
		return this.compagniaBean;
	}

	public void setCompagniaBean(Compagnia compagniaBean) {
		this.compagniaBean = compagniaBean;
	}

}