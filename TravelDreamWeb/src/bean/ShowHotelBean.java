package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.HotelDTO;
import model.Hotel;


@ManagedBean(name="sh")
@RequestScoped
public class ShowHotelBean {
	
	private String nomeHotel;
	private int numeroFoto;
	private String hotelSelezionato;
	public int getNumeroFoto() {
		return numeroFoto;
	}

	public void setNumeroFoto(int numeroFoto) {
		this.numeroFoto = numeroFoto;
	}

	@EJB
	private ShowHotelLocal showHotel;
	
	public String getNomeHotel(){
		return nomeHotel;
	}
	
	public void setNomeHotel(String nomeHotel){
		this.nomeHotel=nomeHotel;
	}
	
	public HotelDTO getHotelPerParametro(){
		HotelDTO h = showHotel.getHotel(nomeHotel);
		return h;
	}
	
	public String getFotoSelezionata(){
		HotelDTO h = getHotelPerParametro();
		switch(numeroFoto){
		case 1:
			return h.getFoto1();
		case 2:
			return h.getFoto2();
		case 3:
			return h.getFoto3();
		default:
			return "";
		}
	}
	
	public List<HotelDTO> listaHotel(){
		return showHotel.listaHotel();
	}	
	
	public String getHotelSelezionato() {
		return hotelSelezionato;
	}

	public void setHotelSelezionato(String hotelSelezionato) {
		this.hotelSelezionato = hotelSelezionato;
	}


}
