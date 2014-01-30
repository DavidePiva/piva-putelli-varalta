package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import DTO.AttivitaDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.VoloDTO;

@ManagedBean(name = "go")
@ViewScoped
public class GestioneOfferteBean {

	@EJB
	private GestioneOfferteLocal gestioneOfferte;
	@EJB
	private DatiStaticiLocal datistatici;
	private int paginaSelezionata;
	private Date dataAndata;
	private Date dataRitorno;
	private PacchettoDTO pDTO;
	@NotEmpty(message="Campo obbligatorio")
	private String titolo;
	@NotEmpty(message="Campo obbligatorio")
	private String descrizione;
	private String citta;
	private String target;
	private String tipologia;
	private int idPacchettoModificare;
	private int idAttivitaDaAggiungere;
	private int idAttivitaDaEliminare;

	private String cittaPartenza;
	private AttivitaDTO attivita1;
	private AttivitaDTO attivita2;
	private AttivitaDTO attivita3;
	private AttivitaDTO attivita4;
	private AttivitaDTO attivita5;

	public GestioneOfferteBean() {
		paginaSelezionata = 0;
		pDTO = new PacchettoDTO();
		pDTOMod = new PacchettoDTO();
		dataAndata = new Date();
		dataRitorno = new Date();
		attivita1 = new AttivitaDTO();
		attivita2 = new AttivitaDTO();
		attivita3 = new AttivitaDTO();
		attivita4 = new AttivitaDTO();
		attivita5 = new AttivitaDTO();
		if (idPacchettoModificare > 0) {
			titolo = datistatici.getPacchettoDTO(idPacchettoModificare)
					.getTitolo();
		}
	}

	public void continuaCreazione() {
		// Passa alla seconda pagina del pacchetto
		if (dataAndata.before(dataRitorno) && !titolo.equals("")
				&& !descrizione.equals("") && !cittaPartenza.equals(citta))
			this.setPaginaSelezionata(1);
	}
	
	public void continuaModifica() {
		// Passa alla seconda pagina della modifica pacchetto
		if (dataAndataMod.before(dataRitornoMod) && !titoloMod.equals("")
				&& !descrizioneMod.equals(""))
			this.setPaginaSelezionata(1);
	}

	public String salva() {
		if (pDTO.getPrezzo() != null) {
			pDTO.setSelezionabile(true);
			pDTO.setTitolo(titolo);
			pDTO.setCitta(citta);
			pDTO.setDescrizione(descrizione);
			pDTO.setTarget(target);
			pDTO.setTipologia(tipologia);
			gestioneOfferte.salvaTutto(pDTO, attivita1, attivita2, attivita3,
					attivita4, attivita5);
			return "/index?faces-redirect=true";
		}
		return "";
	}

	public boolean primaPaginaVisibile() {
		if (paginaSelezionata == 0)
			return true;
		return false;
	}

	public boolean secondaPaginaVisibile() {
		if (paginaSelezionata == 1)
			return true;
		return false;
	}

	public boolean terzaPaginaVisibile() {
		if (paginaSelezionata == 2)
			return true;
		return false;
	}

	public List<PernottamentoDTO> pernottamentiPossibili() {
		return datistatici.getPernottamentiPossibili(getCitta());
	}

	public List<VoloDTO> voliPossibiliAndata() {
		return datistatici.getVoliPossibili(cittaPartenza, getCitta(),
				dataAndata.getYear() + 1900, meseInt(dataAndata),
				Integer.parseInt(dataAndata.toString().substring(8, 10)));
	}

	public List<VoloDTO> voliPossibiliRitorno() {
		return datistatici.getVoliPossibili(getCitta(), cittaPartenza,
				dataRitorno.getYear() + 1900, meseInt(dataRitorno),
				Integer.parseInt(dataRitorno.toString().substring(8, 10)));
	}

	public List<AttivitaDTO> getAttivitaPossibili() {
		return datistatici.getAttivitaPossibili(getCitta(),
				dataAndata.getYear() + 1900, meseInt(dataAndata),
				Integer.parseInt(dataAndata.toString().substring(8, 10)),
				dataRitorno.getYear() + 1900, meseInt(dataRitorno),
				Integer.parseInt(dataRitorno.toString().substring(8, 10)));
	}

	public List<AttivitaDTO> getAttivitaEliminabili() {
		return gestioneOfferte.attivitaEliminabili(idPacchettoModificare);
	}

	public List<AttivitaDTO> getAttivitaAggiungibili() {
		return gestioneOfferte.attivitaAggiungibili(idPacchettoModificare);
	}

	public String eliminaAttivitaDaPacchetto() {
		gestioneOfferte.eliminaAttivitaDaPacchetto(idPacchettoModificare,
				idAttivitaDaEliminare);
		return "/impiegato/modificaAttivitaPacchetto?faces-redirect=true&id="
				+ idPacchettoModificare;
	}

	public String aggiungiAttivitaAlPacchetto() {
		gestioneOfferte.aggiungiAttivitaAlPacchetto(idPacchettoModificare,
				idAttivitaDaAggiungere);
		return "/impiegato/modificaAttivitaPacchetto?faces-redirect=true&id="
				+ idPacchettoModificare;
	}

	public String eliminaPacchetto() {
		gestioneOfferte.eliminaPacchetto(idPacchettoModificare);
		return "/index?faces-redirect=true";
	}

	public String modificaPacchetto() {
		return "/impiegato/modificaPacchetto2?faces-redirect=true&id="
				+ idPacchettoModificare;
	}

	public String modificaAttivitaPacchetto() {
		return "/impiegato/modificaAttivitaPacchetto?faces-redirect=true&id="
				+ idPacchettoModificare;
	}

	public PacchettoDTO getPacchettoSelezionato() {
		return datistatici.getPacchettoDTO(idPacchettoModificare);
	}

	public int meseInt(Date d) {
		String s = d.toString().substring(4, 7);
		switch (s) {
		case "Jan":
			return 1;
		case "Feb":
			return 2;
		case "Mar":
			return 3;
		case "Apr":
			return 4;
		case "May":
			return 5;
		case "Jun":
			return 6;
		case "Jul":
			return 7;
		case "Aug":
			return 8;
		case "Sep":
			return 9;
		case "Oct":
			return 10;
		case "Nov":
			return 11;
		case "Dec":
			return 12;
		}
		return 0;
	}

	public Date getDataAndata() {
		return dataAndata;
	}

	public void setDataAndata(Date dataAndata) {
		this.dataAndata = dataAndata;
	}

	public Date getDataRitorno() {
		return dataRitorno;
	}

	public void setDataRitorno(Date dataRitorno) {
		this.dataRitorno = dataRitorno;
	}

	public int getPaginaSelezionata() {
		return paginaSelezionata;
	}

	public void setPaginaSelezionata(int paginaSelezionata) {
		this.paginaSelezionata = paginaSelezionata;
	}

	public PacchettoDTO getpDTO() {
		return pDTO;
	}

	public void setpDTO(PacchettoDTO pDTO) {
		this.pDTO = pDTO;
	}

	public String getCittaPartenza() {
		return cittaPartenza;
	}

	public void setCittaPartenza(String cittaPartenza) {
		this.cittaPartenza = cittaPartenza;
	}

	public AttivitaDTO getAttivita1() {
		return attivita1;
	}

	public void setAttivita1(AttivitaDTO attivita1) {
		this.attivita1 = attivita1;
	}

	public AttivitaDTO getAttivita2() {
		return attivita2;
	}

	public void setAttivita2(AttivitaDTO attivita2) {
		this.attivita2 = attivita2;
	}

	public AttivitaDTO getAttivita3() {
		return attivita3;
	}

	public void setAttivita3(AttivitaDTO attivita3) {
		this.attivita3 = attivita3;
	}

	public AttivitaDTO getAttivita4() {
		return attivita4;
	}

	public void setAttivita4(AttivitaDTO attivita4) {
		this.attivita4 = attivita4;
	}

	public AttivitaDTO getAttivita5() {
		return attivita5;
	}

	public void setAttivita5(AttivitaDTO attivita5) {
		this.attivita5 = attivita5;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
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

	public int getIdPacchettoModificare() {
		return idPacchettoModificare;
	}

	public void setIdPacchettoModificare(int idPacchettoModificare) {
		this.idPacchettoModificare = idPacchettoModificare;
	}

	public int getIdAttivitaDaAggiungere() {
		return idAttivitaDaAggiungere;
	}

	public void setIdAttivitaDaAggiungere(int idAttivitaDaAggiungere) {
		this.idAttivitaDaAggiungere = idAttivitaDaAggiungere;
	}

	public int getIdAttivitaDaEliminare() {
		return idAttivitaDaEliminare;
	}

	public void setIdAttivitaDaEliminare(int idAttivitaDaEliminare) {
		this.idAttivitaDaEliminare = idAttivitaDaEliminare;
	}

	// Pagina di modifica
	private Date dataAndataMod;
	private Date dataRitornoMod;
	private PacchettoDTO pDTOMod;
	@NotEmpty(message="Campo obbligatorio")
	private String titoloMod;
	@NotEmpty(message="Campo obbligatorio")
	private String descrizioneMod;
	private String targetMod;
	private String tipologiaMod;
	@Pattern(regexp="^(?!^0)\\d{1,9}$", message="Inserisci un valore positivo")
	@NotEmpty(message="Campo obbligatorio")
	private String prezzoMod;
	private String foto1Mod;
	private String foto2Mod;
	private String foto3Mod;
	private String foto4Mod;
	private String foto5Mod;
	private String foto6Mod;
	private int voloAndataMod;
	private int voloRitornoMod;
	private int idPernottamentoMod;

	// Metodi ad hoc per la pagina di modifica
	public List<String> targetColPrimoSelezionato() {
		List<String> l = new ArrayList<String>();
		System.out.println(datistatici.getPacchettoDTO(idPacchettoModificare)
				.getCitta()
				+ "   "
				+ datistatici.getPacchettoDTO(idPacchettoModificare)
						.getTarget());
		String t = datistatici.getPacchettoDTO(idPacchettoModificare)
				.getTarget();
		l.add(t);
		System.out.println("AGGiunta a lista " + t);
		if (!t.equals("singolo"))
			l.add("singolo");
		if (!t.equals("coppia"))
			l.add("coppia");
		if (!t.equals("gruppo"))
			l.add("gruppo");

		return l;
	}

	public List<String> tipologiaColPrimoSelezionato() {
		List<String> c = new ArrayList<String>();
		String t = datistatici.getPacchettoDTO(idPacchettoModificare)
				.getTipologia();

		c.add(t);
		if (!t.equals("culturale"))
			c.add("culturale");
		if (!t.equals("romantico"))
			c.add("romantico");
		if (!t.equals("relax"))
			c.add("relax");
		if (!t.equals("avventura"))
			c.add("avventura");

		return c;
	}

	public List<VoloDTO> voliPossibiliAndataMod() {
		return datistatici.getVoliPossibili(
				datistatici.getAeroportoDTO(
						datistatici.getVoloDTO(
								datistatici.getPacchettoDTO(
										idPacchettoModificare).getVoloAndata())
								.getIdAeroportoPartenza()).getCitta(),
				datistatici.getPacchettoDTO(idPacchettoModificare).getCitta(),
				dataAndataMod.getYear() + 1900, meseInt(dataAndataMod), Integer
						.parseInt(dataAndataMod.toString().substring(8, 10)));
	}

	public List<VoloDTO> voliPossibiliRitornoMod() {
		return datistatici.getVoliPossibili(
				datistatici.getPacchettoDTO(idPacchettoModificare).getCitta(),
				datistatici.getAeroportoDTO(
						datistatici.getVoloDTO(
								datistatici.getPacchettoDTO(
										idPacchettoModificare).getVoloAndata())
								.getIdAeroportoPartenza()).getCitta(),
				dataRitornoMod.getYear() + 1900, meseInt(dataRitornoMod),
				Integer.parseInt(dataRitornoMod.toString().substring(8, 10)));
	}

	public List<PernottamentoDTO> pernottamentiPossibiliMod() {
		return datistatici.getPernottamentiPossibili(datistatici
				.getPacchettoDTO(idPacchettoModificare).getCitta());
	}

	public String salvaMod() {


		pDTOMod.setIdPacchetto(idPacchettoModificare);
		pDTOMod.setSelezionabile(true);
		pDTOMod.setTitolo(titoloMod);
		pDTOMod.setDescrizione(descrizioneMod);
		pDTOMod.setTarget(targetMod);
		pDTOMod.setTipologia(tipologiaMod);
		pDTOMod.setFoto1(foto1Mod);
		pDTOMod.setFoto2(foto2Mod);
		pDTOMod.setFoto3(foto3Mod);
		pDTOMod.setFoto4(foto4Mod);
		pDTOMod.setFoto5(foto5Mod);
		pDTOMod.setFoto6(foto6Mod);
		pDTOMod.setPrezzo(new BigDecimal(prezzoMod));
		pDTOMod.setVoloAndata(voloAndataMod);
		pDTOMod.setVoloRitorno(voloRitornoMod);
		pDTOMod.setIdPernottamento(idPernottamentoMod);
		gestioneOfferte.modificaSalva(pDTOMod);

		return "/impiegato/index?faces-redirect=true";
	}

	public Date getDataAndataMod() {
		return datistatici.getVoloDTO(
				datistatici.getPacchettoDTO(idPacchettoModificare)
						.getVoloAndata()).getData();
	}

	public void setDataAndataMod(Date dataAndataMod) {
		this.dataAndataMod = dataAndataMod;
	}

	public Date getDataRitornoMod() {
		return datistatici.getVoloDTO(
				datistatici.getPacchettoDTO(idPacchettoModificare)
						.getVoloRitorno()).getData();
	}

	public void setDataRitornoMod(Date dataRitornoMod) {
		this.dataRitornoMod = dataRitornoMod;
	}

	public PacchettoDTO getpDTOMod() {
		return pDTOMod;
	}

	public void setpDTOMod(PacchettoDTO pDTOMod) {
		this.pDTOMod = pDTOMod;
	}

	public String getTitoloMod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getTitolo();
	}

	public void setTitoloMod(String titoloMod) {
		this.titoloMod = titoloMod;
	}

	public String getDescrizioneMod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare)
				.getDescrizione();
	}

	public void setDescrizioneMod(String descrizioneMod) {
		this.descrizioneMod = descrizioneMod;
	}

	public String getTargetMod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getTarget();
	}

	public void setTargetMod(String targetMod) {
		this.targetMod = targetMod;
	}

	public String getTipologiaMod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare)
				.getTipologia();
	}

	public void setTipologiaMod(String tipologiaMod) {
		this.tipologiaMod = tipologiaMod;
	}

	public String getPrezzoMod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getPrezzo().toString();
	}

	public void setPrezzoMod(String prezzoMod) {
		this.prezzoMod = prezzoMod;
	}

	public String getFoto1Mod() {

		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto1();
	}

	public void setFoto1Mod(String foto1Mod) {
		this.foto1Mod = foto1Mod;
	}

	public String getFoto2Mod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto2();
	}

	public void setFoto2Mod(String foto2Mod) {
		this.foto2Mod = foto2Mod;
	}

	public String getFoto3Mod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto3();
	}

	public void setFoto3Mod(String foto3Mod) {
		this.foto3Mod = foto3Mod;
	}

	public String getFoto4Mod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto4();
	}

	public void setFoto4Mod(String foto4Mod) {
		this.foto4Mod = foto4Mod;
	}

	public String getFoto5Mod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto5();
	}

	public void setFoto5Mod(String foto5Mod) {
		this.foto5Mod = foto5Mod;
	}

	public String getFoto6Mod() {
		return datistatici.getPacchettoDTO(idPacchettoModificare).getFoto6();
	}

	public void setFoto6Mod(String foto6Mod) {
		this.foto6Mod = foto6Mod;
	}

	public int getVoloAndataMod() {
		return voloAndataMod;
	}

	public void setVoloAndataMod(int voloAndataMod) {
		this.voloAndataMod = voloAndataMod;
	}

	public int getVoloRitornoMod() {
		return voloRitornoMod;
	}

	public void setVoloRitornoMod(int voloRitornoMod) {
		this.voloRitornoMod = voloRitornoMod;
	}

	public int getIdPernottamentoMod() {
		return idPernottamentoMod;
	}

	public void setIdPernottamentoMod(int idPernottamentoMod) {
		this.idPernottamentoMod = idPernottamentoMod;
	}

}