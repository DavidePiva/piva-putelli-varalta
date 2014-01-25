package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Pernottamento database table.
 * 
 */
@Entity
@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p")
public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idPernottamento;

	private byte selezionabile;

	private String tipo;

	//bi-directional many-to-one association to Donazione_Pernottamento
	@OneToMany(mappedBy="pernottamento")
	private List<Donazione_Pernottamento> donazionePernottamentos;

	//bi-directional many-to-one association to Pacchetto
	@OneToMany(mappedBy="pernottamentoBean")
	private List<Pacchetto> pacchettos;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="hotel")
	private Hotel hotelBean;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="pernottamentos")
	private List<Utente> utentes;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="pernottamentoBean")
	private List<Viaggio> viaggios;

	//bi-directional many-to-one association to Viaggio_Pernottamento
	@OneToMany(mappedBy="pernottamento")
	private List<Viaggio_Pernottamento> viaggioPernottamentos;

	public Pernottamento() {
	}

	public int getIdPernottamento() {
		return this.idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public byte getSelezionabile() {
		return this.selezionabile;
	}

	public void setSelezionabile(byte selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Donazione_Pernottamento> getDonazionePernottamentos() {
		return this.donazionePernottamentos;
	}

	public void setDonazionePernottamentos(List<Donazione_Pernottamento> donazionePernottamentos) {
		this.donazionePernottamentos = donazionePernottamentos;
	}

	public Donazione_Pernottamento addDonazionePernottamento(Donazione_Pernottamento donazionePernottamento) {
		getDonazionePernottamentos().add(donazionePernottamento);
		donazionePernottamento.setPernottamento(this);

		return donazionePernottamento;
	}

	public Donazione_Pernottamento removeDonazionePernottamento(Donazione_Pernottamento donazionePernottamento) {
		getDonazionePernottamentos().remove(donazionePernottamento);
		donazionePernottamento.setPernottamento(null);

		return donazionePernottamento;
	}

	public List<Pacchetto> getPacchettos() {
		return this.pacchettos;
	}

	public void setPacchettos(List<Pacchetto> pacchettos) {
		this.pacchettos = pacchettos;
	}

	public Pacchetto addPacchetto(Pacchetto pacchetto) {
		getPacchettos().add(pacchetto);
		pacchetto.setPernottamentoBean(this);

		return pacchetto;
	}

	public Pacchetto removePacchetto(Pacchetto pacchetto) {
		getPacchettos().remove(pacchetto);
		pacchetto.setPernottamentoBean(null);

		return pacchetto;
	}

	public Hotel getHotelBean() {
		return this.hotelBean;
	}

	public void setHotelBean(Hotel hotelBean) {
		this.hotelBean = hotelBean;
	}

	public List<Utente> getUtentes() {
		return this.utentes;
	}

	public void setUtentes(List<Utente> utentes) {
		this.utentes = utentes;
	}

	public List<Viaggio> getViaggios() {
		return this.viaggios;
	}

	public void setViaggios(List<Viaggio> viaggios) {
		this.viaggios = viaggios;
	}

	public Viaggio addViaggio(Viaggio viaggio) {
		getViaggios().add(viaggio);
		viaggio.setPernottamentoBean(this);

		return viaggio;
	}

	public Viaggio removeViaggio(Viaggio viaggio) {
		getViaggios().remove(viaggio);
		viaggio.setPernottamentoBean(null);

		return viaggio;
	}

	public List<Viaggio_Pernottamento> getViaggioPernottamentos() {
		return this.viaggioPernottamentos;
	}

	public void setViaggioPernottamentos(List<Viaggio_Pernottamento> viaggioPernottamentos) {
		this.viaggioPernottamentos = viaggioPernottamentos;
	}

	public Viaggio_Pernottamento addViaggioPernottamento(Viaggio_Pernottamento viaggioPernottamento) {
		getViaggioPernottamentos().add(viaggioPernottamento);
		viaggioPernottamento.setPernottamento(this);

		return viaggioPernottamento;
	}

	public Viaggio_Pernottamento removeViaggioPernottamento(Viaggio_Pernottamento viaggioPernottamento) {
		getViaggioPernottamentos().remove(viaggioPernottamento);
		viaggioPernottamento.setPernottamento(null);

		return viaggioPernottamento;
	}

}