package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AttivitaDTO;

@Local
public interface ShowAttivitaLocal {
	
	public List<String> getListaTitoliAttivita();
	public AttivitaDTO getAttivita(int id);
}
