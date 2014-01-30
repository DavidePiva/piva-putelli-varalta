package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import DTO.AttivitaDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;

@ManagedBean(name="mv")
@ViewScoped
public class ModificaViaggioBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	@EJB
	private DatiStaticiLocal datistatici;

	
	private int idViaggio;
	private ViaggioDTO viaggioDTO;
	private Date dataAndata;
	private Date dataRitorno;
	private int paginaSelezionata;
	private int idAttivitaDaEliminare;
	private int idAttivitaDaAggiungere;

	

	public int getPaginaSelezionata() {
		return paginaSelezionata;
	}


	public void setPaginaSelezionata(int paginaSelezionata) {
		this.paginaSelezionata = paginaSelezionata;
	}


	public ModificaViaggioBean() {
		setViaggioDTO(new ViaggioDTO());
	}

	
	public void continua(){
		this.setPaginaSelezionata(1);
	}
	public String salva(){
		viaggioDTO.setIdViaggio(idViaggio);
		gestioneViaggi.gestoreDate(viaggioDTO, viaggioDTO.getIdVoloAndata(), viaggioDTO.getIdVoloRitorno());
    	return "/user/modificaAttivita?faces-redirect=true&id="+idViaggio;
	}
	public boolean primaPaginaVisibile(){
		if(paginaSelezionata==0)
			return true;
		return false;
	}
	public boolean secondaPaginaVisibile(){
		if(paginaSelezionata==1)
			return true;
		return false;
	}

	public List<VoloDTO> voliPossibiliAndata(){
		String cittaPartenza = datistatici.getAeroportoDTO(datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloAndata()).getIdAeroportoPartenza()).getCitta();
		String cittaArrivo = datistatici.getAeroportoDTO(datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloRitorno()).getIdAeroportoPartenza()).getCitta();
		System.out.println("Viaggi di andata da "+cittaPartenza+" a "+cittaArrivo);
		return datistatici.getVoliPossibili(cittaPartenza, cittaArrivo, dataAndata.getYear()+1900, dataAndata.getMonth()+1, dataAndata.getDate());
	}
	public List<VoloDTO> voliPossibiliRitorno(){
		String cittaPartenza = datistatici.getAeroportoDTO(datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloRitorno()).getIdAeroportoPartenza()).getCitta();
		String cittaArrivo = datistatici.getAeroportoDTO(datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloAndata()).getIdAeroportoPartenza()).getCitta();
		System.out.println("Viaggi di ritorno da "+cittaPartenza+" a "+cittaArrivo);
		return datistatici.getVoliPossibili(cittaPartenza, cittaArrivo, dataRitorno.getYear()+1900, dataRitorno.getMonth()+1, dataRitorno.getDate());
	}

	public String eliminaAttivitaDaViaggio(){
		gestioneViaggi.rimuoviAttivita(idAttivitaDaEliminare, idViaggio);
    	return "/user/modificaAttivita?faces-redirect=true&id="+idViaggio;
	}
	
	public String aggiungiAttivitaAlViaggio(){
		gestioneViaggi.aggiungiAttivita(idAttivitaDaAggiungere, idViaggio);
    	return "/user/modificaAttivita?faces-redirect=true&id="+idViaggio;
	}
	
	
    public List<AttivitaDTO> getAttivita(){
    	return gestioneViaggi.getAttivitaViaggio(idViaggio);
    }
	
	//getter and setter
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}

	public ViaggioDTO getViaggioDTO() {
		return viaggioDTO;
	}

	public void setViaggioDTO(ViaggioDTO viaggioDTO) {
		this.viaggioDTO = viaggioDTO;
	}

	public Date getDataAndata() {
		return datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloAndata()).getData();
	}

	public void setDataAndata(Date dataAndata) {
		this.dataAndata = dataAndata;
	}

	public Date getDataRitorno() {
		return datistatici.getVoloDTO(datistatici.getViaggioDTO(idViaggio).getIdVoloRitorno()).getData();
	}

	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
	}


	public int getIdAttivitaDaEliminare() {
		return idAttivitaDaEliminare;
	}


	public void setIdAttivitaDaEliminare(int idAttivitaDaEliminare) {
		this.idAttivitaDaEliminare = idAttivitaDaEliminare;
	}
	
	public List<AttivitaDTO> getAttivitaNonSelezionateViaggio(){
		return gestioneViaggi.getAttivitaNonSelezionateViaggio(idViaggio);
	}


	public int getIdAttivitaDaAggiungere() {
		return idAttivitaDaAggiungere;
	}


	public void setIdAttivitaDaAggiungere(int idAttivitaDaAggiungere) {
		this.idAttivitaDaAggiungere = idAttivitaDaAggiungere;
	}
	
}
