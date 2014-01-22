package bean;

import java.math.BigDecimal;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import DTO.HotelDTO;
import DTO.ViaggioDTO;
import model.Viaggio;

@ManagedBean(name="gv")
@RequestScoped
public class GestioneViaggiBean {
       
        @EJB
        private GestioneViaggiLocal gestioneViaggi;
       
        @ManagedProperty("#{param.id}")
        private int idViaggio;
        
        private ViaggioDTO viaggio;
        private String citta;
        private int numeroPersone;
        private BigDecimal prezzo;
        private HotelDTO hotel;
        
       
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
        
        
}
