package bean;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.UtenteDTO;

@ManagedBean(name = "gp")
@RequestScoped
public class GestioneProfiliBean {

	@EJB
	private GestioneProfiliLocal gestioneprofili;

	private UtenteDTO utente;
	public String utenteSelezionato;
	
	
	public String getUtenteSelezionato() {
		return utenteSelezionato;
	}


	public void setUtenteSelezionato(String utenteSelezionato) {
		this.utenteSelezionato = utenteSelezionato;
	}


	public GestioneProfiliBean() {
		this.utente=new UtenteDTO();
	}
	
	
	public UtenteDTO getUtente() {
		return utente;
	}


	public void setUtente(UtenteDTO utente) {
		this.utente = utente;	
	}


	public String salva() {
			this.utente.setAttivo(true);
			gestioneprofili.salva(utente);
			return "/index?faces-redirect=true";
	}
	
	public boolean loggato(){
		return gestioneprofili.loggato();
	}
	
	public boolean nonLoggato(){
		return !loggato();
	}
	
	public String getNomeUtente(){
		return gestioneprofili.getUtenteDTO().getNome();
		//return "BOE!";
	}
	
	public boolean isAdmin(){
		return gestioneprofili.isAdmin();
	}
	
	public boolean isImpiegato(){
		return gestioneprofili.isImpiegato();
	}
	
	public List<UtenteDTO> listaUtenti(){
		return gestioneprofili.listaUtenti();
	}
	
	public List<UtenteDTO> listaImpiegati(){
		return gestioneprofili.listaImpiegati();
	}
	
	public void rendiImpiegato(){
		gestioneprofili.rendiImpiegato(utenteSelezionato);
	}
	
	public void rendiUtente(){
		gestioneprofili.rendiUtente(utenteSelezionato);
	}

}
