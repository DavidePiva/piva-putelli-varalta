package business;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.*;

public class GestioneViaggio {
	
	Viaggio viaggio;
//	Pacchetto pacchetto;
	
	public GestioneViaggio (){
		viaggio=new Viaggio();
	}
	
	//setta un nuovo viaggio da gestire
	public void nuovoViaggio(Pacchetto p,Utente u) throws SQLException{
		
		Viaggio v=new Viaggio();
		v.setId(p.getId());
		for(Attivita a: p.getAttivita()){
			v.getAttivita().add(a);
		}
		v.setPernottamento(p.getPernottamento());
		v.setVoloAndata(p.getVoloAndata());
		v.setVoloRitorno(p.getVoloRitorno());
		v.setCitta(p.getCitta());
		v.setPrezzo(p.getPrezzo());
		v.setTitolare(u);
		v.setPagato(false);
		v.setNumeroPersone(1);	
		this.viaggio=v;
//		this.pacchetto=p;
		InterfacciaDB.inserisciViaggio(v);
	}
	
	public void cambiaViaggioDaGestire(Viaggio v){
		this.viaggio=v;
	}

	public void aggiungiAttivita(ArrayList<Attivita> attivita) throws SQLException{
		for(int i=0;i<attivita.size();i++){
			viaggio.getAttivita().add(attivita.get(i));
			InterfacciaDB.inserisciAttivita(viaggio.getId(),attivita.get(i).getId());
		}
		ricalcolaPrezzo();
	}
	

	public void rimuoviAttivita(ArrayList<Attivita> attivita){
		for(int i=0;i<attivita.size();i++){
			viaggio.getAttivita().remove(attivita.get(i));
//			InterfacciaDB.rimuoviAttivita(viaggio.getId(),attivita.get(i).getId());
		}
		ricalcolaPrezzo();
	}
	
	public void modificaPernottamento(Pernottamento pernottamento){
		viaggio.setPernottamento(pernottamento);
//		InterfacciaDB.modificaPernottamento(viaggio.getId(),pernottamento.getId())
		ricalcolaPrezzo();
	}
	
	/*
	public ArrayList<Volo> cercaVoliPerData(int anno,int mese,int giorno,boolean isAndata){
		if(isAndata){
			ArrayList<Volo> voliCandidatiAndata = InterfacciaDB.voliPerData(viaggio.getVoloAndata().getAeroportoPartenza().getCitta(),viaggio.getVoloAndata().getAeroportoArrivo().getCitta(),anno,mese,giorno);
			return voliCandidatiAndata;
		}else if(!isAndata){
			ArrayList<Volo> voliCandidatiRitorno = InterfacciaDB.voliPerData(viaggio.getVoloRitorno().getAeroportoPartenza().getCitta(),viaggio.getVoloRitorno().getAeroportoArrivo().getCitta(),anno,mese,giorno);
			return voliCandidatiRitorno;
		}
	}*/
	
	public boolean modificaVolo(Volo volo, boolean isAndata){
		if(isAndata){
			viaggio.setVoloAndata(volo);
	//		InterfacciaDB.modificaVolo(viaggio,volo,true);
			ricalcolaPrezzo();
			return true;
		}else if(!isAndata){
			viaggio.setVoloRitorno(volo);
	//		InterfacciaDB.modificaVolo(viaggio,volo,false);
			ricalcolaPrezzo();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean modificaNumViaggiatori(int n){
		if(!viaggio.isRegalabile()){
			viaggio.setNumeroPersone(n);
	//		InterfacciaDB.modificaNumeroPersone(viaggio,n);
			ricalcolaPrezzo();
			return true;
		}else{
			return false;
		}
	}
	
	public void rendiRegalabile(){
		viaggio.setRegalabile(true);
	//	InterfacciaDB.rendiRegalabile(idViaggio);
	}
	
	//Qui immagino che servirà anche altro per gestire la pagina del pagamento...
	public void paga(){
		viaggio.setPagato(true);
	//	InterfacciaDB.viaggioPagato(idViaggio);
	}
	
	public void aggiungiPartecipazioni(ArrayList<Utente> partecipanti){
		for(int i=0;i<partecipanti.size();i++){
			viaggio.getPartecipanti().add(partecipanti.get(i));
	//		InterfacciaDB.aggiuntiPartecipante(viaggio.getId(),partecipanti.get(i).getEmail());
		}
	}
	
	public boolean rimuoviPartecipante(Utente partecipante){
		viaggio.getPartecipanti().remove(partecipante);
	//	InterfacciaDB.rimuoviPartecipante(viaggio.getId(),partecipante.getEmail());
		return true;
		///////////////QUANDO TORNA FALSE??? 
	}
	private void ricalcolaPrezzo(){
		int p=0;
		p+=viaggio.getVoloAndata().getPrezzo()+viaggio.getVoloRitorno().getPrezzo();
//		p+=InterfacciaDB.getPrezzoCamera(viaggio.getPernottamento().getHotel().getId(),viaggio.getPernottamento().getTipoCamera());
		ArrayList<Attivita> a=viaggio.getAttivita();
		for(int i=0;i<a.size();i++){
			p+=a.get(i).getPrezzo();
		}
		viaggio.setPrezzo(p);
	}
	
	/*
	private void ricalcolaPrezzo(){
		if(confrontoViaggioPacchetto()){
			viaggio.setPrezzo(pacchetto.getPrezzo());
		}else{
			////////////////////////////////////////////////////////////////7
		}
	}
	
	private boolean confrontoViaggioPacchetto(){
		if(confrontoAttivita() && confrontoVoli() && confrontoPernottamenti() ){
			return true;
		}else{
			return false;
		}	
	}
	
	private boolean confrontoAttivita(){
		
	}
	
	private boolean confrontoVoli(){
		if(viaggio.getVoloAndata().getId()==pacchetto.getVoloAndata().getId() 
			&& viaggio.getVoloRitorno().getId()==pacchetto.getVoloRitorno().getId()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean confrontoPernottamenti(){
	
	}	*/
}
