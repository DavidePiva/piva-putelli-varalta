package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;

import DTO.UtenteDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;

@ManagedBean(name = "rb")
@ViewScoped
public class RegaliBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	@EJB
	private ShowViaggioLocal showViaggio;

	@EJB
	private DatiStaticiLocal datistatici;
	@EJB
	private GestioneProfiliLocal gestioneProfili;
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",   message="Email non valida")	
	private String emailSelezionata;
	private String utenteSelezionato;
	private int idViaggio;
	private List<UtenteDTO> listaDonatori;
	private List<ViaggioDTO> viaggiDaRegalare;
	private List<InfoViaggio> infoRegali;
	private int voloScelto;
	private int attivitaScelta;
	
	public void setAttivitaScelta(int attivitaScelta){
		this.attivitaScelta = attivitaScelta;
	}
	
	public int getAttivitaScelta(){
		return attivitaScelta;
	}
	
	public void setVoloScelto(int voloScelto){
		this.voloScelto = voloScelto;
	}
	
	public int getVoloScelto(){
		return voloScelto;
	}
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}
	
	public void setEmailSelezionata(String emailSelezionata){
		this.emailSelezionata = emailSelezionata;
	}
	
	public String getEmailSelezionata(){
		return emailSelezionata;
	}
	
	public void setUtenteSelezionato(String utenteSelezionato){
		this.utenteSelezionato = utenteSelezionato;
	}
	
	public String getUtenteSelezionato(){
		return utenteSelezionato;
	}
	
	public String aggiungiDonatore(){
		List<String> utenti = datistatici.getListaUtenti();
		if(utenti.contains(emailSelezionata))
		{
			gestioneViaggi.aggiungiDonatore(idViaggio,emailSelezionata);
		}
		else{
			gestioneProfili.salvaUtenteProvvisorio(emailSelezionata);
			gestioneViaggi.aggiungiDonatore(idViaggio,emailSelezionata);
		}
		return "/user/regali?faces-redirect=true&id="+idViaggio;
	}
	
	public String rimuoviDonatore(){
		gestioneViaggi.rimuoviDonatore(idViaggio,utenteSelezionato);
		return "/user/regali?faces-redirect=true&id="+idViaggio;
	}
	
	public List<UtenteDTO> getListaDonatori(){
		listaDonatori = showViaggio.getDonatori(idViaggio);
		return listaDonatori;
	}
	
	public List<ViaggioDTO> getViaggiDaRegalare(String emailUtente){
		viaggiDaRegalare = showViaggio.getViaggiDaRegalare(emailUtente);
		return viaggiDaRegalare;
	}
	
	public List<InfoViaggio> getInfoRegali(String emailUtente){
		viaggiDaRegalare = getViaggiDaRegalare(emailUtente);
		infoRegali = new ArrayList<InfoViaggio>();
		for(int i = 0; i < viaggiDaRegalare.size(); i++){
			InfoViaggio a = new InfoViaggio();
			a.setCitta(viaggiDaRegalare.get(i).getCitta());
			a.setIdViaggio(viaggiDaRegalare.get(i).getIdViaggio());
			a.setNumeroPersone(viaggiDaRegalare.get(i).getNumeroPersone());
			a.setTitolare(viaggiDaRegalare.get(i).getNomeTitolare());
			infoRegali.add(a);
		}
		return infoRegali;
	}
	
	public String pagaPernottamento(String emailUtente,int idViaggio){
		ViaggioDTO v = gestioneViaggi.getViaggio(idViaggio);
		int idPernottamento = v.getIdPernottamento();
		return "/user/pagamentoPernottamento?faces-redirect=true&idViaggio="+idViaggio+"&email="+emailUtente+"&idPer"+idPernottamento;
	}

}
