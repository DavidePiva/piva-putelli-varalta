package bean;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



import javax.faces.bean.ViewScoped;

import DTO.ViaggioDTO;

@ManagedBean(name="pb")
@ViewScoped
public class PagamentoBean {
		
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	@EJB
	private ShowViaggioLocal showViaggio;
	
	private int idViaggio;
	private int numeroPersoneSelezionato;
	private int idPernottamento;
	private String donatore;
	private ViaggioDTO viaggio;
	
	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public String getDonatore() {
		return donatore;
	}

	public void setDonatore(String donatore) {
		this.donatore = donatore;
	}
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}

	
    public ViaggioDTO getViaggio(){

    	viaggio = gestioneViaggi.getViaggio(idViaggio);
    	return viaggio;
    }
    public BigDecimal getPrezzo(){
    	getViaggio();
    	BigDecimal p= this.viaggio.getPrezzo();
    	BigDecimal totale=p.multiply(new BigDecimal(viaggio.getNumeroPersone()));
    	return totale;
    }
    
    public String paga(){
    	gestioneViaggi.paga(idViaggio);
    	return "/user/index?faces-redirect=true";
    }

	public int getNumeroPersoneSelezionato() {
		return numeroPersoneSelezionato;
	}

	public void setNumeroPersoneSelezionato(int numeroPersoneSelezionato) {
		this.numeroPersoneSelezionato = numeroPersoneSelezionato;
	}
    public String setPersone(){
    	gestioneViaggi.setNumeroPersone(numeroPersoneSelezionato, idViaggio);
    	return "/user/modificaViaggio?faces-redirect=true&id="+idViaggio;
    }
    
    public BigDecimal getPrezzoPernottamento(){
    	getViaggio();
    	idPernottamento = viaggio.getIdPernottamento();
    	return showViaggio.getPrezzoPernottamento(idPernottamento);
    }

    
    

}
