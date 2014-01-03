package business;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.*;
import enums.TargetPacchetto;
import enums.TipologiaPacchetto;

public class GestioneOfferte {
		
	Pacchetto pacchetto;
		
	public GestioneOfferte(){
		this.pacchetto=new Pacchetto();
	}
	
	public void nuovoPacchetto(Pacchetto p) throws SQLException{
		this.pacchetto=p;
		InterfacciaDB.inserisciPacchetto(p);
	}
	
	public void cambiaPacchettoDaGestire(Pacchetto p){
		this.pacchetto=p;
	}
	
	public boolean modificaVolo(Volo volo, boolean isAndata) throws SQLException{
		if(isAndata){
			this.pacchetto.setVoloAndata(volo);
			InterfacciaDB.modificaVoloInPacchetto(pacchetto.getId(),volo.getId(),true);
			return true;
		}else if(!isAndata){
			this.pacchetto.setVoloRitorno(volo);
			InterfacciaDB.modificaVoloInPacchetto(this.pacchetto.getId(),volo.getId(),false);
			return true;
		}else{
			return false;
		}
	}
	
	public void modificaPernottamento(Pernottamento pernottamento) throws SQLException{
		this.pacchetto.setPernottamento(pernottamento);
		InterfacciaDB.modificaPernottamentoInPacchetto(this.pacchetto.getId(),pernottamento.getId());
	}
	
	public void aggiungiAttivita(ArrayList<Attivita> attivita) throws SQLException{
		for(int i=0;i<attivita.size();i++){
			this.pacchetto.getAttivita().add(attivita.get(i));
			InterfacciaDB.inserisciAttivitaInPacchetto(this.pacchetto.getId(),attivita.get(i).getId());
		}
	}
	

	public void rimuoviAttivita(ArrayList<Attivita> attivita) throws SQLException{
		for(int i=0;i<attivita.size();i++){
			this.pacchetto.getAttivita().remove(attivita.get(i));
			InterfacciaDB.rimuoviAttivitaDaPacchetto(this.pacchetto.getId(),attivita.get(i).getId());
		}
	}
	
	public void modificaPrezzo(float pr) throws SQLException{
		this.pacchetto.setPrezzo(pr);
		InterfacciaDB.modificaPrezzoPacchetto(this.pacchetto.getId(),pr);
	}
	
	public void modificaTitolo(String t) throws SQLException{
		this.pacchetto.setTitolo(t);
		InterfacciaDB.modificaTitoloPacchetto(this.pacchetto.getId(),t);
	}
	
	public void modificaDescrizione(String d) throws SQLException{
		this.pacchetto.setDescrizione(d);
		InterfacciaDB.modificaDescrizionePacchetto(this.pacchetto.getId(),d);
	}
	
	public void modificaTarget(TargetPacchetto t) throws SQLException{
		this.pacchetto.setTarget(t);
		InterfacciaDB.modificaTargetPacchetto(this.pacchetto.getId(),t);
	}
	
	public void modificaFoto(String foto,int num){
		this.pacchetto.setFoto(foto,num);
//		InterfacciaDB.modificaFotoPacchetto(this.pacchetto.getId(),foto,num);
	}
	
	
	
	public void eliminaFotoPacchetto(int i){
		this.pacchetto.setFoto(null, i);
//		InterfacciaDB.eliminaFotoPacchetto(this.pacchetto.getId(),num);
	}
	
	public void modificaTipologia(TipologiaPacchetto tipo) throws SQLException{
		this.pacchetto.setTipologia(tipo);
		InterfacciaDB.modificaTipologiaPacchetto(this.pacchetto.getId(),tipo);
	}
	
}

