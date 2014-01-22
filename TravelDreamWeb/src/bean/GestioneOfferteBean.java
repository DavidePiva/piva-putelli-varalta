package bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import DTO.AttivitaDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.VoloDTO;

@ManagedBean(name = "go")
public class GestioneOfferteBean {

	@EJB
	private GestioneOfferteLocal gestioneOfferte;
	@EJB
	private DatiStaticiLocal datistatici;
	private int paginaSelezionata;
	private Date dataAndata;
	private Date dataRitorno;
	private PacchettoDTO pDTO;
	private String cittaPartenza;
	private AttivitaDTO attivita1;
	private AttivitaDTO attivita2;
	private AttivitaDTO attivita3;
	private AttivitaDTO attivita4;
	private AttivitaDTO attivita5;
	
	
	public GestioneOfferteBean() {
		pDTO = new PacchettoDTO();
		attivita1 = new AttivitaDTO();
		attivita2 = new AttivitaDTO();
		attivita3 = new AttivitaDTO();
		attivita4 = new AttivitaDTO();
		attivita5 = new AttivitaDTO();
		dataAndata = new Date();
		dataRitorno = new Date();
	}
	
	
	
	




	public void continua(){
		System.out.println("OOOOOOOOOOK, BUONA!");
		gestioneOfferte.setPaginaSelezionata(1);
		this.setPaginaSelezionata(gestioneOfferte.getPaginaSelezionata());
		System.out.println("OOOOOOOOOOK, SI RIPARTE!"+this.getPaginaSelezionata());
	}
	public String salva(){
		System.out.println("RICHIAMATA SALVAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!");
		pDTO.setSelezionabile(true);
		gestioneOfferte.salvaTutto(pDTO, attivita1, attivita2, attivita3, attivita4, attivita5);
		return "/impiegato/index?faces-redirect=true";
	}
	public boolean primaPaginaVisibile(){
		if(paginaSelezionata==0)
			return true;
		return false;
	}
	public boolean secondaPaginaVisibile(){
		if(paginaSelezionata==1)
			return true;
		return false;
	}
	public List<PernottamentoDTO> pernottamentiPossibili(){
		return datistatici.getPernottamentiPossibili(pDTO.getCitta());
	}
	public List<VoloDTO> voliPossibiliAndata(){
		return datistatici.getVoliPossibili(cittaPartenza, pDTO.getCitta(), Integer.parseInt(dataAndata.toString().substring(24, 28)), meseInt(dataAndata), Integer.parseInt(dataAndata.toString().substring(8, 10)));
	}
	public List<VoloDTO> voliPossibiliRitorno(){
		//return datistatici.getVoliPossibili(pDTO.getCitta(), cittaPartenza, dataAndata.getYear(), dataAndata.getMonth(), dataAndata.getDay());
		return datistatici.getVoliPossibili(pDTO.getCitta(), cittaPartenza, Integer.parseInt(dataRitorno.toString().substring(24, 28)), meseInt(dataRitorno), Integer.parseInt(dataRitorno.toString().substring(8, 10)));
	}
	public int meseInt(Date d){
		String s=d.toString().substring(4, 7);
		switch(s){
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
}
