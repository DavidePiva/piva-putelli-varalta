package bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.HotelDTO;
import DTO.PacchettoDTO;

@ManagedBean(name="sp")
@RequestScoped
public class ShowPacchettoBean {
	
	private String nomePacchetto;
	private int numeroFoto;
	
	public int getNumeroFoto() {
		return numeroFoto;
	}

	public void setNumeroFoto(int numeroFoto) {
		this.numeroFoto = numeroFoto;
	}

	@EJB
	private ShowPacchettoLocal showPacchetto;
	
	public void setNomePacchetto(String nomePacchetto){
		this.nomePacchetto=nomePacchetto;
	}
	
	public String getNomePacchetto(){
		return nomePacchetto;
	}
	
	public PacchettoDTO getPacchettoPerParametro(){
		PacchettoDTO p = showPacchetto.getPacchetto(nomePacchetto);
		return p;
	}
	
	public String getFotoSelezionata(){
		PacchettoDTO h = getPacchettoPerParametro();
		switch(numeroFoto){
		case 1:
			return h.getFoto1();
		case 2:
			return h.getFoto2();
		case 3:
			return h.getFoto3();
		case 4:
			return h.getFoto4();
		case 5:
			return h.getFoto5();
		case 6:
			return h.getFoto6();
		default:
			return "";
		}
	}
	
	public HotelDTO getHotelRelativo(){
		PacchettoDTO h = getPacchettoPerParametro();
		return showPacchetto.getHotelRelativo(h);
	}

}
