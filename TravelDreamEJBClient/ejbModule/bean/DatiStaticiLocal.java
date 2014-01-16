package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.*;

@Local
public interface DatiStaticiLocal {
	public String esempio();
	public AeroportoDTO getAeroportoDTO();
	public List<String> getDestinazioni();
	public List<HotelDTO> hotelPerCitta(String s);
	public List<PacchettoDTO> pacchettiPerCitta(String s);
	public List<String> tipiPacchetto();
	public List<PacchettoDTO> pacchettiPerTipo(String tipoSelezionato);
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo,
			int prezzoMassimo);
	List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo);
}
