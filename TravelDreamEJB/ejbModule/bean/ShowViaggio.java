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

}
