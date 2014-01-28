package bean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Attivita;
import model.Gruppo;
import model.Pacchetto;
import model.Pernottamento;
import model.TipoCamere_Hotel;
import model.TipoCamere_HotelPK;
import model.Utente;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.TipoCamere_HotelDTO;
import DTO.UtenteDTO;
import model.Hotel;
import DTO.TipoCamera;
import enums.TipoComponente;

/**
 * Session Bean implementation class GestioneComponenti
 */
@Stateless
public class GestioneComponenti implements GestioneComponentiLocal {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;
	
	

	
GestioneComponenti(){

	}
	//###CREAZIONE###//
@Override
	public void creaPacchetto(PacchettoDTO p){
		Pacchetto pacchetto=new Pacchetto(p);
		
	}


	@Override
	public void creaHotel(HotelDTO h){
		Hotel hotel=new Hotel(h);		
		em.persist(hotel);

	}
	
	public List<TipoCamere_HotelDTO> camereHotel(int idHotel) {
		Hotel h =em.find(Hotel.class, idHotel);
		List<TipoCamere_Hotel> list = h.getTipoCamereHotels();
		List<TipoCamere_HotelDTO> l2 = new ArrayList<TipoCamere_HotelDTO>();
		for(int i = 0; i < list.size(); i++){
			TipoCamere_Hotel t = list.get(i);
			BigDecimal prezzo = t.getPrezzo();
			if(prezzo.compareTo(BigDecimal.ZERO)!=0){
				l2.add(convertiTipoCamereDTO(t));
			}
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
		if (tipo.equals("lowcost")) {
			a = TipoCamera.LOWCOST;
		}
		if (tipo.equals("smart")) {
			a = TipoCamera.SMART;
		}
		if (tipo.equals("dream")) {
			a = TipoCamera.DREAM;
		}
		t2.setTipo(a);
		return t2;
	}
	@Override
	public void creaAttivita(AttivitaDTO a)
	{
		Attivita attivita= new Attivita(a);
		attivita.setSelezionabile(true);
		em.persist(attivita);	
	}
	@Override
	public void salvaCamera(TipoCamere_HotelDTO t,HotelDTO h){
		
		Hotel hotel = new Hotel(h);
		Pernottamento p = new Pernottamento(hotel, true, t.getTipo().getString(t.getTipo()));
		
		List<TipoCamere_HotelDTO> list=camereHotel(t.getId());
		boolean esiste=false;
		TipoCamere_HotelDTO newTipo=new TipoCamere_HotelDTO();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId()==t.getId() && list.get(i).getTipo().getString(list.get(i).getTipo())==t.getTipo().getString(t.getTipo())){
				esiste=true;
				newTipo=list.get(i);
			}
		}
		newTipo.setTipo(t.getTipo());
    	TipoCamere_HotelPK pk=new TipoCamere_HotelPK() ;
    	pk.setIdHotel(hotel.getIdHotel());
    	pk.setTipoCamera(t.getTipo().getString(t.getTipo()));
		TipoCamere_Hotel t2=new TipoCamere_Hotel();
		
		t2.setId(pk);
		t2.setHotel(hotel);
		t2.setPrezzo(t.getPrezzo());
		TipoCamere_Hotel t1 = new TipoCamere_Hotel(t, h);
		if (!esiste) {
			
			em.persist(t1);  
			em.flush();
			em.persist(p);
        }else{

        	TipoCamere_Hotel t3=em.find(TipoCamere_Hotel.class,pk);  
        	t3.setPrezzo(t.getPrezzo());
        	em.merge(t3);

		}
		
		
	}
	
	public int getIdPernottamento(String tipoCamera,int idHotel){
		Query q = em.createNativeQuery("SELECT idPernottamento FROM Pernottamento WHERE hotel = "+idHotel+" AND tipo = '"+tipoCamera+"'");
		List<Integer> list= new ArrayList<Integer>();
		list=q.getResultList();
		if(list.size()>=1){
			int i=list.get(0);
			return i;
		}else{
			return -1;
		}
		
		
	}
	//###MODIFICA###//
	
	private void modificaPernottamento(Pernottamento p){
		em.merge(p);
	}
	@Override
	public void modificaAttivita(AttivitaDTO attivita) {
		
		Attivita a=new Attivita(attivita); 
		em.merge(a);
		
	}
	@Override
	public void modificaHotel(HotelDTO h){
		Hotel hotel=new Hotel(h);		
		em.merge(hotel);
	}
	

	
	//###ELIMINAZIONE###//
	
	private void eliminaPernottamento(int id){
		Pernottamento p=em.find(Pernottamento.class, id);
		p.setSelezionabile(false);
		em.merge(p);
	}
	
	public void eliminaAttivita(int id){
		Attivita a=em.find(Attivita.class, id);
		a.setSelezionabile(false);
		em.merge(a);
	}

	@Override
	public void eliminaHotel(int id){
		Hotel h=em.find(Hotel.class, id);
		h.setSelezionabile(false);
		em.merge(h);
		em.flush();
		
		int id1=getIdPernottamento("lowcost",id);
		if(id1!=-1){
			eliminaPernottamento(id1);
			em.flush();
		}
		
		int id2=getIdPernottamento("smart",id);
		if(id2!=-1){		
			eliminaPernottamento(id2);
			em.flush();
		}
		
		int id3=getIdPernottamento("dream",id);
		if(id3!=-1){
			eliminaPernottamento(id3);	
		}	
	}
	


	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public EJBContext getContext() {
		return context;
	}

	public void setContext(EJBContext context) {
		this.context = context;
	}

	


	
}
