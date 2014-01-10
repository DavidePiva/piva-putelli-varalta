package bean;

import javax.ejb.Local;

import DTO.UtenteDTO;



@Local
public interface GestioneProfiliLocal {
       
        public void salva(UtenteDTO utente);
        public void aggiorna(UtenteDTO utente);
        public void eliminaUtente();
        public boolean loggato();
        public UtenteDTO getUtenteDTO();

       
}
