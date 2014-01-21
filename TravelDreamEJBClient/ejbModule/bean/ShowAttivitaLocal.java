package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AttivitaDTO;

@Local
public interface ShowAttivitaLocal {

	public AttivitaDTO getAttivita(String nomeAttivita);
	public List<String> getListaTitoliAttivita();
}
