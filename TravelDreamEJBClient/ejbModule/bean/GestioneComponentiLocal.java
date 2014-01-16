package bean;

import javax.ejb.Local;

import DTO.AttivitaDTO;
import DTO.HotelDTO;

@Local
public interface GestioneComponentiLocal {
	public void creaAttivita(AttivitaDTO attivita);
	public void creaHotel(HotelDTO hotel);
}
