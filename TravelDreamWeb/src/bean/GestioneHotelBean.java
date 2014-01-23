package bean;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

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
	@EJB
	private DatiStaticiLocal ds;

	
	@Pattern(regexp="^(?!^0)\\d{1,9}$", message="Inserisci un valore positivo")
	@NotEmpty(message="Inserisci un prezzo")
	String p1;
	@Pattern(regexp="^(?!^0)\\d{1,9}$", message="Inserisci un valore positivo")
	@NotEmpty(message="Inserisci un prezzo")
	String p2;
	@Pattern(regexp="^(?!^0)\\d{1,9}$", message="Inserisci un valore positivo")
	@NotEmpty(message="Inserisci un prezzo")
	String p3;
	
	private HotelDTO hotel;
	private TipoCamere_HotelDTO lowCost;
	private TipoCamere_HotelDTO dream;
	private TipoCamere_HotelDTO smart;
	private String nomeHotel;
	private String nomeHotelDaModificare;
	
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
	
	public String modificaHotel(){
		this.hotel.setSelezionabile(true);
		HotelDTO hotelDaModificare=sh.getHotel(nomeHotelDaModificare);
		hotel.setIdHotel(hotelDaModificare.getIdHotel());
		hotel.setCitta(hotelDaModificare.getCitta());
		hotel.setIndirizzo(hotelDaModificare.getIndirizzo());
		if(hotel.getDescrizione().equals("")){
			hotel.setDescrizione(hotelDaModificare.getDescrizione());
		}
		if(hotel.getNome().equals("")){
			hotel.setNome(hotelDaModificare.getNome());
		}
		if(hotel.getTelefono().equals("")){
			hotel.setTelefono(hotelDaModificare.getTelefono());
		}
		if(hotel.getFoto1().equals("")){
			hotel.setFoto1(hotelDaModificare.getFoto1());
		}
		if(hotel.getFoto2().equals("")){
			hotel.setFoto2(hotelDaModificare.getFoto2());
		}
		if(hotel.getFoto3().equals("")){
			hotel.setFoto3(hotelDaModificare.getFoto3());
		}
		gc.modificaHotel(hotel);
		return "/impiegato/prezziCamere?faces-redirect=true";
	}

	public String salvaCamere(){
		
			HotelDTO h=getHotelPerParametro();
			
			lowCost.setId(h.getIdHotel());
			lowCost.setTipo(TipoCamera.LOWCOST);
			lowCost.setPrezzo(new BigDecimal(p1));
			gc.salvaCamera(lowCost,h);
			
			dream.setId(h.getIdHotel());
			dream.setTipo(TipoCamera.DREAM);
			dream.setPrezzo(new BigDecimal(p3));
			gc.salvaCamera(dream,h);
			
			
			smart.setId(h.getIdHotel());
			smart.setTipo(TipoCamera.SMART);
			smart.setPrezzo(new BigDecimal(p2));
			gc.salvaCamera(smart,h);

		return "/impiegato/index?faces-redirect=true";
	}
	
	public String eliminaHotel(){
		HotelDTO h=ds.getHotelPerNome(nomeHotelDaModificare);
		gc.eliminaHotel(h.getIdHotel());
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

	public String getNomeHotelDaModificare() {
		return nomeHotelDaModificare;
	}

	public void setNomeHotelDaModificare(String nomeHotelDaModificare) {
		this.nomeHotelDaModificare = nomeHotelDaModificare;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}
	
	


}
