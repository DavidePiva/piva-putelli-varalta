package DTO;

import java.math.BigDecimal;


public class PacchettoDTO {

	private int idPacchetto;
	
	private int idPernottamento;
	
	private int voloAndata;
	
	private int voloRitorno;
	
	private String citta;

	private String descrizione;

	private String foto1;

	private String foto2;

	private String foto3;

	private String foto4;

	private String foto5;

	private String foto6;

	private BigDecimal prezzo;

	private boolean selezionabile;

	private String target;

	private String tipologia;

	private String titolo;

	public int getIdPacchetto() {
		return idPacchetto;
	}

	public void setIdPacchetto(int idPacchetto) {
		this.idPacchetto = idPacchetto;
	}

	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public int getVoloAndata() {
		return voloAndata;
	}

	public void setVoloAndata(int voloAndata) {
		this.voloAndata = voloAndata;
	}

	public int getVoloRitorno() {
		return voloRitorno;
	}

	public void setVoloRitorno(int voloRitorno) {
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

	public String getFoto1() {
		return foto1;
	}

	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}

	public String getFoto2() {
		return foto2;
	}

	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}

	public String getFoto3() {
		return foto3;
	}

	public void setFoto3(String foto3) {
		this.foto3 = foto3;
	}

	public String getFoto4() {
		return foto4;
	}

	public void setFoto4(String foto4) {
		this.foto4 = foto4;
	}

	public String getFoto5() {
		return foto5;
	}

	public void setFoto5(String foto5) {
		this.foto5 = foto5;
	}

	public String getFoto6() {
		return foto6;
	}

	public void setFoto6(String foto6) {
		this.foto6 = foto6;
	}

	public BigDecimal getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(BigDecimal prezzo) {
		this.prezzo = prezzo;
	}

	public boolean getSelezionabile() {
		return selezionabile;
	}

	public void setSelezionabile(boolean selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	
	
}
