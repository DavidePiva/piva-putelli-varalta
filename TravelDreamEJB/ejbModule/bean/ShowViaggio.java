package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Pacchetto;
import model.Pernottamento;
import model.Utente;
import model.Viaggio;
import model.Volo;
import DTO.UtenteDTO;
import DTO.ViaggioDTO;

/**
 * Session Bean implementation class ShowViaggio
 */
@Stateless
public class ShowViaggio implements ShowViaggioLocal {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private EJBContext context;
	
    public ShowViaggio() {
    }

	@Override
	public List<ViaggioDTO> getViaggiUtente(String email) {
		Query q = em.createNativeQuery("SELECT idViaggio FROM Viaggio WHERE pagato=0 AND titolare = '"+email+"'");
		List<Integer> l = q.getResultList();
		List<ViaggioDTO> lista = new ArrayList<ViaggioDTO>();
		for(int i = 0; i < l.size(); i++){
			int id = l.get(i);
			Viaggio v = em.find(Viaggio.class, id);
			lista.add(convertiViaggioDTO(v));
		}
		return lista;
	}

	private ViaggioDTO convertiViaggioDTO(Viaggio v) {
		ViaggioDTO v2 = new ViaggioDTO();
		int id = v.getIdViaggio();
		int numeroPersone = v.getNumeroPersone();
		Pernottamento p = v.getPernottamentoBean();
		Utente utente = v.getUtente();
		Volo a = v.getVolo1();
		Volo b = v.getVolo2();
		String citta = v.getCitta();
		BigDecimal prezzo = v.getPrezzo();
		boolean pagato = v.getPagato();
		Pacchetto pa = v.getPacchetto();
		v2.setTitolare(utente.getEmail());
		v2.setNomeTitolare(utente.getNome(), utente.getCognome());
		v2.setCitta(citta);
		v2.setIdPacchettoPadre(pa.getIdPacchetto());
		v2.setIdPernottamento(p.getIdPernottamento());
		v2.setPagato(pagato);
		v2.setIdViaggio(id);
		v2.setNumeroPersone(numeroPersone);
		v2.setIdVoloAndata(a.getIdVolo());
		v2.setIdVoloRitorno(b.getIdVolo());
		v2.setPrezzo(prezzo);
		return v2;
	}

	@Override
	public List<ViaggioDTO> getPartecipazioni(String emailUtente) {
		Query q = em.createNativeQuery("SELECT idViaggio FROM Partecipazione WHERE pagato = 0 AND emailPartecipante = '"+emailUtente+"'");
		List<ViaggioDTO> lista = new ArrayList<ViaggioDTO>();
		List<Integer> l = q.getResultList();
		for(int i = 0; i < l.size(); i++){
			int id = l.get(i);
			Viaggio v = em.find(Viaggio.class, id);
			lista.add(convertiViaggioDTO(v));
		}
		return lista;
	}

	@Override
	public List<UtenteDTO> getUtentiInvitati(int idViaggio) {
		Query q = em.createNativeQuery("SELECT emailPartecipante FROM Partecipazione WHERE pagato = 0  AND idViaggio = "+idViaggio);
		List<UtenteDTO> lista = new ArrayList<UtenteDTO>();
		List<String> emails = q.getResultList();
		for(int i = 0; i < emails.size(); i++){
			String email = emails.get(i);
			Utente u = em.find(Utente.class, email);
			lista.add(convertiInDTO(u));
		}
		return lista;
	}
	
	private UtenteDTO convertiInDTO(Utente u) {
		UtenteDTO uDTO = new UtenteDTO();
		uDTO.setEmail(u.getEmail());
		uDTO.setNome(u.getNome());
		uDTO.setCognome(u.getCognome());
		uDTO.setAttivo(u.getAttivo());
		return uDTO;
	}

	@Override
	public List<UtenteDTO> getUtentiConfermati(int idViaggio) {
		Query q = em.createNativeQuery("SELECT emailPartecipante FROM Partecipazione WHERE pagato = 1  AND idViaggio = "+idViaggio);
		List<UtenteDTO> lista = new ArrayList<UtenteDTO>();
		List<String> emails = q.getResultList();
		for(int i = 0; i < emails.size(); i++){
			String email = emails.get(i);
			Utente u = em.find(Utente.class, email);
			lista.add(convertiInDTO(u));
		}
		return lista;
	}

	@Override
	public List<UtenteDTO> getDonatori(int idViaggio) {
		List<UtenteDTO> lista = new ArrayList<UtenteDTO>();
		Query q = em.createNativeQuery("SELECT DISTINCT emailDonatore FROM Donazione_Attivita WHERE donato = 0 AND idViaggio = "+idViaggio);
		List<String> emails = q.getResultList();
		for(int i = 0; i < emails.size(); i++){
			String email = emails.get(i);
			Utente u = em.find(Utente.class, email);
			UtenteDTO u1 = convertiInDTO(u);
			lista.add(u1);
		}
		return lista;
	}

	@Override
	public List<ViaggioDTO> getViaggiDaRegalare(String emailUtente) {
		Query q = em.createNativeQuery("SELECT DISTINCT idViaggio FROM Donazione_Attivita WHERE donato = 0 AND emailDonatore = '"+emailUtente+"'");
		List<Integer> viaggi = q.getResultList();
		Query q2 = em.createNativeQuery("SELECT DISTINCT idViaggio FROM Donazione_Pernottamento WHERE donato = 0 AND emailDonatore = '"+emailUtente+"'");
		List<Integer> viaggi2 = q2.getResultList();
		Query q3 = em.createNativeQuery("SELECT DISTINCT idViaggio FROM Donazione_Volo WHERE donato = 0 AND emailDonatore = '"+emailUtente+"'");
		List<Integer> viaggi3 = q3.getResultList();
		List<ViaggioDTO> lista = new ArrayList<ViaggioDTO>();
		for(int i = 0; i < viaggi.size(); i++){
			for(int j = 0; j < viaggi2.size(); j++){
				int a = viaggi2.get(j);
				int b = viaggi.get(i);
				if(a==b){
					viaggi2.remove(j);
				}
			}
		}
		for(int i = 0; i < viaggi2.size(); i++){
			viaggi.add(viaggi2.get(i));
		}
		for(int i = 0; i < viaggi.size(); i++){
			for(int j = 0; j < viaggi3.size(); j++){
				int a = viaggi3.get(j);
				int b = viaggi.get(i);
				if(a==b){
					viaggi3.remove(j);
				}
			}
		}
		for(int i = 0; i < viaggi3.size(); i++){
			viaggi.add(viaggi3.get(i));
		}
		for(int i = 0; i < viaggi.size(); i++){
			Viaggio v = em.find(Viaggio.class, viaggi.get(i));
			lista.add(convertiViaggioDTO(v));
		}
		return lista;
	}

	@Override
	public List<ViaggioDTO> getPagati(String email) {
		Query q = em.createNativeQuery("SELECT idViaggio FROM Viaggio WHERE pagato=1 AND titolare = '"+email+"'");
		List<Integer> l = q.getResultList();
		List<ViaggioDTO> lista = new ArrayList<ViaggioDTO>();
		for(int i = 0; i < l.size(); i++){
			int id = l.get(i);
			Viaggio v = em.find(Viaggio.class, id);
			lista.add(convertiViaggioDTO(v));
		}
		return lista;
	}

	@Override
	public BigDecimal getPrezzoPernottamento(int idPernottamento) {
		Query q = em.createNativeQuery("SELECT prezzo FROM Pernottamento, TipoCamere_Hotel "
				+ "WHERE Pernottamento.hotel = TipoCamere_Hotel.idHotel AND "
				+ "Pernottamento.tipo = TipoCamere_Hotel.tipoCamera AND Pernottamento.idPernottamento ="+idPernottamento);
		@SuppressWarnings("unchecked")
		List<BigDecimal> l = q.getResultList();
		return l.get(0);
	}

	@Override
	public boolean pagabile(int idViaggio){
		List<UtenteDTO> l=getUtentiInvitati(idViaggio);
		if(l.size()==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean eleminabile(int idViaggio) {
		List<UtenteDTO> l=getUtentiConfermati(idViaggio);
		if(l.size()==0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public BigDecimal getPrezzoAttivita(int idAttivita) {
		Query q = em.createNativeQuery("SELECT prezzo FROM Attivita "
				+ "WHERE idAttivita = "+idAttivita);
		@SuppressWarnings("unchecked")
		List<BigDecimal> l = q.getResultList();
		return l.get(0);
	}

	@Override
	public BigDecimal getPrezzoVolo(int idVolo) {
		Query q = em.createNativeQuery("SELECT prezzo FROM Volo "
				+ "WHERE idVolo = "+idVolo);
		@SuppressWarnings("unchecked")
		List<BigDecimal> l = q.getResultList();
		return l.get(0);
	}
}
