package model;

import java.io.Serializable;

import javax.persistence.*;

import DTO.PacchettoDTO;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the Pacchetto database table.
 * 
 */
@Entity
@Table(name="Pacchetto")
@NamedQuery(name="Pacchetto.findAll", query="SELECT p FROM Pacchetto p")
public class Pacchetto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id     @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPacchetto;

	private String citta;

	@Lob
	private String descrizione;

	private String foto1;

	private String foto2;

	private String foto3;

	private String foto4;

	private String foto5;

	private String foto6;

	private BigDecimal prezzo;

	private boolean selezionabile;

	private String target;

	private String tipologia;

	private String titolo;

	//bi-directional many-to-one association to Pernottamento
	@ManyToOne
	@JoinColumn(name="pernottamento")
	private Pernottamento pernottamentoBean;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="voloAndata")
	private Volo volo1;

	//bi-directional many-to-one association to Volo
	@ManyToOne
	@JoinColumn(name="voloRitorno")
	private Volo volo2;

	//bi-directional many-to-many association to Attivita
	@ManyToMany
	@JoinTable(
		name="Pacchetto_Attivita"
		, joinColumns={
			@JoinColumn(name="idPacchetto")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAttivita")
			}
		)
	private List<Attivita> attivitas;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="pacchetto")
	private List<Viaggio> viaggios;

	public Pacchetto() {
	}

	public Pacchetto(PacchettoDTO p){
		this.titolo = p.getTitolo();
		this.citta = p.getCitta();
		this.descrizione = p.getDescrizione();
		this.foto1 = p.getFoto1();
		this.foto2 = p.getFoto2();
		this.foto3 = p.getFoto3();
		this.foto4 = p.getFoto4();
		this.foto5 = p.getFoto5();
		this.foto6 = p.getFoto6();
		this.idPacchetto = p.getIdPacchetto();
		this.prezzo = p.getPrezzo();
		this.selezionabile = p.getSelezionabile();
		this.target = p.getTarget();
		this.tipologia = p.getTipologia();
	}
	
	public int getIdPacchetto() {
		return this.idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public String getCitta() {
		return this.citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
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

	public String getFoto4() {
		return this.foto4;
	}

	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}

	public String getFoto5() {
		return this.foto5;
	}

	public void setFoto5(String foto5) {
		this.foto5 = foto5;
	}

	public String getFoto6() {
		return this.foto6;
	}

	public void setFoto6(String foto6) {
		this.foto6 = foto6;
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

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTipologia() {
		return this.tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
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

	public List<Attivita> getAttivitas() {
		return this.attivitas;
	}

	public void setAttivitas(List<Attivita> attivitas) {
		this.attivitas = attivitas;
	}

	public List<Viaggio> getViaggios() {
		return this.viaggios;
	}

	public void setViaggios(List<Viaggio> viaggios) {
		this.viaggios = viaggios;
	}

	public Viaggio addViaggio(Viaggio viaggio) {
		getViaggios().add(viaggio);
		viaggio.setPacchetto(this);

		return viaggio;
	}

	public Viaggio removeViaggio(Viaggio viaggio) {
		getViaggios().remove(viaggio);
		viaggio.setPacchetto(null);

		return viaggio;
	}

}