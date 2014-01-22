package bean;

import javax.ejb.Local;

import DTO.HotelDTO;
import DTO.ViaggioDTO;

@Local
public interface GestioneViaggiLocal {

	public void creaViaggio(String nomePacchetto, String emailUtente);
	public ViaggioDTO getViaggio(int idViaggio);
	public HotelDTO getHotelViaggio(int idViaggio);

}
