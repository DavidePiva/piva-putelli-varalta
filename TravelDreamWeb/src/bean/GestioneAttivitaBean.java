package bean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.AttivitaDTO;
import DTO.HotelDTO;

@ManagedBean(name = "ga")
public class GestioneAttivitaBean {

	@EJB
	private GestioneComponentiLocal gc;
	@EJB
	private ShowAttivitaLocal sa;
	
	private AttivitaDTO attivita;
	private int ore;
	private int minuti;
	private String nomeAttivitaDaModificare;
	
	public GestioneAttivitaBean(){
		this.attivita=new AttivitaDTO();
	}
	
	public String creaAttivita(){
		AttivitaDTO pippo=new AttivitaDTO();		

		Time orario=new Time(ore,minuti,0);
		attivita.setOra(orario);

		gc.creaAttivita(attivita);
		return "/impiegato/index?faces-redirect=true";
	}

	public GestioneComponentiLocal getGc() {
		return gc;
	}

	public void setGc(GestioneComponentiLocal gc) {
		this.gc = gc;
	}

	public AttivitaDTO getAttivita() {
		return attivita;
	}

	public void setAttivita(AttivitaDTO attivita) {
		this.attivita = attivita;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public List<String> getOreList(){
		List<String> s = new ArrayList<String>();
		for(int i=0;i<24;i++)
			s.add((i<10 ? "0" : "")+i);
		return s;
	}
	
	public List<String> getMinutiList(){
		List<String> s = new ArrayList<String>();
		for(int i=0;i<60;i=i+5)
			s.add((i<10 ? "0" : "")+i);
		return s;
	}
	
	public String modificaAttivita(){
	/*	this.attivita.setSelezionabile(true);
		AttivitaDTO attivitaDaModificare=sa.getAttivita();
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
		gc.modificaHotel(hotel);*/
		return "/impiegato/index?faces-redirect=true";
	}

	public ShowAttivitaLocal getSa() {
		return sa;
	}

	public void setSa(ShowAttivitaLocal sa) {
		this.sa = sa;
	}

	public String getNomeAttivitaDaModificare() {
		return nomeAttivitaDaModificare;
	}

	public void setNomeAttivitaDaModificare(String nomeAttivitaDaModificare) {
		this.nomeAttivitaDaModificare = nomeAttivitaDaModificare;
	}

}
