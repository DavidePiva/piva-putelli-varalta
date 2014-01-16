package model;

import java.io.Serializable;

import javax.persistence.*;

import DTO.PernottamentoDTO;

import java.util.List;


/**
 * The persistent class for the Pernottamento database table.
 * 
 */
@Entity
@Table(name="Pernottamento")
@NamedQuery(name="Pernottamento.findAll", query="SELECT p FROM Pernottamento p")
public class Pernottamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
		private int idPernottamento;
	
		private boolean selezionabile;
	
		private String tipo;

	//bi-directional many-to-many association to Utente
	@ManyToMany(mappedBy="pernottamentos")
	private List<Utente> utentes;

	//bi-directional many-to-one association to Pacchetto
	@OneToMany(mappedBy="pernottamentoBean")
	private List<Pacchetto> pacchettos;

	//bi-directional many-to-one association to Hotel
	@ManyToOne
	@JoinColumn(name="hotel")
	private Hotel hotelBean;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="pernottamentoBean")
	private List<Viaggio> viaggios;

	//bi-directional many-to-one association to Viaggio_Pernottamento
	@OneToMany(mappedBy="pernottamento")
	private List<Viaggio_Pernottamento> viaggioPernottamentos;

	public Pernottamento() {
	}

	public Pernottamento(PernottamentoDTO p){
		this.idPernottamento=p.getIdPernottamento();
		this.selezionabile=p.getSelezionabile();
		this.tipo=p.getTipo();		
	}
	
	public int getIdPernottamento() {
		return this.idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public boolean getSelezionabile() {
		return this.selezionabile;
	}

	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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