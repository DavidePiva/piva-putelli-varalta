package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.AttivitaDTO;

@ManagedBean(name = "gc")
public class GestioneComponentiBean {

	@EJB
	private GestioneComponentiLocal gc;
	
	private AttivitaDTO attivita;
	private int ore;
	private int minuti;
	private int anno;
	private int giorno;
	private int mese;
	
	public GestioneComponentiBean(){
		this.attivita=new AttivitaDTO();
	}
	
	public String creaAttivita(){
		AttivitaDTO pippo=new AttivitaDTO();		
		Date data=new Date(anno,mese,giorno);
		Time orario=new Time(ore,minuti,0);
		attivita.setData(data);
		attivita.setOra(orario);
		gc.creaAttivita(attivita);
		return "/index?faces-redirect=true";
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

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getGiorno() {
		return giorno;
	}

	public void setGiorno(int giorno) {
		this.giorno = giorno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}
	
	
}
