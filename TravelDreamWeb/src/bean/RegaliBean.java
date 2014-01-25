package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import DTO.UtenteDTO;

@ManagedBean(name = "rb")
@ViewScoped
public class RegaliBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	@EJB
	private ShowViaggioLocal showViaggio;
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
		gestioneViaggi.aggiungiDonatore(idViaggio,emailSelezionata);
		return "/user/regali?faces-redirect=true&id="+idViaggio;
	}
	
	public List<UtenteDTO> getListaDonatori(){
		listaDonatori = showViaggio.getDonatori(idViaggio);
		return listaDonatori;
	}

}
