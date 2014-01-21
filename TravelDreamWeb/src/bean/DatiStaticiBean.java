package bean;
//ManageBean statico

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AeroportoDTO;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.VoloDTO;

@ManagedBean(name="ds")
@RequestScoped
public class DatiStaticiBean {
	
	private static final int SOGLIA_1 = 1000;
	private static final int SOGLIA_2 = 2500;
	private static final int SOGLIA_3 = 5000;
	private int prezzoMinimo;
	private int prezzoMassimo;
	private String cittaSelezionata;
	private String tipoSelezionato;
	private String hotelSelezionato;
	private List<String> citta;
	private List<String> hotels;
	private List<Integer> ids;
	private List<String> pacchetti;
	private List<String> pacchettiPerTipo;
	private List<String> tipiPacchetto;
	private List<String> pacchettiPerPrezzo;
	private List<String> pacchettiPerHotel;
	private List<String> voliPossibili;
	private List<String> attivitaPossibili;
	private List<String> pernottamentiPossibili;
	
	@EJB
	private DatiStaticiLocal datistatici;
	
	
	public List<String> getTipiPacchetto(){
		tipiPacchetto = tipiPacchetto();
		return tipiPacchetto;
	}
	
	public List<String> getPacchettiPerHotel(){
		pacchettiPerHotel = new ArrayList<String>();
		List<PacchettoDTO> l = datistatici.pacchettiPerHotel(hotelSelezionato);
		for(int i = 0; i < l.size(); i++){
			pacchettiPerHotel.add(l.get(i).getTitolo());
		}
		return pacchettiPerHotel;
	}
	
	public int getPrezzoMinimo(){
		return prezzoMinimo;
	}
	
	public int getPrezzoMassimo(){
		return prezzoMassimo;
	}
	
	public void setHotelSelezionato(String hotelSelezionato){
		this.hotelSelezionato = hotelSelezionato;
	}
	
	public String getHotelSelezionato(){
		return hotelSelezionato;
	}
	
	public void setPrezzoMinimo(int prezzoMinimo){
		this.prezzoMinimo = prezzoMinimo;
	}
	
	public void setPrezzoMassimo(int prezzoMassimo){
		this.prezzoMassimo = prezzoMassimo;
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
	
	public List<String> getPacchettiPerPrezzo(){
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		if(prezzoMassimo!=-1)
		{
			l1 = datistatici.pacchettiPerPrezzo(prezzoMinimo,prezzoMassimo);
		}
		else{
			l1 = datistatici.pacchettiPerPrezzo(prezzoMinimo);
		}
		pacchettiPerPrezzo = new ArrayList<String>();
		for(int i = 0; i < l1.size(); i++){
			pacchettiPerPrezzo.add(l1.get(i).getTitolo());
		}
		return pacchettiPerPrezzo;
	}
	
	public boolean getPrezzoMinimoSettato(){
		if(prezzoMinimo>0)
			return true;
		return false;
	}
	
	public boolean getPrezzoMassimoSettato(){
		if(prezzoMassimo!=-1)
			return true;
		return false;
	}
	
	public boolean getNotPrezzoMassimoSettato(){
		if(prezzoMassimo!=-1)
			return false;
		return true;
	}
	
	public List<String> getVoliPossibili(String cittaPartenza, String cittaArrivo, int anno, int mese, int giorno){
		List<VoloDTO> l = datistatici.getVoliPossibili(cittaPartenza, cittaArrivo, anno, mese, giorno);
		voliPossibili = new ArrayList<String>();
		for(int i = 0; i < l.size(); i++){
			VoloDTO v = l.get(i);
			voliPossibili.add(v.toString());
		}
		return voliPossibili;
	}
	
	public List<String> getAttivitaPossibili(String citta, int anno1, int mese1, int giorno1, int anno2, int mese2, int giorno2){
		List<AttivitaDTO> l = datistatici.getAttivitaPossibili(citta,anno1,mese1,giorno1,anno2,mese2,giorno2);
		attivitaPossibili = new ArrayList<String>();
		for(int i = 0; i < l.size(); i++){
			attivitaPossibili.add(l.get(i).getTitolo());
		}
		return attivitaPossibili;
	}
	
	public List<String> getPernottamentiPossibili(String citta){
		List<PernottamentoDTO> l = datistatici.getPernottamentiPossibili(citta);
		pernottamentiPossibili = new ArrayList<String>();
		List<HotelDTO> h = hotelPerCitta(citta);
		for(int i = 0; i < l.size(); i++){
			for(int j = 0; j < h.size(); j++){
				int idHotel = h.get(j).getIdHotel();
				if(l.get(i).getIdHotel()==idHotel){
					String nome = h.get(j).getNome();
					pernottamentiPossibili.add(nome);
				}
			}
		}
		return pernottamentiPossibili;
	}

}
