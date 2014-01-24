package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.ViaggioDTO;

@Local
public interface ShowViaggioLocal {

	public List<ViaggioDTO> getViaggiUtente(String email);

	public List<ViaggioDTO> getPartecipazioni(String emailUtente);

}
