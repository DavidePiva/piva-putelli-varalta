package bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import DTO.HotelDTO;
import DTO.ViaggioDTO;
import model.Hotel;
import model.Utente;
import model.Attivita;
import model.Pacchetto;
import model.Pernottamento;
import model.Viaggio;
import model.Viaggio_Attivita;
import model.Viaggio_AttivitaPK;
import model.Viaggio_Pernottamento;
import model.Viaggio_PernottamentoPK;
import model.Viaggio_Volo;
import model.Viaggio_VoloPK;
import model.Volo;

/**
 * Session Bean implementation class GestioneViaggi
 */
@Stateless
@LocalBean
public class GestioneViaggi implements GestioneViaggiLocal {
	

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;
	
    public GestioneViaggi() {
    }

	@Override
	public void creaViaggio(String nomePacchetto, String emailUtente) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+nomePacchetto+"'");
		List<Integer> l = q.getResultList();
		Pacchetto p = null;
		if(l.size()==1){
			p = em.find(Pacchetto.class, l.get(0));
		}
		Utente u = em.find(Utente.class, emailUtente);
		String citta = p.getCitta();
		Pernottamento pe = p.getPernottamentoBean();
		Volo andata = p.getVolo1();
		Volo ritorno = p.getVolo2();
		List<Attivita> a = p.getAttivitas();
		BigDecimal prezzo = p.getPrezzo();
		int id = p.getIdPacchetto();
		Viaggio v = new Viaggio();
		v.setPacchetto(p);
		v.setCitta(citta);
		v.setVolo1(andata);
		v.setVolo2(ritorno);
		v.setPernottamentoBean(pe);
		v.setPrezzo(prezzo);
		v.setUtente(u);
		v.setNumeroPersone(1);
		em.persist(v);
		em.flush();
		for(int i = 0; i < a.size(); i++){
			Attivita a1 = a.get(i);
			Viaggio_Attivita va = new Viaggio_Attivita();
			va.setAttivita(a1);
			va.setViaggio(v);
			va.setRegalabile(false);
			Viaggio_AttivitaPK pk = new Viaggio_AttivitaPK();
			pk.setIdAttivita(a1.getIdAttivita());
			pk.setIdViaggio(v.getIdViaggio());
			va.setId(pk);
			v.addViaggioAttivita(va);
		}
		Viaggio_PernottamentoPK vpk = new Viaggio_PernottamentoPK();
		vpk.setIdPernottamento(pe.getIdPernottamento());
		vpk.setIdViaggio(v.getIdViaggio());
		Viaggio_Pernottamento vp = new Viaggio_Pernottamento();
		vp.setId(vpk);
		vp.setRegalabile(false);
		vp.setPernottamento(pe);
		vp.setViaggio(v);
		v.addViaggioPernottamento(vp);
		Viaggio_VoloPK vv1 = new Viaggio_VoloPK();
		vv1.setIdViaggio(v.getIdViaggio());
		vv1.setIdVolo(andata.getIdVolo());
		Viaggio_Volo v1 = new Viaggio_Volo();
		v1.setId(vv1);
		v1.setRegalabile(false);
		v1.setViaggio(v);
		v1.setVolo(andata);
		v.addViaggioVolo(v1);
		Viaggio_VoloPK vv2 = new Viaggio_VoloPK();
		vv2.setIdViaggio(v.getIdViaggio());
		vv2.setIdVolo(andata.getIdVolo());
		Viaggio_Volo v2 = new Viaggio_Volo();
		v2.setId(vv2);
		v2.setRegalabile(false);
		v2.setViaggio(v);
		v2.setVolo(ritorno);
		v.addViaggioVolo(v2);
		em.merge(v);
	}

	@Override
	public ViaggioDTO getViaggio(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		return convertiViaggioDTO(v);
	}

	private ViaggioDTO convertiViaggioDTO(Viaggio v) {
		ViaggioDTO v2 = new ViaggioDTO();
		int idViaggio = v.getIdViaggio();
		int numeroPersone = v.getNumeroPersone();
		Volo volo1 = v.getVolo1();
		Volo volo2 = v.getVolo2();
		String citta = v.getCitta();
		Utente utente = v.getUtente();
		BigDecimal prezzo = v.getPrezzo();
		boolean pagato = v.getPagato();
		Pacchetto pacchettoPadre = v.getPacchetto();
		Pernottamento pe = v.getPernottamentoBean();
		v2.setIdViaggio(idViaggio);
		v2.setCitta(citta);
		v2.setNumeroPersone(numeroPersone);
		v2.setIdVoloAndata(volo1.getIdVolo());
		v2.setIdVoloRitorno(volo2.getIdVolo());
		v2.setPrezzo(prezzo);
		v2.setTitolare(utente.getEmail());
		v2.setPagato(pagato);
		v2.setIdPernottamento(pe.getIdPernottamento());
		v2.setIdPacchettoPadre(pacchettoPadre.getIdPacchetto());
		return v2;
	}

	@Override
	public HotelDTO getHotelViaggio(int idViaggio) {
		Query q = em.createNativeQuery("SELECT Pernottamento.hotel FROM Pernottamento,Viaggio WHERE Pernottamento.idPernottamento = Viaggio.pernottamento AND Viaggio.idViaggio = "+idViaggio);
		List<Integer> l = q.getResultList();
		Hotel h = new Hotel();
		if(l.size()==1){
			h = em.find(Hotel.class, l.get(0));
		}
		return convertiHotelDTO(h);
	}
	
	public HotelDTO convertiHotelDTO(Hotel h){
		int id = h.getIdHotel();
		String nome = h.getNome();
		String citta = h.getCitta();
		String descrizione = h.getDescrizione();
		String telefono = h.getTelefono();
		String indirizzo = h.getIndirizzo();
		boolean selezionabile = h.getSelezionabile();
		String foto1 = h.getFoto1();
		String foto2 = h.getFoto2();
		String foto3 = h.getFoto3();
		HotelDTO h2 = new HotelDTO();
		h2.setIdHotel(id);
		h2.setNome(nome);
		h2.setCitta(citta);
		h2.setIndirizzo(indirizzo);
		h2.setTelefono(telefono);
		h2.setDescrizione(descrizione);
		h2.setSelezionabile(selezionabile);
		h2.setFoto1(foto1);
		h2.setFoto2(foto2);
		h2.setFoto3(foto3);
		return h2;
	}

}
