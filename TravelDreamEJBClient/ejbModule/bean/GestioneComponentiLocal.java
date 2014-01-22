package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.TipoCamere_HotelDTO;

@Local
public interface GestioneComponentiLocal {
	public void creaAttivita(AttivitaDTO attivita);
	public void creaHotel(HotelDTO hotel);
	public void salvaCamera(TipoCamere_HotelDTO tipoCamera,HotelDTO h);
	public void creaPacchetto(PacchettoDTO pacchetto);
	
	public void modificaHotel(HotelDTO h);
	public void modificaAttivita(AttivitaDTO h);
}
