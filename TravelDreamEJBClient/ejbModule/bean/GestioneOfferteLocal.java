package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.AttivitaDTO;
import DTO.PacchettoDTO;

@Local
public interface GestioneOfferteLocal {

	public String getCittaSelezionata();

	public int getPaginaSelezionata();
	public void setPaginaSelezionata(int paginaSelezionata);
	public void setDescrizione(String descr);

	public void salvaTutto(PacchettoDTO p, AttivitaDTO a1, AttivitaDTO a2, AttivitaDTO a3, AttivitaDTO a4, AttivitaDTO a5);
	
	public void eliminaPacchetto(int idPacchetto);
	public List<AttivitaDTO> attivitaAggiungibili(int idPacchetto);
	public List<AttivitaDTO> attivitaEliminabili(int idPacchetto);
	public void eliminaAttivitaDaPacchetto(int idPacchetto, int idAttivita);
	public void aggiungiAttivitaAlPacchetto(int idPacchetto, int idAttivita);
}
