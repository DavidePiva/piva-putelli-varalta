package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AttivitaDTO;
import DTO.HotelDTO;


@ManagedBean(name="sa")
@RequestScoped
public class ShowAttivitaBean {
	
	@EJB
	private ShowAttivitaLocal showAttivita;
	private int numeroFoto;
	private int idAttivita;
	
	public void setNumeroFoto(int numeroFoto){
		this.numeroFoto = numeroFoto;
	}
	
	public int getNumeroFoto(){
		return numeroFoto;
	}
	
	public List<String> getListaTitoliAttivita(){
		return showAttivita.getListaTitoliAttivita();
	}

	public int getIdAttivita() {
		return idAttivita;
	}

	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}
	
	public AttivitaDTO getAttivita(){
		return showAttivita.getAttivita(idAttivita);
	}
	
	public String getFotoSelezionata(){
		AttivitaDTO aDTO = getAttivita();
		switch(numeroFoto){
		case 1:
			return aDTO.getFoto1();
		case 2:
			return aDTO.getFoto2();
		case 3:
			return aDTO.getFoto3();
		default:
			return "";
		}
	}

}
