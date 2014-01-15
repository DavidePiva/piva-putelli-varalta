package bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Pernottamento;
import model.Volo;
import model.Pacchetto;
import DTO.PacchettoDTO;

/**
 * Session Bean implementation class ShowPacchetto
 */
@Stateless
public class ShowPacchetto implements ShowPacchettoLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private EJBContext context;
    /**
     * Default constructor. 
     */
    public ShowPacchetto() {
    }

	@Override
	public PacchettoDTO getPacchetto(String nomePacchetto) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+nomePacchetto+"'");
		List<Integer> l1 = q.getResultList();
		int id = 0;
		if(l1.size()==1){
			id = l1.get(0);
		}
		Pacchetto p = em.find(Pacchetto.class, id);
		return convertiPacchettoDTO(p);
	}

	public PacchettoDTO convertiPacchettoDTO(Pacchetto p){
		PacchettoDTO p1 = new PacchettoDTO();
		int idPacchetto = p.getIdPacchetto();
		Pernottamento pe = p.getPernottamentoBean();
		Volo v1 = p.getVolo1();
		Volo v2 = p.getVolo2();
		String citta = p.getCitta();
		String titolo = p.getTitolo();
		String descrizione = p.getDescrizione();
		BigDecimal prezzo = p.getPrezzo();
		boolean selezionabile = p.getSelezionabile();
		p1.setIdPacchetto(idPacchetto);
		p1.setCitta(citta);
		p1.setTitolo(titolo);
		p1.setPrezzo(prezzo);
		p1.setDescrizione(descrizione);
		p1.setVoloAndata(v1.getIdVolo());
		p1.setVoloRitorno(v2.getIdVolo());
		p1.setIdPernottamento(pe.getIdPernottamento());
		p1.setSelezionabile(selezionabile);
		return p1;
	}
}
