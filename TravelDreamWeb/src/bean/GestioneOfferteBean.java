package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.HotelDTO;
import DTO.PacchettoDTO;

@ManagedBean(name = "go")
public class GestioneOfferteBean {

	@EJB
	private GestioneOfferteLocal gestioneOfferte;
	
	@EJB
	private DatiStaticiLocal datiStatici;
	
	private String cittaSelezionata;
	private List<String> hotels;
	
	public GestioneOfferteBean(){
		
	}

	public String continua(){
		gestioneOfferte.setCitta(cittaSelezionata);
		System.out.println("WAAAAAAAAAAAAAAAAAAAAAAAA"+gestioneOfferte.getCitta());

		return "/impiegato/creaPacchetto2?faces-redirect=true";
	}
	
	public String getCitta(){
		return this.gestioneOfferte.getCitta();
	}

	public GestioneOfferteLocal getGestioneOfferte() {
		return gestioneOfferte;
	}



	public void setGestioneOfferte(GestioneOfferteLocal gestioneOfferte) {
		this.gestioneOfferte = gestioneOfferte;
	}





	public String getCittaSelezionata() {
		return cittaSelezionata;
	}

	public void setCittaSelezionata(String cittaSelezionata) {
		this.cittaSelezionata = cittaSelezionata;
	}
	
	public List<String> getHotels(){
		hotels = new ArrayList<String>();
	/*	List<HotelDTO> h = hotelPerCitta(this.pacchetto.getCitta());
		for(int i = 0; i < h.size(); i++){
			hotels.add(h.get(i).getNome());
		}*/
		return hotels;
	}
	public List<HotelDTO> hotelPerCitta(String s) {
		List<HotelDTO> h = datiStatici.hotelPerCitta(s);
		return h;
	}
	
}
