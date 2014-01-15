package bean;

import javax.ejb.Local;

import DTO.AttivitaDTO;

@Local
public interface GestioneComponentiLocal {
	public void creaAttivita(AttivitaDTO attivita);
}
