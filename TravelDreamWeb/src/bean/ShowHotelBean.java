package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.HotelDTO;
import model.Hotel;


@ManagedBean(name="sh")
@RequestScoped
public class ShowHotelBean {
	
	private String nomeHotel;
	
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
	
	public String getDescrizione(){
		HotelDTO h = getHotelPerParametro();
		return h.getDescrizione();
	}
	
	
	
}
