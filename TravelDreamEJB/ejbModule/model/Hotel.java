package model;

import java.io.Serializable;

import javax.persistence.*;

import DTO.HotelDTO;

import java.util.List;


/**
 * The persistent class for the Hotel database table.
 * 
 */
@Entity
@Table(name="Hotel")
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idHotel;

	private String citta;

	@Lob
	private String descrizione;

	private String foto1;

	private String foto2;

	private String foto3;

	private String indirizzo;

	private String nome;

	private boolean selezionabile;

	private String telefono;

	//bi-directional many-to-one association to Pernottamento
	@OneToMany(mappedBy="hotelBean")
	private List<Pernottamento> pernottamentos;

	//bi-directional many-to-one association to TipoCamere_Hotel
	@OneToMany(mappedBy="hotel")
	private List<TipoCamere_Hotel> tipoCamereHotels;

	public Hotel() {
	}
	public Hotel(HotelDTO h){
		this.idHotel=h.getIdHotel();
		this.citta=h.getCitta();
		this.descrizione=h.getDescrizione();
		this.indirizzo=h.getIndirizzo();
		this.nome=h.getNome();
		this.selezionabile=h.isSelezionabile();
		this.telefono=h.getTelefono();
		this.foto1=h.getFoto1();
		this.foto2=h.getFoto2();
		this.foto3=h.getFoto3();
	}
	public int getIdHotel() {
		return this.idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
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

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean getSelezionabile() {
		return this.selezionabile;
	}

	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Pernottamento> getPernottamentos() {
		return this.pernottamentos;
	}

	public void setPernottamentos(List<Pernottamento> pernottamentos) {
		this.pernottamentos = pernottamentos;
	}

	public Pernottamento addPernottamento(Pernottamento pernottamento) {
		getPernottamentos().add(pernottamento);
		pernottamento.setHotelBean(this);

		return pernottamento;
	}

	public Pernottamento removePernottamento(Pernottamento pernottamento) {
		getPernottamentos().remove(pernottamento);
		pernottamento.setHotelBean(null);

		return pernottamento;
	}

	public List<TipoCamere_Hotel> getTipoCamereHotels() {
		return this.tipoCamereHotels;
	}

	public void setTipoCamereHotels(List<TipoCamere_Hotel> tipoCamereHotels) {
		this.tipoCamereHotels = tipoCamereHotels;
	}

	public TipoCamere_Hotel addTipoCamereHotel(TipoCamere_Hotel tipoCamereHotel) {
		getTipoCamereHotels().add(tipoCamereHotel);
		tipoCamereHotel.setHotel(this);

		return tipoCamereHotel;
	}

	public TipoCamere_Hotel removeTipoCamereHotel(TipoCamere_Hotel tipoCamereHotel) {
		getTipoCamereHotels().remove(tipoCamereHotel);
		tipoCamereHotel.setHotel(null);

		return tipoCamereHotel;
	}

}