package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AttivitaDTO;


@ManagedBean(name="sa")
@RequestScoped
public class ShowAttivitaBean {
	
	@EJB
	private ShowAttivitaLocal showAttivita;
	private int numeroFoto;
	private String nomeAttivita;
	
	public void setNumeroFoto(int numeroFoto){
		this.numeroFoto = numeroFoto;
	}
	
	public void setNomeAttivita(String nomeAttivita){
		this.nomeAttivita = nomeAttivita;
	}
	
	public String getNomeAttivita(){
		return nomeAttivita;
	}
	
	public int getNumeroFoto(){
		return numeroFoto;
	}
	
	public AttivitaDTO getAttivitaPerParametro(){
		AttivitaDTO a = showAttivita.getAttivita(nomeAttivita);
		return a;
	}
	
	public String descrizioneAttivita(){
		AttivitaDTO a = getAttivitaPerParametro();
		return a.getDescrizione();
	}
}
