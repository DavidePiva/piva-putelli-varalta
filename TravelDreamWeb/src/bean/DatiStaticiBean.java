package bean;
//ManageBean statico

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AeroportoDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;

@ManagedBean(name="ds")
@RequestScoped
public class DatiStaticiBean {
	
	private static final int SOGLIA_1 = 1000;
	private static final int SOGLIA_2 = 2500;
	private static final int SOGLIA_3 = 5000;
	private String cittaSelezionata;
	private String tipoSelezionato;
	private List<String> citta;
	private List<String> hotels;
	private List<Integer> ids;
	private List<String> pacchetti;
	private List<String> pacchettiPerTipo;
	private List<String> tipiPacchetto;
	
	@EJB
	private DatiStaticiLocal datistatici;
	
	
	public List<String> getTipiPacchetto(){
		tipiPacchetto = tipiPacchetto();
		return tipiPacchetto;
	}
	
	public int getSOGLIA_1(){
		return SOGLIA_1;
	}
	
	public int getSOGLIA_2(){
		return SOGLIA_2;
	}
	
	public int getSOGLIA_3(){
		return SOGLIA_3;
	}
	
	public String getCittaSelezionata() {
		return cittaSelezionata;
	}

	public void setCittaSelezionata(String cittaSelezionata) {
		this.cittaSelezionata = cittaSelezionata;
	}
	
	public String getTipoSelezionato(){
		return tipoSelezionato;
	}
	
	public void setTipoSelezionato(String tipoSelezionato){
		this.tipoSelezionato = tipoSelezionato;
	}

	private AeroportoDTO aero;
	
	public String esempio(){
		return datistatici.esempio();
	}
	
	public String nomeAeroporto(){
		aero = datistatici.getAeroportoDTO();
		return aero.getNome();
	}
	
	
	public List<String> getCitta(){
		citta= datistatici.getDestinazioni();
		for(String s : citta)
			System.out.println(s);
		return citta;
		
	}
	
	public List<String> getHotels(){
		hotels = new ArrayList<String>();
		List<HotelDTO> h = hotelPerCitta(cittaSelezionata);
		for(int i = 0; i < h.size(); i++){
			hotels.add(h.get(i).getNome());
		}
		return hotels;
	}
	
	public List<Integer> getIds(){
		ids = new ArrayList<Integer>();
		List<HotelDTO> h = hotelPerCitta(cittaSelezionata);
		for(int i = 0; i < h.size(); i++){
			ids.add(h.get(i).getIdHotel());
		}
		return ids;
	}
	
	public String citta(int i){
		List<String> c = datistatici.getDestinazioni();
		return c.get(i);
	}
	
	public List<HotelDTO> hotelPerCitta(String s) {
		List<HotelDTO> h = datistatici.hotelPerCitta(s);
		return h;
	}
	
	public String nome(){
		List<HotelDTO> h = hotelPerCitta(cittaSelezionata);
		HotelDTO h2 = h.get(0);
		return h2.getNome();
	}
	
	public String pacchetto(){
		List<PacchettoDTO> lp = pacchettiPerCitta(cittaSelezionata);
		PacchettoDTO p = lp.get(0);
		return p.getTitolo();
	}

	private List<PacchettoDTO> pacchettiPerCitta(String s) {
		return datistatici.pacchettiPerCitta(s);
	}
	
	public List<String> getPacchetti(){
		List<PacchettoDTO> l1 = pacchettiPerCitta(cittaSelezionata);
		pacchetti = new ArrayList<String>();
		for(int i = 0; i < l1.size(); i++){
			pacchetti.add(l1.get(i).getTitolo());
		}
		return pacchetti;
	}
	
	public List<String> getPacchettiPerTipo(){
		List<PacchettoDTO> l1 = datistatici.pacchettiPerTipo(tipoSelezionato);
		pacchettiPerTipo = new ArrayList<String>();
		for(int i = 0; i < l1.size(); i++){
			pacchettiPerTipo.add(l1.get(i).getTitolo());
		}
		return pacchettiPerTipo;
	}
	
	public List<String> tipiPacchetto(){
		return datistatici.tipiPacchetto();
	}
	
	
}
