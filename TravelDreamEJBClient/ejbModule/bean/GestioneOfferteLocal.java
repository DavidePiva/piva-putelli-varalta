package bean;

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
}
