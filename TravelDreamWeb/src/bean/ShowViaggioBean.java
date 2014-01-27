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
	private List<InfoViaggio> infoViaggi;
	private List<ViaggioDTO> pagati;
	private List<InfoViaggio> viaggiPagati;
	
	public List<ViaggioDTO> getViaggiUtente(String email){
		viaggiUtente = showViaggio.getViaggiUtente(email);
		return viaggiUtente;
	}
	
	public List<ViaggioDTO> getPagati(String email){
		pagati = showViaggio.getPagati(email);
		return pagati;
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
	
	public List<InfoViaggio> getInfoViaggi(String email){
		viaggiUtente = getViaggiUtente(email);
		infoViaggi = new ArrayList<InfoViaggio>();
		for(int i = 0; i < viaggiUtente.size(); i++){
			InfoViaggio a = new InfoViaggio();
			a.setCitta(viaggiUtente.get(i).getCitta());
			a.setIdViaggio(viaggiUtente.get(i).getIdViaggio());
			a.setNumeroPersone(viaggiUtente.get(i).getNumeroPersone());
			a.setTitolare(viaggiUtente.get(i).getTitolare());
			infoViaggi.add(a);
		}
		return infoViaggi;
	}
	
	public List<InfoViaggio> viaggiPagati(String email){
		pagati = getPagati(email);
		viaggiPagati = new ArrayList<InfoViaggio>();
		for(int i = 0; i < pagati.size(); i++){
			InfoViaggio a = new InfoViaggio();
			a.setCitta(pagati.get(i).getCitta());
			a.setIdViaggio(pagati.get(i).getIdViaggio());
			a.setNumeroPersone(pagati.get(i).getNumeroPersone());
			a.setTitolare(pagati.get(i).getTitolare());
			viaggiPagati.add(a);
		}
		return viaggiPagati;
	}
	
	
	
}
