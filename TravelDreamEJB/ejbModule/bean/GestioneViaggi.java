package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;
import model.Hotel;
import model.Partecipazione;
import model.PartecipazionePK;
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
@Stateful
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

	@Override
	public List<AttivitaDTO> getAttivitaViaggio(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		List<Viaggio_Attivita> l = v.getViaggioAttivitas();
		List<AttivitaDTO> lista = new ArrayList<AttivitaDTO>();
		for(int i = 0; i < l.size(); i++){
			Attivita a = l.get(i).getAttivita();
			lista.add(convertiAttivitaDTO(a));
		}
		return lista;
	}
	
	private AttivitaDTO convertiAttivitaDTO(Attivita a) {
		int id = a.getIdAttivita();
		String titolo = a.getTitolo();
		String citta = a.getCitta();
		String descrizione = a.getDescrizione();
		String foto1 = a.getFoto1();
		String foto2 = a.getFoto2();
		String foto3 = a.getFoto3();
		Date data = a.getData();
		Time ora = a.getOra();
		BigDecimal prezzo = a.getPrezzo();
		AttivitaDTO a2 = new AttivitaDTO();
		a2.setId(id);
		a2.setCitta(citta);
		a2.setTitolo(titolo);
		a2.setDescrizione(descrizione);
		a2.setData(data);
		a2.setOra(ora);
		a2.setFoto1(foto1);
		a2.setFoto2(foto2);
		a2.setFoto3(foto3);
		a2.setPrezzo(prezzo);
		return a2;
	}

	@Override
	public VoloDTO getAndataViaggio(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Volo andata = v.getVolo1();
		return convertiVoloDTO(andata);
	}

	@Override
	public VoloDTO getRitornoViaggio(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Volo ritorno = v.getVolo2();
		return convertiVoloDTO(ritorno);
	}
	
	private VoloDTO convertiVoloDTO(Volo v) {
		VoloDTO vDTO = new VoloDTO();
		vDTO.setOraPartenza(v.getOraPartenza());
		vDTO.setIdAeroportoPartenza(v.getAeroporto1().getIdAeroporto());
		vDTO.setOraArrivo(v.getOraArrivo());
		vDTO.setIdAeroportoArrivo(v.getAeroporto2().getIdAeroporto());
		vDTO.setIdVolo(v.getIdVolo());
		vDTO.setData(v.getData());
		vDTO.setPrezzo(v.getPrezzo());
		vDTO.setIdCompagnia(v.getCompagniaBean().getIdCompagnia());
		return vDTO;
	}

	@Override
	public void rimuoviViaggio(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		List<Viaggio_Attivita> l1 = v.getViaggioAttivitas();
		for(int i = 0; i < l1.size(); i++){
			em.remove(l1.get(i));
		}
		List<Viaggio_Pernottamento> vp = v.getViaggioPernottamentos();
		for(int i = 0; i < vp.size(); i++){
			em.remove(vp.get(i));
		}
		List<Viaggio_Volo> vv = v.getViaggioVolos();
		for(int i = 0; i < vv.size(); i++){
			em.remove(vv.get(i));
		}
		em.flush();
		em.remove(v);
	}

	@Override
	public void setNumeroPersone(int n, int idViaggio) {
		Viaggio v=em.find(Viaggio.class, idViaggio);
		v.setNumeroPersone(n);
		em.merge(v);
		
	}

	@Override
	public void sostituisciHotel(int idViaggio, int idHotelScelto, String tipoCameraScelto) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Pernottamento p = v.getPernottamentoBean();
		Hotel h = em.find(Hotel.class, idHotelScelto);
		p.setHotelBean(h);
		p.setTipo(tipoCameraScelto);
		em.merge(p);
		em.flush();
		BigDecimal prezzo = ricalcolaPrezzo(idViaggio);
		v.setPrezzo(prezzo);
		em.merge(v);
	}

	private BigDecimal ricalcolaPrezzo(int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		BigDecimal nuovoPrezzo = v.getPrezzo();
		Float f = nuovoPrezzo.floatValue();
		f+=100;
		nuovoPrezzo = BigDecimal.valueOf(f);
		return nuovoPrezzo;
	}

	@Override
	public void aggiungiPartecipazione(int idViaggio, String emailInvito) {
		Utente utente = em.find(Utente.class, emailInvito);
		Viaggio viaggio = em.find(Viaggio.class, idViaggio);
		PartecipazionePK pk = new PartecipazionePK();
		pk.setEmailPartecipante(emailInvito);
		pk.setIdViaggio(idViaggio);
		Partecipazione p = new Partecipazione();
		p.setId(pk);
		p.setPagato(false);
		p.setUtente(utente);
		p.setViaggio(viaggio);
		em.merge(p);
		viaggio.addPartecipazione(p);
		em.merge(viaggio);
	}

	@Override
	public void rendiRegalabile(int idViaggio) {
		Viaggio v=em.find(Viaggio.class, idViaggio);
		List<Viaggio_Pernottamento> pernottamenti=v.getViaggioPernottamentos();
		List<Viaggio_Volo> voli=v.getViaggioVolos();
		List<Viaggio_Attivita> attivita=v.getViaggioAttivitas();
		
		for(Viaggio_Pernottamento p : pernottamenti){
			p.setRegalabile(true);
		}
		for(Viaggio_Volo vo : voli){
			vo.setRegalabile(true);
		}
		for(Viaggio_Attivita a : attivita){
			a.setRegalabile(true);
		}
		
	}

	@Override
	public void rimuoviPartecipazione(int idViaggio, String emailInvito) {
		PartecipazionePK pk = new PartecipazionePK();
		pk.setEmailPartecipante(emailInvito);
		pk.setIdViaggio(idViaggio);
		Partecipazione p = em.find(Partecipazione.class, pk);
		em.remove(p);
	}

	@Override
	public boolean isRegalabile(int idViaggio) {
		Viaggio v=em.find(Viaggio.class, idViaggio);
		if(v.getViaggioPernottamentos().get(0).getRegalabile()){
			return true;
		}else{			
			return false;	
		}
	}

}
