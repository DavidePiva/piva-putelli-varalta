package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.ViaggioDTO;

@ManagedBean(name="sv")
@RequestScoped
public class ShowViaggioBean {
	
	@EJB
	private ShowViaggioLocal showViaggio;
	private List<String> cittaViaggiUtente;
	private List<ViaggioDTO> viaggiUtente;
	
	public List<String> getCittaViaggiUtente(String email){
		cittaViaggiUtente = new ArrayList<String>();
		List<ViaggioDTO> viaggiUtente = showViaggio.getViaggiUtente(email);
		for(int i = 0; i < viaggiUtente.size(); i++){
			cittaViaggiUtente.add(viaggiUtente.get(i).getCitta());
		}
		return cittaViaggiUtente;
	}
	
	
}
