package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Aeroporto;
import model.Hotel;
import model.Pacchetto;
import model.Pernottamento;
import model.TipoCamere_Hotel;
import model.Volo;
import DTO.AeroportoDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;


/**
 * Session Bean implementation class DatiStatici
 */
@Stateless
public class DatiStatici implements DatiStaticiLocal {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public DatiStatici() {
    }
    
    @Override
    public String esempio(){
    	return "Hello world!";
    }

	@Override
	public AeroportoDTO getAeroportoDTO() {
		AeroportoDTO aeroportoDTO = convertiInDTO(getAeroporto(1));
		return aeroportoDTO;
	}
	
	private AeroportoDTO convertiInDTO(Aeroporto aeroporto) {
		AeroportoDTO a = new AeroportoDTO();
		a.setId(aeroporto.getIdAeroporto());
		a.setNome(aeroporto.getNome());
		a.setCitta(aeroporto.getCitta());
		return a;
	}

	public Aeroporto getAeroporto(int id){
		Aeroporto a = em.find(Aeroporto.class,id);
		return a;
	}

	@Override
	public List<String> getDestinazioni() {
		Query q = em.createNativeQuery("SELECT DISTINCT citta FROM Aeroporto ORDER BY citta ASC");
		List a = new ArrayList<String>();
		a = q.getResultList();
		return a;
	}
	
	public List<HotelDTO> hotelPerCitta(String s){
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE citta = '"+s+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<HotelDTO> lista = new ArrayList<HotelDTO>();
		for(int index = 0; index < i.size(); index++){
			Hotel h = em.find(Hotel.class, i.get(index));
			lista.add(convertiHotelDTO(h));
		}
		return lista;
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
	public List<PacchettoDTO> pacchettiPerCitta(String s) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE citta = '"+s+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
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

	@Override
	public List<String> tipiPacchetto() {
		Query q = em.createNativeQuery("SELECT DISTINCT tipologia FROM Pacchetto WHERE selezionabile = 1");
		List<String> l1 = new ArrayList<String>();
		l1 = q.getResultList();
		return l1;
	}

	@Override
	public List<PacchettoDTO> pacchettiPerTipo(String tipoSelezionato) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND tipologia = '"+tipoSelezionato+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@Override
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo,int prezzoMassimo) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND prezzo BETWEEN " + prezzoMinimo+ " AND "+ prezzoMassimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@Override
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND prezzo > " + prezzoMinimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}
	

	@Override
	public List<PacchettoDTO> pacchettiPerHotel(String hotelSelezionato) {
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE nome = '"+hotelSelezionato+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		int id = 0;
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		if(i.size()==1){
			id = i.get(0);
		}
		Query q2 = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto,Pernottamento WHERE Pacchetto.pernottamento = Pernottamento.idPernottamento AND Pernottamento.hotel = "+id);
		List<Integer> j = new ArrayList<Integer>();
		j = q2.getResultList();
		for(int index = 0; index < j.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, j.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	public int getIdPernottamento(String tipoCamera,int idHotel){
		Query q = em.createNativeQuery("SELECT idPernottamento FROM Pernottamento WHERE hotel = "+idHotel+" AND tipo = "+tipoCamera);
		List<Integer> list=new ArrayList<Integer>();
		list=q.getResultList();
		
		int i = list.get(0);
		return i;
	}
	
	public TipoCamere_Hotel getTipoCamere_Hotel(int idHotel,String tipoCamera){
		Query q = em.createNativeQuery("SELECT  FROM TipoCamere_Hotel WHERE idHotel = "+idHotel+" AND tipoCamera = "+tipoCamera);
		List<TipoCamere_Hotel> list = new ArrayList<TipoCamere_Hotel>();
		list=q.getResultList();
		TipoCamere_Hotel t=list.get(0);
		return t;
	}
	
	public BigDecimal getPrezzoCamera(int idHotel,String tipoCamera){
		Query q = em.createNativeQuery("SELECT prezzo FROM TipoCamere_Hotel WHERE idHotel = "+idHotel+" AND tipoCamera = "+tipoCamera);
		List<TipoCamere_Hotel> list = new ArrayList<TipoCamere_Hotel>();
		list=q.getResultList();
		BigDecimal prezzo=list.get(0).getPrezzo();
		return prezzo;
	}

}
