package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.ViaggioDTO;

@ManagedBean(name = "par")
@RequestScoped
public class PartecipazioniBean {
	
	private List<ViaggioDTO> partecipazioni;
	
	public List<ViaggioDTO> getPartecipazioni(){
		return partecipazioni;
	}
}
