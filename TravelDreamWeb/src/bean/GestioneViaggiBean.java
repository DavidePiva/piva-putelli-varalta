package bean;

import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import DTO.ViaggioDTO;

@ManagedBean(name="gv")
@RequestScoped
public class GestioneViaggiBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	@ManagedProperty("#{param.id}")
	private int idViaggio;
	private ViaggioDTO viaggio;
	
	public GestioneViaggiBean(){
		this.viaggio=new ViaggioDTO();
		
	}
	
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

}
