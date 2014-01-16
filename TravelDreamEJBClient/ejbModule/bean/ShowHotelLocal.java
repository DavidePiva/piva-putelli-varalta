package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.HotelDTO;

@Local
public interface ShowHotelLocal {
	
	public HotelDTO getHotel(String nome);
	public List<HotelDTO> listaHotel();
}
