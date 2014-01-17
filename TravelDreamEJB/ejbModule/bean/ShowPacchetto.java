package bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Hotel;
import model.Pernottamento;
import model.Volo;
import model.Pacchetto;
import DTO.HotelDTO;
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
		p1.setFoto1(p.getFoto1());
		p1.setFoto2(p.getFoto2());
		p1.setFoto3(p.getFoto3());
		p1.setFoto4(p.getFoto4());
		p1.setFoto5(p.getFoto5());
		p1.setFoto6(p.getFoto6());
		return p1;
	}

	@Override
	public HotelDTO getHotelRelativo(PacchettoDTO pDTO) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+pDTO.getTitolo()+"'");
		List<Integer> l1 = q.getResultList();
		int id = 0;
		if(l1.size()==1){
			id = l1.get(0);
		}
		Pacchetto p = em.find(Pacchetto.class, id);
		return convertiHotelDTO(p.getPernottamentoBean().getHotelBean());
	}
	
	public HotelDTO convertiHotelDTO(Hotel h){
		int id = h.getIdHotel();
		String nome = h.getNome();
		String citta = h.getCitta();
		String descrizione = h.getDescrizione();
		String telefono = h.getTelefono();
		String indirizzo = h.getIndirizzo();
		boolean selezionabile = h.getSelezionabile();
		String foto1 = h.getFoto1();
		String foto2 = h.getFoto2();
		String foto3 = h.getFoto3();
		HotelDTO h2 = new HotelDTO();
		h2.setIdHotel(id);
		h2.setNome(nome);
		h2.setCitta(citta);
		h2.setIndirizzo(indirizzo);
		h2.setTelefono(telefono);
		h2.setDescrizione(descrizione);
		h2.setSelezionabile(selezionabile);
		h2.setFoto1(foto1);
		h2.setFoto2(foto2);
		h2.setFoto3(foto3);
		return h2;
	}
}
