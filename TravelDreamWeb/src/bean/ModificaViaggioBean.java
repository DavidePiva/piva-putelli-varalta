package bean;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="mv")
@ViewScoped
public class ModificaViaggioBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	private int idViaggio;
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}
}
