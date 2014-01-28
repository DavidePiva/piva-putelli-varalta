package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DTO.HotelDTO;
import DTO.TipoCamere_HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;
import model.TipoCamere_Hotel;
import model.Viaggio;
import DTO.AttivitaDTO;

@ManagedBean(name="gv")
@ViewScoped
public class GestioneViaggiBean {
       
        @EJB
        private GestioneViaggiLocal gestioneViaggi;
    	@EJB
    	private ShowViaggioLocal showViaggio;
    	
        @EJB
        private DatiStaticiLocal datistatici;
        
        private int idViaggio;
        
        private ViaggioDTO viaggio;
        private String citta;
        private int numeroPersone;
        private BigDecimal prezzo;
        private HotelDTO hotel;
        private VoloDTO andata;
        private VoloDTO ritorno;
        private List<VoloDTO> voli;
        private int idHotelScelto;
        private String tipoCameraScelto;
        private List<HotelDTO> hotelPossibili;
        private List<TipoCamere_HotelDTO> camerePossibili;
        
        public void setTipoCameraScelto(String tipoCameraScelto){
        	this.tipoCameraScelto = tipoCameraScelto;
        }
        
        public String getTipoCameraScelto(){
        	return tipoCameraScelto;
        }
        
        public void setIdHotelScelto(int idHotelScelto){
        	this.idHotelScelto = idHotelScelto;
        }
        
        public int getIdHotelScelto(){
        	return idHotelScelto;
        }
        
        
        public void setIdViaggio(int idViaggio){
                this.idViaggio = idViaggio;
        }
       
        public int getIdViaggio(){
                return idViaggio;
        }
       
        public String creaViaggio(String nomePacchetto, String emailUtente){
                gestioneViaggi.creaViaggio(nomePacchetto, emailUtente);
                return "/user/index?faces-redirect=true";
        }
        
        public ViaggioDTO getViaggio(){
        	if(idViaggio==0)
        	{
        		String a = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        		idViaggio=Integer.parseInt(a);
        	}
        	viaggio = gestioneViaggi.getViaggio(idViaggio);
        	return viaggio;
        }
        
        public String getCitta(){
        	getViaggio();
        	citta = viaggio.getCitta();
        	return citta;
        }
        
        public int getNumeroPersone(){
        	numeroPersone = viaggio.getNumeroPersone();
        	return numeroPersone;
        }
        
        public BigDecimal getPrezzo(){
        	prezzo = viaggio.getPrezzo();
        	return prezzo;
        }
        
        public HotelDTO getHotel(){
        	hotel = gestioneViaggi.getHotelViaggio(idViaggio);
        	return hotel;
        }
        
        public List<AttivitaDTO> getAttivita(){
        	return gestioneViaggi.getAttivitaViaggio(idViaggio);
        }
        
        public VoloDTO getAndata(){
        	voli = new ArrayList<VoloDTO>();
        	andata = gestioneViaggi.getAndataViaggio(idViaggio);
        	voli.add(andata);
        	return andata;
        }
        
        public VoloDTO getRitorno(){
        	ritorno = gestioneViaggi.getRitornoViaggio(idViaggio);
        	voli.add(ritorno);
        	return ritorno;
        }
        
        public List<VoloDTO> getVoli(){
        	getAndata();
        	getRitorno();
        	return voli;
        }
        
        public List<HotelDTO> getHotelPossibili(){
        	hotelPossibili = datistatici.hotelPerCitta(getCitta());
        	return hotelPossibili;
        }
        
        public List<TipoCamere_HotelDTO> getCamerePossibili(){
        	camerePossibili = datistatici.camerePerHotel(idHotelScelto);
        	return camerePossibili;
        }
        
        public String acquista(){
        	return "/user/pagamento?faces-redirect=true&id="+idViaggio;
        }
        
        public String rimuovi(){
        	gestioneViaggi.rimuoviViaggio(idViaggio);
        	return "/user/index?faces-redirect=true";
        }
        
        public String modificaNumeroPersone(){
        	return "/user/numeroPersone?faces-redirect=true&id="+idViaggio;
        }
        
        public String scegliHotel(){
        	return "/user/scegliHotel?faces-redirect=true&id="+idViaggio;
        }
        
        public String scegliCamera(){
        	return "/user/scegliCamera?faces-redirect=true&id="+idViaggio+"&idHotel="+idHotelScelto;
        }
        
        public String sostituisciHotel(){
        	gestioneViaggi.sostituisciHotel(idViaggio,idHotelScelto,tipoCameraScelto.toLowerCase());
        	return "/user/modificaViaggio?faces-redirect=true&id="+idViaggio;
        }
        
        public String invita(){
        	return "/user/inviti?faces-redirect=true&id="+idViaggio;
        }
        
        public String regali(){
        	return "/user/regali?faces-redirect=true&id="+idViaggio;
        }
        
        public String rendiRegalabile(){
        	gestioneViaggi.rendiRegalabile(idViaggio);
        	return "/user/modificaViaggio?faces-redirect=true&id="+idViaggio;
        }
        
        public boolean nonRegalabile(){
        	boolean regalabile=gestioneViaggi.isRegalabile(idViaggio);
        	return !regalabile;
        }
        
        public boolean pagabile(){
        	if(showViaggio.pagabile(idViaggio)){
        		return true;
        	}else{
        		return false;
        	}
        }
        
        public boolean nonPagabile(){
        	return !pagabile();
        }
       
        public boolean eliminabile(){
        	if(showViaggio.eleminabile(idViaggio) && pagabile()){
           			return true;
            	}else{
            		return false;
            	}
        }
        
        public String scegliDate(){
        	return "/user/modificaDate?faces-redirect=true&id="+idViaggio;
        }
        
}
