package bean;

import javax.ejb.Local;

import DTO.HotelDTO;

@Local
public interface ShowHotelLocal {
	
	public HotelDTO getHotel(String nome);
}
