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
	public void setNumeroPersone(int n,int idViaggio);
	public void sostituisciHotel(int idViaggio, int idHotelScelto, String tipoCameraScelto);
	public void aggiungiPartecipazione(int idViaggio, String emailInvito);
	public void rendiRegalabile(int idViaggio);
	public void rimuoviPartecipazione(int idViaggio, String emailInvito);
	public void aggiungiDonatore(int idViaggio, String emailSelezionata);
	public boolean isRegalabile(int idViaggio);
	public void rimuoviDonatore(int idViaggio, String utenteSelezionato);
	public void paga(int idViaggio);
	public void pagaPernottamento(int idViaggio, int idPernottamento, String donatore);
}
