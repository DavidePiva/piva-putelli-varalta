package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.HotelDTO;
import DTO.TipoCamera;
import DTO.TipoCamere_HotelDTO;
import model.Hotel;
import model.TipoCamere_Hotel;


@ManagedBean(name="sh")
@RequestScoped
public class ShowHotelBean {
	
	@EJB
	private ShowHotelLocal showHotel;
	private String nomeHotel;
	private int numeroFoto;
	

	private List<TipoCamere_HotelDTO> camere;
	private List<String> nomiCamere;
	private List<BigDecimal> prezziCamere;
	
	public int getNumeroFoto() {
		return numeroFoto;
	}

	public void setNumeroFoto(int numeroFoto) {
		this.numeroFoto = numeroFoto;
	}


	
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

	public ShowHotelLocal getShowHotel() {
		return showHotel;
	}
	
	public List<TipoCamere_HotelDTO> getCamere(){
		HotelDTO h = getHotelPerParametro();
		camere = showHotel.camereHotel(h.getIdHotel());
		return camere;
	}
	
	public List<String> getNomiCamere(){
		camere = getCamere();
		nomiCamere = new ArrayList<String>();
		for(int i = 0; i < camere.size(); i++){
			TipoCamera tc = camere.get(i).getTipo();
			nomiCamere.add(tc.getString(tc));
		}
		return nomiCamere;
	}
	
	public List<BigDecimal> getPrezziCamere(){
		camere = getCamere();
		prezziCamere = new ArrayList<BigDecimal>();
		for(int i = 0; i < camere.size(); i++){
			BigDecimal b = camere.get(i).getPrezzo();
			prezziCamere.add(b);
		}
		return prezziCamere;
	}

	public void setShowHotel(ShowHotelLocal showHotel) {
		this.showHotel = showHotel;
	}	
	


}
