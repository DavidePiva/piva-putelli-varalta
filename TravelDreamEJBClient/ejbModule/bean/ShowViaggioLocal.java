package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.UtenteDTO;
import DTO.ViaggioDTO;

@Local
public interface ShowViaggioLocal {

	public List<ViaggioDTO> getViaggiUtente(String email);

	public List<ViaggioDTO> getPartecipazioni(String emailUtente);

	public List<UtenteDTO> getUtentiInvitati(int idViaggio);

	public List<UtenteDTO> getUtentiConfermati(int idViaggio);

	public List<UtenteDTO> getDonatori(int idViaggio);

	public List<ViaggioDTO> getViaggiDaRegalare(String emailUtente);

	public List<ViaggioDTO> getPagati(String email);

	boolean pagabile(int idViaggio);

}
