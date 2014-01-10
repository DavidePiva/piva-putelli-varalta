package bean;



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
	
	public String nomeUtente(){
		String s =  gestioneprofili.getUtenteDTO().getNome();
		return s;
		//return "BOE!";
	}

}
