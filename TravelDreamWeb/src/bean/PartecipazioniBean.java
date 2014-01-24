package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.ViaggioDTO;

@ManagedBean(name = "par")
@RequestScoped
public class PartecipazioniBean {
	
	@EJB
	private ShowViaggioLocal showViaggio;
	private List<ViaggioDTO> partecipazioni;
	public List<InfoViaggio> infoPartecipazioni;
	
	public List<ViaggioDTO> getPartecipazioni(String emailUtente){
		partecipazioni = showViaggio.getPartecipazioni(emailUtente);
		return partecipazioni;
	}
	
	public List<InfoViaggio> getInfoPartecipazioni(String emailUtente){
		partecipazioni = getPartecipazioni(emailUtente);
		infoPartecipazioni = new ArrayList<InfoViaggio>();
		for(int i = 0; i < partecipazioni.size(); i++){
			InfoViaggio a = new InfoViaggio();
			a.setCitta(partecipazioni.get(i).getCitta());
			a.setIdViaggio(partecipazioni.get(i).getIdViaggio());
			a.setNumeroPersone(partecipazioni.get(i).getNumeroPersone());
			a.setTitolare(partecipazioni.get(i).getTitolare());
			infoPartecipazioni.add(a);
		}
		return infoPartecipazioni;
	}
	
	
}
