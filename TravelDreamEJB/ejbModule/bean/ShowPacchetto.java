package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Aeroporto;
import model.Attivita;
import model.Hotel;
import model.Pernottamento;
import model.Volo;
import model.Pacchetto;
import DTO.AeroportoDTO;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.VoloDTO;

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

	@Override
	public VoloDTO getVolo1(PacchettoDTO pDTO) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+pDTO.getTitolo()+"'");
		List<Integer> l1 = q.getResultList();
		int id = 0;
		if(l1.size()==1){
			id = l1.get(0);
		}
		Pacchetto p = em.find(Pacchetto.class, id);
		return convertiVoloDTO(p.getVolo1());
	}

	private VoloDTO convertiVoloDTO(Volo v) {
		VoloDTO vDTO = new VoloDTO();
		vDTO.setOraPartenza(v.getOraPartenza());
		vDTO.setIdAeroportoPartenza(v.getAeroporto1().getIdAeroporto());
		vDTO.setOraArrivo(v.getOraArrivo());
		vDTO.setIdAeroportoArrivo(v.getAeroporto2().getIdAeroporto());
		vDTO.setIdVolo(v.getIdVolo());
		vDTO.setData(v.getData());
		vDTO.setPrezzo(v.getPrezzo());
		vDTO.setIdCompagnia(v.getCompagniaBean().getIdCompagnia());
		return vDTO;
	}

	@Override
	public VoloDTO getVolo2(PacchettoDTO pDTO) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+pDTO.getTitolo()+"'");
		List<Integer> l1 = q.getResultList();
		int id = 0;
		if(l1.size()==1){
			id = l1.get(0);
		}
		Pacchetto p = em.find(Pacchetto.class, id);
		return convertiVoloDTO(p.getVolo2());
	}

	@Override
	public AeroportoDTO getAeroporto(int id) {
		Aeroporto a = em.find(Aeroporto.class, id);
		return convertiAeroportoDTO(a);
	}

	private AeroportoDTO convertiAeroportoDTO(Aeroporto a) {
		AeroportoDTO aDTO = new AeroportoDTO();
		aDTO.setCitta(a.getCitta());
		aDTO.setId(a.getIdAeroporto());
		aDTO.setNome(a.getNome());
		return aDTO;
	}

	@Override
	public List<AttivitaDTO> getListaAttivita(int idPacchetto) {
		Pacchetto p = em.find(Pacchetto.class, idPacchetto);
		List<Attivita> att = p.getAttivitas();
		List<AttivitaDTO> l = new ArrayList<AttivitaDTO>();
		for(int i = 0; i < att.size(); i++){
			Attivita a = att.get(i);
			l.add(convertiAttivitaDTO(a));
		}
		return l;
	}
	
	public AttivitaDTO convertiAttivitaDTO(Attivita a){
		AttivitaDTO a2 = new AttivitaDTO();
		int id = a.getIdAttivita();
		boolean selezionabile = a.getSelezionabile();
		String titolo = a.getTitolo();
		String citta = a.getCitta();
		String descrizione = a.getDescrizione();
		String foto1 = a.getFoto1();
		String foto2 = a.getFoto2();
		String foto3 = a.getFoto3();
		Date data = a.getData();
		Time ora = a.getOra();
		a2.setId(id);
		a2.setDescrizione(descrizione);
		a2.setCitta(citta);
		a2.setFoto1(foto1);
		a2.setFoto2(foto2);
		a2.setFoto3(foto3);
		a2.setTitolo(titolo);
		a2.setOra(ora);
		a2.setData(data);
		a2.setSelezionabile(selezionabile);
		a2.setPrezzo(a.getPrezzo());
		return a2;
	}
}
