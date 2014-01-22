package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="gv")
@RequestScoped
public class GestioneViaggiBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	private int idViaggio;
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}
	
	public String creaViaggio(String nomePacchetto, String emailUtente){
		gestioneViaggi.creaViaggio(nomePacchetto, emailUtente);
		return "/user/index?faces-redirect=true";
	}
	
	public String modificaViaggio(int idViaggio){
		return "/user/modificaViaggio?faces-redirect=true";
	}
}
