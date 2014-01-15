package bean;

import javax.ejb.Local;

import DTO.PacchettoDTO;

@Local
public interface ShowPacchettoLocal {

	public PacchettoDTO getPacchetto(String nomePacchetto);

}
