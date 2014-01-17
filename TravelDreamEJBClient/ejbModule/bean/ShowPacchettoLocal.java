package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.HotelDTO;
import DTO.PacchettoDTO;

@Local
public interface ShowPacchettoLocal {

	public PacchettoDTO getPacchetto(String nomePacchetto);
	public HotelDTO getHotelRelativo(PacchettoDTO pDTO);

}
