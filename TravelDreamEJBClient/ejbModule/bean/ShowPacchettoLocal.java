package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AeroportoDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.VoloDTO;

@Local
public interface ShowPacchettoLocal {

	public PacchettoDTO getPacchetto(String nomePacchetto);
	public HotelDTO getHotelRelativo(PacchettoDTO pDTO);
	public VoloDTO getVolo1(PacchettoDTO pDTO);
	public VoloDTO getVolo2(PacchettoDTO pDTO);
	public AeroportoDTO getAeroporto(int id);
}
