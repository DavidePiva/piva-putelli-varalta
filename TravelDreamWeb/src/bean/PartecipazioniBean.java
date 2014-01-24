package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.UtenteDTO;
import DTO.ViaggioDTO;

@ManagedBean(name = "par")
@RequestScoped
public class PartecipazioniBean {
	
	
	
	@EJB
	private ShowViaggioLocal showViaggio;
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	private List<ViaggioDTO> partecipazioni;
	public List<InfoViaggio> infoPartecipazioni;
	private int idViaggio;
	private ViaggioDTO viaggio;
	private BigDecimal totale;
	private List<UtenteDTO> utentiInvitati;
	private String utenteSelezionato;
	
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}
	
	public void setUtenteSelezionato(String utenteSelezionato){
		this.utenteSelezionato = utenteSelezionato;
	}
	
	public String getUtenteSelezionato(){
		return utenteSelezionato;
	}
	
	public List<UtenteDTO> getUtentiInvitati(){
		utentiInvitati = showViaggio.getUtentiInvitati(idViaggio);
		return utentiInvitati;
	}
	
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
			a.setTitolare(partecipazioni.get(i).getNomeTitolare());
			infoPartecipazioni.add(a);
		}
		return infoPartecipazioni;
	}
	
	public ViaggioDTO getViaggio(){
		viaggio = gestioneViaggi.getViaggio(idViaggio);
		return viaggio;
	}
	
    public BigDecimal getTotale(){
    	getViaggio();
    	BigDecimal p= this.viaggio.getPrezzo();
    	BigDecimal totale=p.multiply(new BigDecimal(viaggio.getNumeroPersone()));
    	return totale;
    }
	
}
