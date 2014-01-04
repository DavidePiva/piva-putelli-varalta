package business;
import java.sql.SQLException;

import entity.*;
import enums.TipoCamera;
import enums.TipoComponente;

public class GestioneComponenti {
	
	GestioneComponenti(){
		
	}
	//###CREAZIONE###//
	
	public void creaPernottamento(Hotel h, TipoCamera t) throws SQLException{
		InterfacciaDB.creaPernottamento(h.getId(),t);
	}
	
	public void creaHotel(String nome,String citta,String indirizzo,String telefono,String descrizione,	TipoCamera[] tipiCamera){
	//	InterfacciaDB.creaHotel(nome,citta,indirizzo,telefono,descrizione,tipiCamera);
	}
	
	
	public void creaAttivita(int anno,int mese,int giorno,int ora,int minuti,String titolo,
								String descrizione,String citta,float prezzo)
	{
	//	InterfacciaDB.creaAttivita(anno,mese,giorno,ora,minuti,titolo,descrizione,citta,prezzo);
		
	}
	
	public void inserisciFoto(TipoComponente tipoComponente,int id,int numeroFoto,String url){
//		InterfacciaDB.inserisciFotoComponente(tipoComponente,id,numeroFoto,url);
	}
	

	
	//###MODIFICA###//
	
	public void modificaPernottamento(int idPernottamento,Hotel h, TipoCamera t) throws SQLException{
		InterfacciaDB.modificaPernottamento(idPernottamento,h.getId(),t);
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
