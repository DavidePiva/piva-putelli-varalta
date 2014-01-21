package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="gv")
@RequestScoped
public class GestioneViaggiBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	public String creaViaggio(String nomePacchetto, String emailUtente){
		gestioneViaggi.creaViaggio(nomePacchetto, emailUtente);
		return "/user/index?faces-redirect=true";
	}
}
