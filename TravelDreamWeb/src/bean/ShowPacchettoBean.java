package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.AeroportoDTO;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.VoloDTO;

@ManagedBean(name="sp")
@RequestScoped
public class ShowPacchettoBean {
	
	private String nomePacchetto;
	private int numeroFoto;
	private ArrayList<String> attivitaPacchetto;
	
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
	
	public AeroportoDTO getAeroportoPartenza(){
		PacchettoDTO h = getPacchettoPerParametro();
		return showPacchetto.getAeroporto(showPacchetto.getVolo1(h).getIdAeroportoPartenza());
	}
	
	public AeroportoDTO getAeroportoArrivo(){
		PacchettoDTO h = getPacchettoPerParametro();
		return showPacchetto.getAeroporto(showPacchetto.getVolo2(h).getIdAeroportoPartenza());
	}
	
	public VoloDTO getVoloPartenza(){
		PacchettoDTO h = getPacchettoPerParametro();
		return showPacchetto.getVolo1(h);
	}
	
	public VoloDTO getVoloArrivo(){
		PacchettoDTO h = getPacchettoPerParametro();
		return showPacchetto.getVolo2(h);
	}
	
	public ArrayList<String> getAttivitaPacchetto(){
		PacchettoDTO h = getPacchettoPerParametro();
		attivitaPacchetto = new ArrayList<String>();
		List<AttivitaDTO> att = new ArrayList<AttivitaDTO>();
		att = showPacchetto.getListaAttivita(h.getIdPacchetto());
		for(int i = 0; i < att.size(); i++){
			attivitaPacchetto.add(att.get(i).getTitolo());
		}
		return attivitaPacchetto;
	}
}
