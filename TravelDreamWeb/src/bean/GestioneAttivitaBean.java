package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import DTO.AttivitaDTO;

@ManagedBean(name = "ga")
public class GestioneAttivitaBean {

	@EJB
	private GestioneComponentiLocal gc;
	@EJB
	private ShowAttivitaLocal sa;
	@EJB
	private DatiStaticiLocal ds;

	@Pattern(regexp = "^(?!^0)\\d{1,9}$", message = "Inserisci un valore positivo")
	@NotEmpty(message = "Inserisci un prezzo")
	String prezzo;
	private AttivitaDTO attivita;
	private Date data;
	private int ore;
	private int minuti;
	private String nomeAttivitaDaModificare;
	private int idAttivitaDaEliminare;
	
	@Pattern(regexp = "^$|^(?!^0)\\d{1,9}$", message = "Inserisci un valore positivo")
	private String prezzoNuovo;

	public String getPrezzoNuovo() {
		return prezzoNuovo;
	}

	public void setPrezzoNuovo(String prezzoNuovo) {
		this.prezzoNuovo = prezzoNuovo;
	}

	@NotEmpty(message = "Inserisci un titolo")
	private String titolo;

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@NotEmpty(message = "Inserisci una descrizione")
	private String descrizione;

	public GestioneAttivitaBean() {
		attivita = new AttivitaDTO();
		data = new Date();
	}

	public String creaAttivita() {

		attivita.setPrezzo(new BigDecimal(prezzo));
		@SuppressWarnings("deprecation")
		Time orario = new Time(ore, minuti, 0);
		attivita.setOra(orario);
		attivita.setData(data);
		attivita.setTitolo(titolo);
		attivita.setDescrizione(descrizione);

		gc.creaAttivita(attivita);
		return "/impiegato/index?faces-redirect=true";
	}

	public AttivitaDTO getAttivita() {
		return attivita;
	}

	public void setAttivita(AttivitaDTO attivita) {
		this.attivita = attivita;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public int getMinuti() {
		return minuti;
	}

	public void setMinuti(int minuti) {
		this.minuti = minuti;
	}

	public List<String> getOreList() {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < 24; i++)
			s.add((i < 10 ? "0" : "") + i);
		return s;
	}

	public List<String> getMinutiList() {
		List<String> s = new ArrayList<String>();
		for (int i = 0; i < 60; i = i + 5)
			s.add((i < 10 ? "0" : "") + i);
		return s;
	}

	public String eliminaAttivita() {
		gc.eliminaAttivita(idAttivitaDaEliminare);
		return "/impiegato/index?faces-redirect=true";
	}

	public String modificaAttivitaUguali() {

		List<AttivitaDTO> list = new ArrayList<AttivitaDTO>();
		list = ds.attivitaPerTitolo(nomeAttivitaDaModificare);
		for (int i = 0; i < list.size(); i++) {
			modificaAttivita(list.get(i));
		}

		return "/impiegato/index?faces-redirect=true";
	}

	public List<AttivitaDTO> getListaAttivita() {
		return ds.attivitaDTO();

	}

	private void modificaAttivita(AttivitaDTO attivitaDaModificare) {
		this.attivita.setSelezionabile(true);
		attivita.setId(attivitaDaModificare.getId());
		attivita.setCitta(attivitaDaModificare.getCitta());
		attivita.setData(attivitaDaModificare.getData());
		@SuppressWarnings("deprecation")
		Time orario = new Time(ore, minuti, 0);
		attivita.setOra(orario);
		if (attivita.getDescrizione().equals("")) {
			attivita.setDescrizione(attivitaDaModificare.getDescrizione());
		}
		if (prezzoNuovo.equals(""))
			attivita.setPrezzo(attivitaDaModificare.getPrezzo());
		else
			attivita.setPrezzo(new BigDecimal(prezzoNuovo));
		if (attivita.getTitolo().equals("")) {
			attivita.setTitolo(attivitaDaModificare.getTitolo());
		}
		if (attivita.getFoto1().equals("")) {
			attivita.setFoto1(attivitaDaModificare.getFoto1());
		}
		if (attivita.getFoto2().equals("")) {
			attivita.setFoto2(attivitaDaModificare.getFoto2());
		}
		if (attivita.getFoto3().equals("")) {
			attivita.setFoto3(attivitaDaModificare.getFoto3());
		}
		gc.modificaAttivita(attivita);

	}

	public String getNomeAttivitaDaModificare() {
		return nomeAttivitaDaModificare;
	}

	public void setNomeAttivitaDaModificare(String nomeAttivitaDaModificare) {
		this.nomeAttivitaDaModificare = nomeAttivitaDaModificare;
	}

	public int getIdAttivitaDaEliminare() {
		return idAttivitaDaEliminare;
	}

	public void setIdAttivitaDaEliminare(int idAttivitaDaEliminare) {
		this.idAttivitaDaEliminare = idAttivitaDaEliminare;
	}

	public void setDs(DatiStaticiLocal ds) {
		this.ds = ds;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
