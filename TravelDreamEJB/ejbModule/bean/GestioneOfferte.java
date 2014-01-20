package bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import DTO.PacchettoDTO;

/**
 * Session Bean implementation class GestioneOfferte
 */
@Stateful
@LocalBean
public class GestioneOfferte implements GestioneOfferteLocal {
	
	private PacchettoDTO pacchetto;
    /**
     * Default constructor. 
     */
    public GestioneOfferte() {
       this.pacchetto=new PacchettoDTO();
    }

	public PacchettoDTO getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(PacchettoDTO pacchetto) {
		this.pacchetto = pacchetto;
	}

	@Override
	public void setCitta(String citta) {
		this.pacchetto.setCitta(citta);
		
	}

	@Override
	public String getCitta() {
		
		return this.pacchetto.getCitta();
	}

}
