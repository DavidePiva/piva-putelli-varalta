package bean;

import java.math.BigDecimal;
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
	private List<BigDecimal> prezziViaggi;
	private List<Integer> numeriPersone;
	private List<Integer> idViaggi;
	
	public List<ViaggioDTO> getViaggiUtente(String email){
		viaggiUtente = showViaggio.getViaggiUtente(email);
		return viaggiUtente;
	}
	
	public List<String> getCittaViaggiUtente(String email){
		cittaViaggiUtente = new ArrayList<String>();
		viaggiUtente = getViaggiUtente(email);
		for(int i = 0; i < viaggiUtente.size(); i++){
			cittaViaggiUtente.add(viaggiUtente.get(i).getCitta());
		}
		return cittaViaggiUtente;
	}
	
	public List<BigDecimal> getPrezziViaggi(String email){
		viaggiUtente = getViaggiUtente(email);
		prezziViaggi = new ArrayList<BigDecimal>();
		for(int i = 0; i < viaggiUtente.size(); i++){
			prezziViaggi.add(viaggiUtente.get(i).getPrezzo());
		}
		return prezziViaggi;
	}
	
	public List<Integer> getNumeriPersone(String email){
		viaggiUtente = getViaggiUtente(email);
		numeriPersone = new ArrayList<Integer>();
		for(int i = 0; i < viaggiUtente.size(); i++){
			numeriPersone.add(viaggiUtente.get(i).getNumeroPersone());
		}
		return numeriPersone;
	}
	
	public List<Integer> getIdViaggi(String email){
		viaggiUtente = getViaggiUtente(email);
		idViaggi = new ArrayList<Integer>();
		for(int i = 0; i < viaggiUtente.size(); i++){
			idViaggi.add(viaggiUtente.get(i).getIdViaggio());
		}
		return idViaggi;
	}
	
}