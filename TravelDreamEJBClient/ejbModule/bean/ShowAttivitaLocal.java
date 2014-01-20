package bean;

import javax.ejb.Local;

import DTO.AttivitaDTO;

@Local
public interface ShowAttivitaLocal {

	public AttivitaDTO getAttivita(String nomeAttivita);

}
