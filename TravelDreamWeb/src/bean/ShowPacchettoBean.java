package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.PacchettoDTO;

@ManagedBean(name="sp")
@RequestScoped
public class ShowPacchettoBean {
	
	private String nomePacchetto;
	
	@EJB
	private ShowPacchettoLocal showPacchetto;
	
	public void setNomePacchetto(String nomePacchetto){
		this.nomePacchetto=nomePacchetto;
	}
	
	public String getNomePacchetto(){
		return nomePacchetto;
	}
	
	public PacchettoDTO getPacchettoPerParametro(){
		PacchettoDTO p = showPacchetto.getPacchetto(nomePacchetto);
		return p;
	}
	
	public String getDescrizione(){
		PacchettoDTO p = getPacchettoPerParametro();
		return p.getDescrizione();
	}
}
