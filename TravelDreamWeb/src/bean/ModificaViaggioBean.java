package bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import DTO.ViaggioDTO;

@ManagedBean(name="mv")
@ViewScoped
public class ModificaViaggioBean {
	
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	
	private int idViaggio;
	private ViaggioDTO viaggioDTO;
	private Date dataAndata;
	private Date dataRitorno;

	

	public ModificaViaggioBean() {
		setViaggioDTO(new ViaggioDTO());
	}

	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}

	public ViaggioDTO getViaggioDTO() {
		return viaggioDTO;
	}

	public void setViaggioDTO(ViaggioDTO viaggioDTO) {
		this.viaggioDTO = viaggioDTO;
	}

	public Date getDataAndata() {
		return dataAndata;
	}

	public void setDataAndata(Date dataAndata) {
		this.dataAndata = dataAndata;
	}

	public Date getDataRitorno() {
		return dataRitorno;
	}

	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
	}
	
	
}
