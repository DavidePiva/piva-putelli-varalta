package business;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneViaggio {
	/*

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
		v.setPacchetto(p);
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
	

	public void rimuoviAttivita(ArrayList<Attivita> attivita) throws SQLException{
		for(int i=0;i<attivita.size();i++){
			viaggio.getAttivita().remove(attivita.get(i));
			InterfacciaDB.rimuoviAttivita(viaggio.getId(),attivita.get(i).getId());
		}
		ricalcolaPrezzo();
	}
	
	public void modificaPernottamento(Pernottamento pernottamento) throws SQLException{
		viaggio.setPernottamento(pernottamento);
		InterfacciaDB.modificaPernottamento(viaggio.getId(),pernottamento.getId());
		ricalcolaPrezzo();
	}
	

	public ArrayList<Volo> cercaVoliPerData(int anno,int mese,int giorno,boolean isAndata) throws SQLException{
		if(isAndata){
			ArrayList<Volo> voliCandidatiAndata = InterfacciaDB.voliPerData(viaggio.getVoloAndata().getAeroportoPartenza(),viaggio.getVoloAndata().getAeroportoArrivo(),anno,mese,giorno);
			return voliCandidatiAndata;
		}else{
			ArrayList<Volo> voliCandidatiRitorno = InterfacciaDB.voliPerData(viaggio.getVoloRitorno().getAeroportoPartenza(),viaggio.getVoloRitorno().getAeroportoArrivo(),anno,mese,giorno);
			return voliCandidatiRitorno;
		}
	}
	
	public boolean modificaVolo(Volo volo, boolean isAndata) throws SQLException{
		if(isAndata){
			viaggio.setVoloAndata(volo);
			InterfacciaDB.modificaVolo(viaggio.getId(),volo.getId(),true);
			ricalcolaPrezzo();
			return true;
		}else if(!isAndata){
			viaggio.setVoloRitorno(volo);
			InterfacciaDB.modificaVolo(viaggio.getId(),volo.getId(),false);
			ricalcolaPrezzo();
			return true;
		}else{
			return false;
		}
	}
	
	public boolean modificaNumViaggiatori(int n) throws SQLException{
		if(!viaggio.isRegalabile()){
			viaggio.setNumeroPersone(n);
			InterfacciaDB.modificaNumeroPersone(viaggio,n);
			ricalcolaPrezzo();
			return true;
		}else{
			return false;
		}
	}
	
	public void rendiRegalabile() throws SQLException{
		viaggio.setRegalabile(true);
		InterfacciaDB.rendiRegalabile(viaggio.getId());
	}
	
	//Qui immagino che servirà anche altro per gestire la pagina del pagamento...
	public void paga() throws SQLException{
		viaggio.setPagato(true);
		InterfacciaDB.viaggioPagato(viaggio.getId());
	}
	
	public void aggiungiPartecipazioni(ArrayList<Utente> partecipanti) throws SQLException{
		for(int i=0;i<partecipanti.size();i++){
			viaggio.getPartecipanti().add(partecipanti.get(i));
			InterfacciaDB.aggiungiPartecipante(viaggio.getId(),partecipanti.get(i).getEmail());
		}
	}
	
	public void rimuoviPartecipante(Utente partecipante) throws SQLException{
		viaggio.getPartecipanti().remove(partecipante);
		InterfacciaDB.rimuoviPartecipante(viaggio.getId(),partecipante.getEmail());
	}

	
	private void ricalcolaPrezzo() throws SQLException{
		if(confrontoViaggioPacchetto()){
			viaggio.setPrezzo(viaggio.getPacchetto().getPrezzo());
		}else{
			float p=0;
			p+=viaggio.getVoloAndata().getPrezzo()+viaggio.getVoloRitorno().getPrezzo();
			p+=InterfacciaDB.getPrezzoCamera(viaggio.getPernottamento().getHotel().getId(),viaggio.getPernottamento().getTipoCamera());
			ArrayList<Attivita> a=viaggio.getAttivita();
			for(int i=0;i<a.size();i++){
				p+=a.get(i).getPrezzo();
			}
			viaggio.setPrezzo(p);
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
		if(viaggio.getAttivita().size()==viaggio.getPacchetto().getAttivita().size()){
				ArrayList<Attivita> attivitaPacchetto=viaggio.getPacchetto().getAttivita();
				for(Attivita a : viaggio.getAttivita()){
					if(!attivitaPacchetto.contains(a)){
						return false;
					}
				}
				return true;
		}
		return false;
	}
	private boolean confrontoVoli(){
		if(viaggio.getVoloAndata().getId()==viaggio.getPacchetto().getVoloAndata().getId() 
			&& viaggio.getVoloRitorno().getId()==viaggio.getPacchetto().getVoloRitorno().getId()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean confrontoPernottamenti(){
		if(viaggio.getPernottamento().getId()==viaggio.getPacchetto().getPernottamento().getId()){
			return true;
		}else{
			return false;
		}
	}	*/
}
