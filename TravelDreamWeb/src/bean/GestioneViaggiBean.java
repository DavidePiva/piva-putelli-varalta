package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import DTO.HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;
import model.Viaggio;
import DTO.AttivitaDTO;

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
        private List<AttivitaDTO> attivita;
        private VoloDTO andata;
        private VoloDTO ritorno;
        private List<VoloDTO> voli;
        
       
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
        
        public List<AttivitaDTO> getAttivita(){
        	attivita = gestioneViaggi.getAttivitaViaggio(idViaggio);
        	return attivita;
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
        
}
