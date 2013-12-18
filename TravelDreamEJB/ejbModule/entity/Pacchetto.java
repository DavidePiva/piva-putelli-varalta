package entity;

import java.util.ArrayList;

import enums.TargetPacchetto;
import enums.TipologiaPacchetto;

public class Pacchetto {

	int id;
	Pernottamento pernottamento;
	Volo voloAndata;
	Volo voloRitorno;
	String citta;
	String descrizione;
	float prezzo;
	TipologiaPacchetto tipologia;
	TargetPacchetto target;
	ArrayList<Attivita> attivita;
	
	public Pacchetto() {
		super();
		this.id = 0;
		this.pernottamento = null;
		this.voloAndata = null;
		this.voloRitorno = null;
		this.citta = null;
		this.descrizione =null;
		this.prezzo = 0;
		this.tipologia = null;
		this.target = null;
	
		this.attivita=null;
	}
	
	public Pacchetto(int id, Pernottamento pernottamento, Volo voloAndata,
			Volo voloRitorno, String citta, String descrizione, float prezzo,
			TipologiaPacchetto tipologia, TargetPacchetto target) {
		super();
		this.id = id;
		this.pernottamento = pernottamento;
		this.voloAndata = voloAndata;
		this.voloRitorno = voloRitorno;
		this.citta = citta;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.tipologia = tipologia;
		this.target = target;
		this.attivita=attivita;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pernottamento getPernottamento() {
		return pernottamento;
	}
	public void setPernottamento(Pernottamento pernottamento) {
		this.pernottamento = pernottamento;
	}
	public Volo getVoloAndata() {
		return voloAndata;
	}
	public void setVoloAndata(Volo voloAndata) {
		this.voloAndata = voloAndata;
	}
	public Volo getVoloRitorno() {
		return voloRitorno;
	}
	public void setVoloRitorno(Volo voloRitorno) {
		this.voloRitorno = voloRitorno;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public TipologiaPacchetto getTipologia() {
		return tipologia;
	}
	public void setTipologia(TipologiaPacchetto tipologia) {
		this.tipologia = tipologia;
	}
	public TargetPacchetto getTarget() {
		return target;
	}
	public void setTarget(TargetPacchetto target) {
		this.target = target;
	}
	public ArrayList<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(ArrayList<Attivita> attivita) {
		this.attivita = attivita;
	}
	
	
}
