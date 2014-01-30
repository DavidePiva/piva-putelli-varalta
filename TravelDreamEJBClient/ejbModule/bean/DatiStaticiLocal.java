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
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo);
	public List<PacchettoDTO> pacchettiPerHotel(String hotelSelezionato);
	public List<VoloDTO> getVoliPossibili(String cittaPartenza, String cittaArrivo, int anno, int mese, int giorno);
	public List<AttivitaDTO> getAttivitaPossibili(String citta, int anno1,
			int mese1, int giorno1, int anno2, int mese2, int giorno2);
	public List<PernottamentoDTO> getPernottamentiPossibili(String citta);
	public List<String> getTarget();
	public List<PacchettoDTO> pacchettiPerTarget(String targetSelezionato);
	public List<PacchettoDTO> pacchettiTutti();
	public List<HotelDTO> getHotelPerPrezzo(int prezzoMinimo, int prezzoMassimo);
	public List<HotelDTO> getHotelPerPrezzo(int prezzoMinimo);
	public List<AttivitaDTO> attivitaPerTitolo(String titolo);
	public List<AttivitaDTO> attivitaDTO();
	public HotelDTO getHotelPerId(int idHotel);
	HotelDTO getHotelPerNome(String nome);
	public ViaggioDTO getViaggioDTO(int id);
	public PacchettoDTO getPacchettoDTO(int id);
	public List<TipoCamere_HotelDTO> camerePerHotel(int idHotelScelto);
	public VoloDTO getVoloDTO(int id);
	public AeroportoDTO getAeroportoDTO(int id);
	public List<String> getListaUtenti();
	public List<PacchettoDTO> ultimiPacchetti();
	
	public List<String> getCittaConHotel();
	public List<String> getCittaConPacchetto();
}
