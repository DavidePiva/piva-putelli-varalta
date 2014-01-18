package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import DTO.HotelDTO;
import DTO.TipoCamera;
import DTO.TipoCamere_HotelDTO;
import model.Hotel;
import model.TipoCamere_Hotel;
import model.TipoCamere_HotelPK;

/**
 * Session Bean implementation class ShowHotel
 */
@Stateless
public class ShowHotel implements ShowHotelLocal {

    /**
     * Default constructor. 
     */
	
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    public ShowHotel() {
    }

	public HotelDTO getHotel(String nome) {
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE nome = '"+nome+"'");
		List<Integer> l1 = q.getResultList();
		int id = 0;
		if(l1.size()==1){
			id = l1.get(0);
		}
		Hotel h = em.find(Hotel.class, id);
		return convertiHotelDTO(h);
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
	public List<HotelDTO> listaHotel(){
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel");
		List s = new ArrayList<String>();

		s = q.getResultList();
		List<HotelDTO> list = new ArrayList<HotelDTO>();
		for(int i=0; i<s.size(); i++){
			Hotel h = em.find(Hotel.class, s.get(i));
			list.add(convertiHotelDTO(h));
		}
	
		return list;
	}

	@Override
	public List<TipoCamere_HotelDTO> camereHotel(int idHotel) {
		Hotel h = em.find(Hotel.class, idHotel);
		List<TipoCamere_Hotel> list = h.getTipoCamereHotels();
		List<TipoCamere_HotelDTO> l2 = new ArrayList<TipoCamere_HotelDTO>();
		for(int i = 0; i < list.size(); i++){
			TipoCamere_Hotel t = list.get(i);
			l2.add(convertiTipoCamereDTO(t));
		}
		return l2;
	}
	
	public TipoCamere_HotelDTO convertiTipoCamereDTO(TipoCamere_Hotel tc){
		BigDecimal prezzo = tc.getPrezzo();
		Hotel h = tc.getHotel();
		TipoCamere_HotelPK pk = tc.getId();
		String tipo = pk.getTipoCamera();
		TipoCamere_HotelDTO t2 = new TipoCamere_HotelDTO();
		t2.setId(h.getIdHotel());
		t2.setPrezzo(prezzo);
		TipoCamera a = null;
		if(tipo.equals("lowcost")){
			a = TipoCamera.LOWCOST;
		}
		if(tipo.equals("smart")){
			a = TipoCamera.SMART;
		}
		if(tipo.equals("dream")){
			a = TipoCamera.DREAM;
		}
		t2.setTipo(a);
		return t2;
	}

}
