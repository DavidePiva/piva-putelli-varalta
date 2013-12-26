package entity;

import java.util.ArrayList;


public class Viaggio {

	int id;
	Pernottamento pernottamento;
	Volo voloAndata;
	Volo voloRitorno;
	String citta;
	float prezzo;
	Utente titolare;
	boolean pagato;
	int numeroPersone;
	ArrayList<Attivita> attivita;
	ArrayList<Utente> partecipanti;
	boolean regalabile;

	public Viaggio() {
	
		super();
		this.id = 0;
		this.pernottamento =null;
		this.voloAndata = null;
		this.voloRitorno = null;
		this.citta = null;
		this.prezzo = 0;
		this.titolare = null;
		this.pagato = false;
		this.numeroPersone = 0;
		this.attivita=null;
		this.partecipanti=new ArrayList<Utente>();
		this.regalabile=false;
	}
	
	public Viaggio(int id, Pernottamento pernottamento, Volo voloAndata,
			Volo voloRitorno, String citta, float prezzo, Utente titolare,
			boolean pagato, int numeroPersone, ArrayList<Attivita> attivita ) {
	
		super();
		this.id = id;
		this.pernottamento = pernottamento;
		this.voloAndata = voloAndata;
		this.voloRitorno = voloRitorno;
		this.citta = citta;
		this.prezzo = prezzo;
		this.titolare = titolare;
		this.pagato = pagato;
		this.numeroPersone = numeroPersone;
		this.attivita=attivita;
		this.partecipanti=new ArrayList<Utente>();
		this.regalabile=false;
	}
	
	public Viaggio(int id){
		this.id=id;
	}

	public ArrayList<Attivita> getAttivita() {
		return attivita;
	}

	public void setAttivita(ArrayList<Attivita> attivita) {
		this.attivita = attivita;
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

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public Utente getTitolare() {
		return titolare;
	}

	public void setTitolare(Utente titolare) {
		this.titolare = titolare;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public int getNumeroPersone() {
		return numeroPersone;
	}

	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}

	public ArrayList<Utente> getPartecipanti() {
		return partecipanti;
	}

	public void setPartecipanti(ArrayList<Utente> partecipanti) {
		this.partecipanti = partecipanti;
	}

	public boolean isRegalabile() {
		return regalabile;
	}

	public void setRegalabile(boolean regalabile) {
		this.regalabile = regalabile;
	}
	
	
}
