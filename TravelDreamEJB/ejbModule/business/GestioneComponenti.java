package business;
import entity.*;
import enums.TipoCamera;

public class GestioneComponenti {
	
	GestioneComponenti(){
		
	}
	
	public void creaPernottamento(Hotel h, TipoCamera t){
//		InterfacciaDB.creaPernottamento(h.getId(),t);
	}
	
	public void creaHotel(String nome,String citta,String indirizzo,String telefono,String descrizione,
						TipoCamera[] tipiCamera, String foto1,String foto2,String foto3){
//		InterfacciaDB.creaHotel(nome,citta,indirizzo,telefono,descizione,tipiCamera,foto1,foto2,foto3);
	}
	
	
	//CONTROLLO DATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public boolean creaAttivita(int anno,int mese,int giorno,int ora,int minuti,String titolo,
								String descrizione,String citta,float prezzo,
								String foto1,String foto2,String foto3)
	{
	//	InterfacciaDB.creaAttivita(anno,mese,giorno,ora,minuti,titolo,descrizione,citta,prezzo,foto1,foto2,foto3);
		return true;
		
	}
}
