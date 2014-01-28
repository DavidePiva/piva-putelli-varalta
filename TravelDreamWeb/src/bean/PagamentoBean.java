package bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;
import org.joda.time.Days;

import DTO.ViaggioDTO;
import DTO.VoloDTO;

@ManagedBean(name="pb")
@ViewScoped
public class PagamentoBean {
		
	@EJB
	private GestioneViaggiLocal gestioneViaggi;
	@EJB
	private ShowViaggioLocal showViaggio;
	@EJB
	private DatiStaticiLocal datistatici;
	
	private int idViaggio;
	private int numeroPersoneSelezionato;
	private int idPernottamento;
	private String donatore;
	private ViaggioDTO viaggio;
	private int idAttivita;
	private String invitato;
	private int idVolo;
	
	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public int getIdAttivita() {
		return idAttivita;
	}

	public int getIdVolo() {
		return idVolo;
	}

	public void setIdVolo(int idVolo) {
		this.idVolo = idVolo;
	}

	public void setIdAttivita(int idAttivita) {
		this.idAttivita = idAttivita;
	}

	public String getDonatore() {
		return donatore;
	}

	public void setDonatore(String donatore) {
		this.donatore = donatore;
	}
	
	public String getInvitato(){
		return invitato;
	}
	
	public void setInvitato(String invitato){
		this.invitato = invitato;
	}
	
	public void setIdViaggio(int idViaggio){
		this.idViaggio = idViaggio;
	}
	
	public int getIdViaggio(){
		return idViaggio;
	}

	
    public ViaggioDTO getViaggio(){

    	viaggio = gestioneViaggi.getViaggio(idViaggio);
    	return viaggio;
    }
    public BigDecimal getPrezzo(){
    	getViaggio();
    	BigDecimal p= this.viaggio.getPrezzo();
    	BigDecimal totale=p.multiply(new BigDecimal(viaggio.getNumeroPersone()));
    	return totale;
    }
    
    public String paga(){
    	gestioneViaggi.paga(idViaggio);
    	return "/user/index?faces-redirect=true";
    }

	public int getNumeroPersoneSelezionato() {
		return numeroPersoneSelezionato;
	}

	public void setNumeroPersoneSelezionato(int numeroPersoneSelezionato) {
		this.numeroPersoneSelezionato = numeroPersoneSelezionato;
	}
    public String setPersone(){
    	gestioneViaggi.setNumeroPersone(numeroPersoneSelezionato, idViaggio);
    	return "/user/modificaViaggio?faces-redirect=true&id="+idViaggio;
    }
    
    public BigDecimal getPrezzoPernottamento(){
    	getViaggio();
    	int v1 = viaggio.getIdVoloAndata();
    	int v2 = viaggio.getIdVoloRitorno();
    	VoloDTO andata = datistatici.getVoloDTO(v1);
    	VoloDTO ritorno = datistatici.getVoloDTO(v2);
    	Date data1 = andata.getData();
    	Date data2 = ritorno.getData();
    	DateTime dt1 = new DateTime(data1);
    	DateTime dt2 = new DateTime(data2);
    	int days = Days.daysBetween(dt1, dt2).getDays();
    	BigDecimal giorni = BigDecimal.valueOf(days);
    	idPernottamento = viaggio.getIdPernottamento();
    	BigDecimal prezzo = showViaggio.getPrezzoPernottamento(idPernottamento);
    	return prezzo.multiply(giorni);
    }

    public String pagaPernottamento(){
    	gestioneViaggi.pagaPernottamento(idViaggio,idPernottamento,donatore);
    	return "/user/index?faces-redirect=true";
    }
    
    public String pagaAttivita(){
    	gestioneViaggi.pagaAttivita(idViaggio,idAttivita,donatore);
    	return "/user/index?faces-redirect=true";
    }

    public String pagaVolo(){

    	gestioneViaggi.pagaVolo(idViaggio,idVolo,donatore);
    	return "/user/index?faces-redirect=true";
    }
    public BigDecimal getPrezzoAttivita(){
    	getViaggio();
    	return showViaggio.getPrezzoAttivita(idAttivita);
    }

    
    public String pagaPartecipazione(){
    	gestioneViaggi.pagaPartecipazione(idViaggio,invitato);
    	return "/user/index?faces-redirect=true";
    }
    public BigDecimal getPrezzoVolo(){
    	getViaggio();
    	
    	return showViaggio.getPrezzoVolo(idVolo);
    }
}
