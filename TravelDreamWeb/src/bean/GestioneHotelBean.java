package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.HotelDTO;
import DTO.UtenteDTO;

@ManagedBean(name = "gh")
public class GestioneHotelBean {

	@EJB
	private GestioneComponentiLocal gc;


	private HotelDTO hotel;

	
	public GestioneHotelBean(){
		this.hotel=new HotelDTO();
	}
	
	public String creaHotel(){
		this.hotel.setSelezionabile(true);
		gc.creaHotel(hotel);
		return "/impiegato/index?faces-redirect=true";
	}

	public GestioneComponentiLocal getGc() {
		return gc;
	}

	public void setGc(GestioneComponentiLocal gc) {
		this.gc = gc;
	}

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}


}
