package bean;

import java.util.Date;
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
	public List<PacchettoDTO> pacchettiPerHotel(String hotelSelezionato);
	public List<VoloDTO> getVoliPossibili(String cittaPartenza, String cittaArrivo, int anno, int mese, int giorno);
	public List<AttivitaDTO> getAttivitaPossibili(String citta, int anno1,
			int mese1, int giorno1, int anno2, int mese2, int giorno2);
	public List<PernottamentoDTO> getPernottamentiPossibili(String citta);
}
