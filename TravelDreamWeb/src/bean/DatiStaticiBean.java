package bean;
//ManageBean statico

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import DTO.AeroportoDTO;
import DTO.HotelDTO;

@ManagedBean(name="ds")
@RequestScoped
public class DatiStaticiBean {
	
	private String cittaSelezionata;
	
	@EJB
	private DatiStaticiLocal datistatici;
	
	
	public String getCittaSelezionata() {
		return cittaSelezionata;
	}

	public void setCittaSelezionata(String cittaSelezionata) {
		this.cittaSelezionata = cittaSelezionata;
	}

	private AeroportoDTO aero;
	
	public String esempio(){
		return datistatici.esempio();
	}
	
	public String nomeAeroporto(){
		aero = datistatici.getAeroportoDTO();
		return aero.getNome();
	}
	
	private List<String> citta;
	
	public List<String> getCitta(){
		citta= datistatici.getDestinazioni();
		for(String s : citta)
			System.out.println(s);
		return citta;
		
	}
	
	public String citta(int i){
		List<String> c = datistatici.getDestinazioni();
		return c.get(i);
	}
	
	public List<HotelDTO> hotelPerCitta(String s) {
		return datistatici.hotelPerCitta(s);
	}
	
	public String nome(){
		List<HotelDTO> h = hotelPerCitta(cittaSelezionata);
		HotelDTO h2 = h.get(0);
		return h2.getNome();
	}
	
	
}
