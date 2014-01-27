package bean;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



import DTO.ViaggioDTO;

@ManagedBean(name="pb")
@RequestScoped
public class PagamentoBean {
		
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	private int idViaggio;
	private int numeroPersoneSelezionato;
	private ViaggioDTO viaggio;
	
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
}
