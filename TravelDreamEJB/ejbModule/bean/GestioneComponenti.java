package bean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
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
import model.Utente;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.TipoCamere_HotelDTO;
import DTO.UtenteDTO;
import business.InterfacciaDB;
import model.Hotel;
import enums.TipoCamera;
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

	private void creaPernottamento(Pernottamento p){
		em.persist(p);
	}
	
	public void creaHotel(HotelDTO h){
		Hotel hotel=new Hotel(h);		
		em.persist(hotel);

	}
		
	public void creaAttivita(AttivitaDTO a)
	{
		Attivita attivita= new Attivita(a);
		em.persist(attivita);	
	}
	
	public void salvaCamera(TipoCamere_HotelDTO t,HotelDTO h){
		
		TipoCamere_Hotel tipoCamera = new TipoCamere_Hotel(t, h);
	/*	Hotel hotel = new Hotel(h);
		Pernottamento p = new Pernottamento(hotel, true, t.getTipo().getString(t.getTipo()));
		
		List<TipoCamere_HotelDTO> list=sh.camereHotel(t.getId());
		boolean esiste=false;
		if(list.contains(t)){
			esiste=true;
		}*/
		
		//if (!esiste && t.getPrezzo().floatValue() > 0) {
    //        em.persist(tipoCamera);         
          //  creaPernottamento(p);}
        /*else if (esiste && t.getPrezzo().floatValue() > 0){
			em.merge(tipoCamera);
			int idPernottamento=ds.getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
			p.setIdPernottamento(idPernottamento);
			modificaPernottamento(p);
		}*/
		//	int idPernottamento=ds.getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
		//	p.setIdPernottamento(idPernottamento);
		//	rendiNonSelezionabilePernottamento(p);
		
		
	//	TipoCamere_Hotel tipoCamera = new TipoCamere_Hotel(t, h);
	//	List<TipoCamere_HotelDTO> list=sh.camereHotel(h.getIdHotel());
		
	//	TipoCamere_Hotel oldTipoCamera=ds.getTipoCamere_Hotel(h.getIdHotel(), t.getTipo().getString(t.getTipo()));
	//	BigDecimal oldPrezzo=ds.getPrezzoCamera(h.getIdHotel(), t.getTipo().getString(t.getTipo()));
	//	Hotel hotel = new Hotel(h);
	//	Pernottamento p = new Pernottamento(hotel, true, t.getTipo().getString(t.getTipo()));
		
	/*	
		if (!list.contains(t) && t.getPrezzo().floatValue() > 0) {		
			em.persist(tipoCamera);
		//	creaPernottamento(p);
		}else if (list.contains(t) && t.getPrezzo().floatValue() > 0){
			em.merge(tipoCamera);
		//	int idPernottamento=ds.getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
		//	p.setIdPernottamento(idPernottamento);
		//	modificaPernottamento(p);
		}else if (t.getPrezzo().floatValue() == 0){
			TipoCamere_HotelDTO oldTipoCamera=new TipoCamere_HotelDTO();
			oldTipoCamera.setId(t.getId());
			oldTipoCamera.setTipo(t.getTipo());
			for(int i=0;i<list.size();i++){
				if(list.get(i).getTipo().equals(t.getTipo().getString(t.getTipo()))){
					oldTipoCamera.setPrezzo(t.getPrezzo());
				}
			}	
			em.remove(tipoCamera);
		//	int idPernottamento=ds.getIdPernottamento(t.getTipo().getString(t.getTipo()),hotel.getIdHotel());
		//	p.setIdPernottamento(idPernottamento);
		//	rendiNonSelezionabilePernottamento(p);
		}
		*/	
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
	
	private void rendiNonSelezionabilePernottamento(Pernottamento p){
		p.setSelezionabile(false);
		em.merge(p);
	}
	
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
