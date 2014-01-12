package bean;
//ManageBean statico

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AeroportoDTO;

@ManagedBean(name="ds")
@RequestScoped
public class DatiStaticiBean {
	
	@EJB
	private DatiStaticiLocal datistatici;
	private AeroportoDTO aero;
	
	public String esempio(){
		return datistatici.esempio();
	}
	
	public String nomeAeroporto(){
		aero = datistatici.getAeroportoDTO();
		return aero.getNome();
	}
	
	private List<String> citta;
	
	public List<String> getCitta(){
		citta= datistatici.getDestinazioni();
		return citta;
	}
	
	
	public String citta(int i){
		List<String> c = datistatici.getDestinazioni();
		return c.get(i);
	}
	
}
