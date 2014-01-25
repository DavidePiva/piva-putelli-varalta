package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "rb")
@ViewScoped
public class RegaliBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	private String emailSelezionata;
	private int idViaggio;
	
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
	
	public String aggiungiDonatore(){
		gestioneViaggi.aggiungiDonatore(idViaggio,emailSelezionata);
		return "/user/regali?faces-redirect=true&id="+idViaggio;
	}

}
