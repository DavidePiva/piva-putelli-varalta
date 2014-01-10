package bean;
//ManageBean statico
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
	
}
