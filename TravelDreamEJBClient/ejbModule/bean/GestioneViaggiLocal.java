package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;

@Local
public interface GestioneViaggiLocal {

	public void creaViaggio(String nomePacchetto, String emailUtente);
	public ViaggioDTO getViaggio(int idViaggio);
	public HotelDTO getHotelViaggio(int idViaggio);
	public List<AttivitaDTO> getAttivitaViaggio(int idViaggio);
	public VoloDTO getAndataViaggio(int idViaggio);
	public VoloDTO getRitornoViaggio(int idViaggio);
	public void rimuoviViaggio(int idViaggio);

}
