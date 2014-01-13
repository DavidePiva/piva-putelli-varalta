package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.*;

@Local
public interface DatiStaticiLocal {
	public String esempio();
	public AeroportoDTO getAeroportoDTO();
	public List<String> getDestinazioni();
	public List<HotelDTO> hotelPerCitta();
}
