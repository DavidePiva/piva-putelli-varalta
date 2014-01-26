package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;

import DTO.UtenteDTO;

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

}
