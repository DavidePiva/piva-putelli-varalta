package model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the Viaggio database table.
 * 
 */
@Entity
@Table(name="Viaggio")
@NamedQuery(name="Viaggio.findAll", query="SELECT v FROM Viaggio v")
public class Viaggio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idViaggio")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idViaggio;

	private String citta;

	private int numeroPersone;

	private boolean pagato;

	private BigDecimal prezzo;

	//bi-directional many-to-one association to Partecipazione
	@OneToMany(mappedBy="viaggio")
	private List<Partecipazione> partecipaziones;

	//bi-directional many-to-one association to Pernottamento
	@ManyToOne
	@JoinColumn(name="pernottamento")
	private Pernottamento pernottamentoBean;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="voloRitorno")
	private Volo volo1;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="voloAndata")
	private Volo volo2;

	//bi-directional many-to-one association to Utente
	@ManyToOne
	@JoinColumn(name="titolare")
	private Utente utente;

	//bi-directional many-to-one association to Pacchetto
	@ManyToOne
	@JoinColumn(name="pacchettoPadre")
	private Pacchetto pacchetto;

	//bi-directional many-to-one association to Viaggio_Attivita
	@OneToMany(mappedBy="viaggio")
	private List<Viaggio_Attivita> viaggioAttivitas;

	//bi-directional many-to-one association to Viaggio_Pernottamento
	@OneToMany(mappedBy="viaggio")
	private List<Viaggio_Pernottamento> viaggioPernottamentos;

	//bi-directional many-to-one association to Viaggio_Volo
	@OneToMany(mappedBy="viaggio")
	private List<Viaggio_Volo> viaggioVolos;

	public Viaggio() {
		viaggioAttivitas = new ArrayList<Viaggio_Attivita>();
	}

	public int getIdViaggio() {
		return this.idViaggio;
	}

	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public int getNumeroPersone() {
		return this.numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public boolean getPagato() {
		return this.pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public BigDecimal getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public List<Partecipazione> getPartecipaziones() {
		return this.partecipaziones;
	}

	public void setPartecipaziones(List<Partecipazione> partecipaziones) {
		this.partecipaziones = partecipaziones;
	}

	public Partecipazione addPartecipazione(Partecipazione partecipazione) {
		getPartecipaziones().add(partecipazione);
		partecipazione.setViaggio(this);

		return partecipazione;
	}

	public Partecipazione removePartecipazione(Partecipazione partecipazione) {
		getPartecipaziones().remove(partecipazione);
		partecipazione.setViaggio(null);

		return partecipazione;
	}

	public Pernottamento getPernottamentoBean() {
		return this.pernottamentoBean;
	}

	public void setPernottamentoBean(Pernottamento pernottamentoBean) {
		this.pernottamentoBean = pernottamentoBean;
	}

	public Volo getVolo1() {
		return this.volo1;
	}

	public void setVolo1(Volo volo1) {
		this.volo1 = volo1;
	}

	public Volo getVolo2() {
		return this.volo2;
	}

	public void setVolo2(Volo volo2) {
		this.volo2 = volo2;
	}

	public Utente getUtente() {
		return this.utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Pacchetto getPacchetto() {
		return this.pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}

	public List<Viaggio_Attivita> getViaggioAttivitas() {
		return this.viaggioAttivitas;
	}

	public void setViaggioAttivitas(List<Viaggio_Attivita> viaggioAttivitas) {
		this.viaggioAttivitas = viaggioAttivitas;
	}

	public Viaggio_Attivita addViaggioAttivita(Viaggio_Attivita viaggioAttivita) {
		getViaggioAttivitas().add(viaggioAttivita);
		viaggioAttivita.setViaggio(this);

		return viaggioAttivita;
	}

	public Viaggio_Attivita removeViaggioAttivita(Viaggio_Attivita viaggioAttivita) {
		getViaggioAttivitas().remove(viaggioAttivita);
		viaggioAttivita.setViaggio(null);

		return viaggioAttivita;
	}

	public List<Viaggio_Pernottamento> getViaggioPernottamentos() {
		return this.viaggioPernottamentos;
	}

	public void setViaggioPernottamentos(List<Viaggio_Pernottamento> viaggioPernottamentos) {
		this.viaggioPernottamentos = viaggioPernottamentos;
	}

	public Viaggio_Pernottamento addViaggioPernottamento(Viaggio_Pernottamento viaggioPernottamento) {
		getViaggioPernottamentos().add(viaggioPernottamento);
		viaggioPernottamento.setViaggio(this);

		return viaggioPernottamento;
	}

	public Viaggio_Pernottamento removeViaggioPernottamento(Viaggio_Pernottamento viaggioPernottamento) {
		getViaggioPernottamentos().remove(viaggioPernottamento);
		viaggioPernottamento.setViaggio(null);

		return viaggioPernottamento;
	}

	public List<Viaggio_Volo> getViaggioVolos() {
		return this.viaggioVolos;
	}

	public void setViaggioVolos(List<Viaggio_Volo> viaggioVolos) {
		this.viaggioVolos = viaggioVolos;
	}

	public Viaggio_Volo addViaggioVolo(Viaggio_Volo viaggioVolo) {
		getViaggioVolos().add(viaggioVolo);
		viaggioVolo.setViaggio(this);

		return viaggioVolo;
	}

	public Viaggio_Volo removeViaggioVolo(Viaggio_Volo viaggioVolo) {
		getViaggioVolos().remove(viaggioVolo);
		viaggioVolo.setViaggio(null);

		return viaggioVolo;
	}

}