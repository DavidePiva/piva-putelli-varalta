package bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;

import DTO.HotelDTO;
import DTO.TipoCamere_HotelDTO;

@Local
public interface ShowHotelLocal {
	
	public HotelDTO getHotel(String nome);
	public List<HotelDTO> listaHotel();
	public List<TipoCamere_HotelDTO> camereHotel(int i);
}
