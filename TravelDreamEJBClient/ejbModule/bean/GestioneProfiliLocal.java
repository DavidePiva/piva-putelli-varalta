package bean;

import java.util.List;

import javax.ejb.Local;

import DTO.UtenteDTO;



@Local
public interface GestioneProfiliLocal {
       
        public void salva(UtenteDTO utente);
        public void aggiorna(UtenteDTO utente);
        public void eliminaUtente();
        public boolean loggato();
        public UtenteDTO getUtenteDTO();
        public boolean isAdmin();
        public boolean isImpiegato();
        public List<UtenteDTO> listaUtenti();
        public void rendiImpiegato(String email);
        public List<UtenteDTO> listaImpiegati();
        public void rendiUtente(String email);
		public void salvaUtenteProvvisorio(String emailInvito);
}
