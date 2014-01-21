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
import business.InterfacciaDB;
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
	
	
	private DatiStatici ds;
	private ShowHotel sh;
	
GestioneComponenti(){
		this.ds=new DatiStatici();
		this.sh=new ShowHotel();
	}
	//###CREAZIONE###//
	
	public void creaPacchetto(PacchettoDTO p){
		Pacchetto pacchetto=new Pacchetto(p);
		
	}


	
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
		
	public void creaAttivita(AttivitaDTO a)
	{
		Attivita attivita= new Attivita(a);
		em.persist(attivita);	
	}
	
	public void salvaCamera(TipoCamere_HotelDTO t,HotelDTO h){
		em.flush();
		TipoCamere_Hotel t1 = new TipoCamere_Hotel(t, h);
		Hotel hotel = new Hotel(h);
		Pernottamento p = new Pernottamento(hotel, true, t.getTipo().getString(t.getTipo()));
		
		List<TipoCamere_HotelDTO> list=camereHotel(t.getId());
		boolean esiste=false;
		TipoCamere_HotelDTO newTipo=new TipoCamere_HotelDTO();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId()==t.getId() && list.get(i).getTipo().equals(t.getTipo())){
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
		
		if (!esiste) {
			
			em.persist(t1);  
			em.flush();
			if(t.getPrezzo().compareTo(BigDecimal.ZERO) == 0){
				p.setSelezionabile(false);
			}
			em.persist(p);
		  	em.flush();
        }else if (esiste && t.getPrezzo().compareTo(BigDecimal.ZERO) != 0){

        	TipoCamere_Hotel t3=em.find(TipoCamere_Hotel.class,pk);  
        	em.remove(t3);        	
        	em.flush();
    
        	em.persist(t2);   
        	em.flush();
        	
			int idPernottamento=getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
			Pernottamento p1=new Pernottamento();
			p1=em.find(Pernottamento.class, idPernottamento);
			em.flush();
			if (p1.getSelezionabile() == false) {
				Pernottamento p2 = new Pernottamento();
				p2.setHotelBean(p1.getHotelBean());
				p2.setIdPernottamento(p1.getIdPernottamento());
				p2.setSelezionabile(true);
				p2.setTipo(p1.getTipo());
				em.remove(p1);
				em.flush();
				em.persist(p2);
				em.flush();
			}
		}else if(esiste && t.getPrezzo().compareTo(BigDecimal.ZERO) == 0){

        	TipoCamere_Hotel t3=em.getReference(TipoCamere_Hotel.class,pk);
        	em.remove(t3);
        	em.flush();
   
        	em.persist(t2);
        	em.flush();

			int idPernottamento=getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
			Pernottamento p1=new Pernottamento();
			p1=em.find(Pernottamento.class, idPernottamento);
			em.flush();
			if (p1.getSelezionabile() == true) {
				Pernottamento p2 = new Pernottamento();
				p2.setHotelBean(p1.getHotelBean());
				p2.setIdPernottamento(p1.getIdPernottamento());
				p2.setSelezionabile(false);
				p2.setTipo(p1.getTipo());
				em.remove(p1);
				em.flush();
				em.persist(p2);
				em.flush();
			}
		}
		
	}
	
	public int getIdPernottamento(String tipoCamera,int idHotel){
		Query q = em.createNativeQuery("SELECT idPernottamento FROM Pernottamento WHERE hotel = "+idHotel+" AND tipo = '"+tipoCamera+"'");
		List<Integer> list= new ArrayList<Integer>();
		list=q.getResultList();
		int i=list.get(0);
		return i;
	}
	//###MODIFICA###//
	
	private void modificaPernottamento(Pernottamento p){
		em.merge(p);
	}
	
	public void modificaHotel(HotelDTO h){
		Hotel hotel=new Hotel(h);		
		em.merge(hotel);
	}
	
	public void modificaAttivita(int idAttivita,int anno,int mese,int giorno,int ora,int minuti,String titolo,
			String descrizione,String citta,float prezzo) throws SQLException
	{
		InterfacciaDB.modificaAttivita(idAttivita,anno,mese,giorno,ora,minuti,titolo,descrizione,citta,prezzo);

	}
	
	public void modificaFoto(TipoComponente tipoComponente,int id,int numeroFoto,String url) throws SQLException{
		InterfacciaDB.modificaFotoComponente(tipoComponente,id,numeroFoto,url);
	}
	
	//###ELIMINAZIONE###//
	


	
	public void eliminaFoto(TipoComponente tipoComponente,int id,int numeroFoto) throws SQLException{
		InterfacciaDB.eliminaFotoComponente(tipoComponente,id,numeroFoto);
	}
	
	public void	eliminaComponente(TipoComponente tipo,int id){
//		InterfacciaDB.eliminaComponente(tipo,id);
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


	public DatiStatici getDs() {
		return ds;
	}

	public void setDs(DatiStatici ds) {
		this.ds = ds;
	}

	public ShowHotel getSh() {
		return sh;
	}

	public void setSh(ShowHotel sh) {
		this.sh = sh;
	}
	
}
