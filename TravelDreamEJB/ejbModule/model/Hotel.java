package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Hotel database table.
 * 
 */
@Entity
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idHotel;

	private String citta;

	@Lob
	private String descrizione;

	private String indirizzo;

	private String nome;

	private byte selezionabile;

	private String telefono;

	//bi-directional many-to-one association to Pernottamento
	@OneToMany(mappedBy="hotelBean")
	private List<Pernottamento> pernottamentos;

	//bi-directional many-to-one association to TipoCamere_Hotel
	@OneToMany(mappedBy="hotel")
	private List<TipoCamere_Hotel> tipoCamereHotels;

	public Hotel() {
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

	public byte getSelezionabile() {
		return this.selezionabile;
	}

	public void setSelezionabile(byte selezionabile) {
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