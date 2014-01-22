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
	@EJB 
	private DatiStaticiLocal ds;
	
	
	private AttivitaDTO attivita;
	private int ore;
	private int minuti;
	private String nomeAttivitaDaModificare;
	private int idAttivitaDaEliminare;
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
	
	public String eliminaAttivita(){
		gc.eliminaAttivita(idAttivitaDaEliminare);
		return "/impiegato/index?faces-redirect=true";
	}
	
	public String modificaAttivitaUguali(){
		
		List<AttivitaDTO> list=new ArrayList<AttivitaDTO>();
		list=ds.attivitaPerTitolo(nomeAttivitaDaModificare);
		for(int i=0;i<list.size();i++){
			modificaAttivita(list.get(i));
		}
		
		return "/impiegato/index?faces-redirect=true";
	}
	
	public List<AttivitaDTO> getListaAttivita(){
		return ds.attivitaDTO();
		
	}
	
	private void modificaAttivita(AttivitaDTO attivitaDaModificare){
		this.attivita.setSelezionabile(true);
		attivita.setId(attivitaDaModificare.getId());
		attivita.setCitta(attivitaDaModificare.getCitta());
		attivita.setData(attivitaDaModificare.getData());
		Time orario=new Time(ore,minuti,0);
		attivita.setOra(orario);
		if(attivita.getDescrizione().equals("")){
			attivita.setDescrizione(attivitaDaModificare.getDescrizione());
		}
		if(attivita.getPrezzo().equals("")){
			attivita.setPrezzo(attivitaDaModificare.getPrezzo());
		}
		if(attivita.getTitolo().equals("")){
			attivita.setTitolo(attivitaDaModificare.getTitolo());
		}

		if(attivita.getFoto1().equals("")){
			attivita.setFoto1(attivitaDaModificare.getFoto1());
		}
		if(attivita.getFoto2().equals("")){
			attivita.setFoto2(attivitaDaModificare.getFoto2());
		}
		if(attivita.getFoto3().equals("")){
			attivita.setFoto3(attivitaDaModificare.getFoto3());
		}
		gc.modificaAttivita(attivita);
	
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

	public DatiStaticiLocal getDs() {
		return ds;
	}

	public void setDs(DatiStatici ds) {
		this.ds = ds;
	}

	public int getIdAttivitaDaEliminare() {
		return idAttivitaDaEliminare;
	}

	public void setIdAttivitaDaEliminare(int idAttivitaDaEliminare) {
		this.idAttivitaDaEliminare = idAttivitaDaEliminare;
	}

	public void setDs(DatiStaticiLocal ds) {
		this.ds = ds;
	}
	
}
