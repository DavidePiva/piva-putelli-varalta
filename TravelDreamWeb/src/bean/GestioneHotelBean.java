package bean;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.HotelDTO;
import DTO.TipoCamera;
import DTO.TipoCamere_HotelDTO;
import DTO.UtenteDTO;

@ManagedBean(name = "gh")
public class GestioneHotelBean {

	@EJB
	private GestioneComponentiLocal gc;
	@EJB
	private ShowHotelLocal sh;

	private HotelDTO hotel;
	private TipoCamere_HotelDTO lowCost;
	private TipoCamere_HotelDTO dream;
	private TipoCamere_HotelDTO smart;
	private String nomeHotel;
	
	public GestioneHotelBean(){
		this.hotel=new HotelDTO();
		this.lowCost=new TipoCamere_HotelDTO();
		this.smart=new TipoCamere_HotelDTO();
		this.dream=new TipoCamere_HotelDTO();
	}
	
	public String creaHotel(){
		this.hotel.setSelezionabile(true);
		gc.creaHotel(hotel);
		return "/impiegato/prezziCamere?faces-redirect=true";
	}

	public String salvaCamere(){
			HotelDTO h=getHotelPerParametro();
			lowCost.setId(h.getIdHotel());
			lowCost.setTipo(TipoCamera.LOWCOST);
			dream.setId(h.getIdHotel());
			dream.setTipo(TipoCamera.DREAM);
			smart.setId(h.getIdHotel());
			smart.setTipo(TipoCamera.SMART);
			gc.salvaCamera(lowCost,h);
			gc.salvaCamera(smart,h);
			gc.salvaCamera(dream,h);

		return "/impiegato/index?faces-redirect=true";
	}
	
	private HotelDTO getHotelPerParametro(){
		HotelDTO h = sh.getHotel(nomeHotel);
		return h;
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

	public TipoCamere_HotelDTO getLowCost() {
		return lowCost;
	}

	public void setLowCost(TipoCamere_HotelDTO lowCost) {
		this.lowCost = lowCost;
	}

	public TipoCamere_HotelDTO getDream() {
		return dream;
	}

	public void setDream(TipoCamere_HotelDTO dream) {
		this.dream = dream;
	}

	public TipoCamere_HotelDTO getSmart() {
		return smart;
	}

	public void setSmart(TipoCamere_HotelDTO smart) {
		this.smart = smart;
	}

	public ShowHotelLocal getSh() {
		return sh;
	}

	public void setSh(ShowHotelLocal sh) {
		this.sh = sh;
	}

	public String getNomeHotel() {
		return nomeHotel;
	}

	public void setNomeHotel(String nomeHotel) {
		this.nomeHotel = nomeHotel;
	}

	


}
