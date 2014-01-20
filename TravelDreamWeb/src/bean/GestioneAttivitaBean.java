package bean;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.AttivitaDTO;

@ManagedBean(name = "ga")
public class GestioneAttivitaBean {

	@EJB
	private GestioneComponentiLocal gc;
	
	private AttivitaDTO attivita;
	private int ore;
	private int minuti;
	
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
	
	/*public List<String> getGiorniList(){
		List<String> s = new ArrayList<String>();
		for(int i=1;i<=31;i++)
			s.add(""+i);
		return s;
	}
	
	public List<String> getAnniList(){
		List<String> s = new ArrayList<String>();
		for(int i=2010;i<=2025;i++)
			s.add(""+i);
		return s;
	}
	
	public List<String> getMesiList(){
		List<String> s = new ArrayList<String>();
		s.add("Gennaio");
		s.add("Febbraio");
		s.add("Marzo");
		s.add("Aprile");
		s.add("Maggio");
		s.add("Giugno");
		s.add("Luglio");
		s.add("Agosto");
		s.add("Settembre");
		s.add("Ottobre");
		s.add("Novembre");
		s.add("Dicembre");
		return s;
	}
	
	public int getMeseDaString(String s){
		switch(s){
		//Non so perche', ma vuole un numero in meno per il mese...
			case "Gennaio":
				return 0;
			case "Febbraio":
				return 1;
			case "Marzo":
				return 2;
			case "Aprile":
				return 3;
			case "Maggio":
				return 4;
			case "Giugno":
				return 5;
			case "Luglio":
				return 6;
			case "Agosto":
				return 7;
			case "Settembre":
				return 8;
			case "Ottobre":
				return 9;
			case "Novembre":
				return 10;
			case "Dicembre":
				return 11;
		}
		return 0;
	}*/

}
