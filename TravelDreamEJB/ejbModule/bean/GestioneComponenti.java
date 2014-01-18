package bean;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
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
	
GestioneComponenti(){
		
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
		
		if (t.getPrezzo().floatValue() > 0) {
			TipoCamere_Hotel tipoCamera = new TipoCamere_Hotel(t, h);
			em.persist(tipoCamera);
			Hotel hotel = new Hotel(h);
			Pernottamento p = new Pernottamento(hotel, true, t.getTipo().getString(t.getTipo()));
			creaPernottamento(p);
		}

	}
	
	//###MODIFICA###//
	
	public void modificaPernottamento(int idPernottamento,Hotel h, TipoCamera t) throws SQLException{
//		InterfacciaDB.modificaPernottamento(idPernottamento,h.getId(),t);
	}
	
	public void modificaHotel(int idHotel, String nome,String citta,String indirizzo,String telefono,String descrizione,	TipoCamera[] tipiCamera){
//		InterfacciaDB.modificaHotel(idHotel,nome,citta,indirizzo,telefono,descrizione,tipiCamera);
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

	
			
}
