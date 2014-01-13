package bean;

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
import DTO.AeroportoDTO;
import DTO.HotelDTO;


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
		Map<String, Object> m = context.getContextData();
		Collection<Object> c = m.values();
		Iterator<Object> i = c.iterator();
		//String s = (String)i.next();
		Query q = em.createNativeQuery("SELECT * FROM Hotel WHERE citta = '"+s+"'");
		List<HotelDTO> h = new ArrayList<HotelDTO>();
		List<Hotel> h1 = new ArrayList<Hotel>();
		h1 = q.getResultList();
		for(int j = 0; j < h1.size(); j++){
			Hotel ho = h1.get(j);
			HotelDTO ho1 = convertiHotelDTO(ho);
			h.add(ho1);
		}
		return h;
	}
	
	public HotelDTO convertiHotelDTO(Hotel h){
		int id = h.getIdHotel();
		String nome = h.getNome();
		String citta = h.getCitta();
		String descrizione = h.getDescrizione();
		String telefono = h.getTelefono();
		String indirizzo = h.getIndirizzo();
		HotelDTO h2 = new HotelDTO();
		h2.setIdHotel(id);
		h2.setNome(nome);
		h2.setCitta(citta);
		h2.setIndirizzo(indirizzo);
		h2.setTelefono(telefono);
		h2.setDescrizione(descrizione);
		return h2;
	}

}
