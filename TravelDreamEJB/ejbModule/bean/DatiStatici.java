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
import model.Pernottamento;
import model.TipoCamere_Hotel;
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

}
