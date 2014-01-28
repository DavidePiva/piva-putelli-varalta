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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;
import model.Donazione_Attivita;
import model.Donazione_AttivitaPK;
import model.Donazione_Pernottamento;
import model.Donazione_PernottamentoPK;
import model.Donazione_Volo;
import model.Donazione_VoloPK;
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
	
	private int paginaSelezionata;
	
    public GestioneViaggi() {
    }

	@Override
	public void creaViaggio(String nomePacchetto, String emailUtente) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE titolo = '"+nomePacchetto+"'");
		@SuppressWarnings("unchecked")
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
		@SuppressWarnings("unchecked")
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
		System.out.println("ENTRATO IN GETATTIVITAVIAGGIO");/////////////////////////////////////////////////////
		/*Viaggio v = em.find(Viaggio.class, idViaggio);
		List<Viaggio_Attivita> l = v.getViaggioAttivitas();
		List<AttivitaDTO> lista = new ArrayList<AttivitaDTO>();
		for(int i = 0; i < l.size(); i++){
			Attivita a = l.get(i).getAttivita();
			lista.add(convertiAttivitaDTO(a));
		}
		System.out.println("LISTA "+lista.toString());
		return lista;*/
		
		
		Query q = em.createNativeQuery("SELECT idAttivita FROM Viaggio_Attivita WHERE idViaggio = "+idViaggio);
		List<Integer> ids = q.getResultList();
		List<AttivitaDTO> list  = new ArrayList<AttivitaDTO>();
		for(int i = 0; i < ids.size(); i++){
			Attivita a = em.find(Attivita.class, ids.get(i));
			list.add(convertiAttivitaDTO(a));
		}
		
		return list;

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
	public void aggiungiDonatore(int idViaggio, String emailSelezionata) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Utente utente = em.find(Utente.class, emailSelezionata);
		int idPernottamento = v.getPernottamentoBean().getIdPernottamento();
		Donazione_PernottamentoPK pk1 = new Donazione_PernottamentoPK();
		pk1.setEmailDonatore(emailSelezionata);
		pk1.setIdPernottamento(idPernottamento);
		pk1.setIdViaggio(idViaggio);
		Donazione_Pernottamento dp = new Donazione_Pernottamento();
		dp.setId(pk1);
		dp.setPernottamento(v.getPernottamentoBean());
		dp.setUtente(utente);
		dp.setDonato(false);
		dp.setViaggio(v);
		em.merge(dp);
		Donazione_VoloPK pk2 = new Donazione_VoloPK();
		pk2.setEmailDonatore(emailSelezionata);
		pk2.setIdVolo(v.getVolo1().getIdVolo());
		pk2.setIdViaggio(idViaggio);
		Donazione_Volo dv1 = new Donazione_Volo();
		dv1.setId(pk2);
		dv1.setUtente(utente);
		dv1.setVolo(v.getVolo1());
		dv1.setDonato(false);
		dv1.setViaggio(v);
		em.merge(dv1);
		Donazione_VoloPK pk3 = new Donazione_VoloPK();
		pk3.setEmailDonatore(emailSelezionata);
		pk3.setIdVolo(v.getVolo2().getIdVolo());
		pk3.setIdViaggio(idViaggio);
		Donazione_Volo dv2 = new Donazione_Volo();
		dv2.setId(pk3);
		dv2.setUtente(utente);
		dv2.setVolo(v.getVolo2());
		dv2.setDonato(false);
		dv2.setViaggio(v);
		em.merge(dv2);
		List<Viaggio_Attivita> l = v.getViaggioAttivitas();
		for(int i = 0; i < l.size(); i++){
			Viaggio_Attivita va = l.get(i);
			Attivita a = va.getAttivita();
			Donazione_AttivitaPK pki = new Donazione_AttivitaPK();
			pk1.setEmailDonatore(emailSelezionata);
			pki.setIdAttivita(a.getIdAttivita());
			pki.setIdViaggio(idViaggio);
			Donazione_Attivita dai = new Donazione_Attivita();
			dai.setId(pki);
			dai.setAttivita(a);
			dai.setDonato(false);
			dai.setUtente(utente);
			dai.setViaggio(v);
			em.merge(dai);
		}
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

	@Override
	public void rimuoviDonatore(int idViaggio, String utenteSelezionato) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		List<Donazione_Attivita> l1 = v.getDonazioneAttivitas();
		List<Donazione_Attivita> r = new ArrayList<Donazione_Attivita>();
		for(int i = 0; i < l1.size(); i++){
			Donazione_Attivita a = l1.get(i);
			Utente u = a.getUtente();
			String email = u.getEmail();
			if(!a.getDonato() && utenteSelezionato.equals(email)){
				r.add(a);
			}
		}
		List<Donazione_Pernottamento> l2 = v.getDonazionePernottamentos();
		List<Donazione_Pernottamento> r2 = new ArrayList<Donazione_Pernottamento>();
		for(int i = 0; i < l2.size(); i++){
			Donazione_Pernottamento a = l2.get(i);
			Utente u = a.getUtente();
			String email = u.getEmail();
			if(!a.getDonato() && utenteSelezionato.equals(email)){
				r2.add(a);
			}
		}
		List<Donazione_Volo> l3 = v.getDonazioneVolos();
		List<Donazione_Volo> r3 = new ArrayList<Donazione_Volo>();
		for(int i = 0; i < l3.size(); i++){
			Donazione_Volo a = l3.get(i);
			Utente u = a.getUtente();
			String email = u.getEmail();
			if(!a.getDonato() && utenteSelezionato.equals(email)){
				r3.add(a);
			}
		}
		for(int i = 0; i < r.size(); i++){
			em.remove(r.get(i));
		}
		for(int i = 0; i < r2.size(); i++){
			em.remove(r2.get(i));
		}
		for(int i = 0; i < r3.size(); i++){
			em.remove(r3.get(i));
		}
	}

	@Override
	public void paga(int idViaggio) {
		Query q = em.createNativeQuery("SELECT idViaggio FROM Viaggio WHERE idViaggio = "+idViaggio);
		@SuppressWarnings("unchecked")
		List<Integer> lista = q.getResultList();
		if(lista.size()==1){
			Viaggio v = em.find(Viaggio.class, lista.get(0));
			v.setPagato(true);
			em.merge(v);
		}
	}

	@Override
	public void pagaPernottamento(int idViaggio, int idPernottamento, String donatore) {
		Donazione_PernottamentoPK pk = new Donazione_PernottamentoPK();
		pk.setEmailDonatore(donatore);
		pk.setIdPernottamento(idPernottamento);
		pk.setIdViaggio(idViaggio);
		Donazione_Pernottamento dp = em.find(Donazione_Pernottamento.class, pk);
		dp.setDonato(true);
		em.merge(dp);
		Query q = em.createNativeQuery("SELECT emailDonatore FROM Donazione_Pernottamento WHERE idViaggio = "+ idViaggio+ " AND donato = 0");
		@SuppressWarnings("unchecked")
		List<String> emails = q.getResultList();

		for(int i = 0; i < emails.size(); i++){
			Donazione_PernottamentoPK temp = new Donazione_PernottamentoPK();
			temp.setEmailDonatore(emails.get(i));
			temp.setIdViaggio(idViaggio);
			temp.setIdPernottamento(idPernottamento);
			Donazione_Pernottamento rem = em.find(Donazione_Pernottamento.class, temp);
			em.remove(rem);
		}
	}


	@Override
	public void pagaAttivita(int idViaggio, int idAttivita, String donatore) {
		Donazione_AttivitaPK pk = new Donazione_AttivitaPK();
		pk.setEmailDonatore(donatore);
		pk.setIdAttivita(idAttivita);
		pk.setIdViaggio(idViaggio);
		Donazione_Attivita da = em.find(Donazione_Attivita.class, pk);
		da.setDonato(true);
		em.merge(da);
		Query q = em.createNativeQuery("SELECT DISTINCT emailDonatore FROM Donazione_Attivita WHERE idViaggio = "+ idViaggio+ " AND donato = 0");
		List<String> emails = q.getResultList();
		for(int i = 0; i < emails.size(); i++){
			Donazione_AttivitaPK temp = new Donazione_AttivitaPK();
			temp.setEmailDonatore(emails.get(i));
			temp.setIdViaggio(idViaggio);
			temp.setIdAttivita(idAttivita);
			Donazione_Attivita rem = em.find(Donazione_Attivita.class, temp);
			em.remove(rem);
		}
		
	}


	@Override
	public List<VoloDTO> getVoliRegalabili(int idViaggio) {
		Query q = em.createNativeQuery("SELECT DISTINCT idVolo FROM Donazione_Volo WHERE donato = 0 AND idViaggio ="+idViaggio);
		List<VoloDTO> voli = new ArrayList<VoloDTO>();
		List<Integer> lista = q.getResultList();
		for(int i = 0; i < lista.size(); i++){
			int id = lista.get(i);
			Volo v = em.find(Volo.class, id);
			voli.add(convertiVoloDTO(v));
		}
		return voli;
	}

	@Override
	public List<AttivitaDTO> getAttivitaRegalabili(int idViaggio) {
		Query q = em.createNativeQuery("SELECT DISTINCT idAttivita FROM Donazione_Attivita WHERE donato = 0 AND idViaggio ="+idViaggio);
		List<AttivitaDTO> attivita = new ArrayList<AttivitaDTO>();
		List<Integer> lista = q.getResultList();
		for(int i = 0; i < lista.size(); i++){
			int id = lista.get(i);
			Attivita v = em.find(Attivita.class, id);
			attivita.add(convertiAttivitaDTO(v));
		}
		return attivita;
	}

	
	public HotelDTO getHotelRegalabile(int idViaggio){
		Query q = em.createNativeQuery("SELECT DISTINCT idPernottamento FROM Donazione_Pernottamento WHERE donato = 0 AND idViaggio ="+idViaggio);
		List<Integer> lista = q.getResultList();
		if(lista.isEmpty()){
			return null;
		}
		int id = lista.get(0);
		Pernottamento p = em.find(Pernottamento.class, id);
		Hotel h = p.getHotelBean();
		return convertiHotelDTO(h);
	}

	public int getPaginaSelezionata() {
		return paginaSelezionata;
	}

	public void setPaginaSelezionata(int paginaSelezionata) {
		this.paginaSelezionata = paginaSelezionata;
	}

	@Override
	public void aggiungiAttivita(int idAttivita, int idViaggio) {
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Attivita a = em.find(Attivita.class, idAttivita);
		Viaggio_AttivitaPK pk = new Viaggio_AttivitaPK();
		pk.setIdAttivita(idAttivita);
		pk.setIdViaggio(idViaggio);
		Viaggio_Attivita va = new Viaggio_Attivita();
		va.setId(pk);
		va.setViaggio(v);
		va.setAttivita(a);
		va.setRegalabile(false);
		em.persist(va);
	}

	@Override
	public void cancellaTutteAttivita(int idViaggio) {
		Query q = em.createNativeQuery("SELECT idAttivita FROM Viaggio_Attivita WHERE idViaggio = "+idViaggio);
		List<Integer> ids = q.getResultList();
		for(int i = 0; i < ids.size(); i++){
			int id = ids.get(i);
			Viaggio_AttivitaPK pk = new Viaggio_AttivitaPK();
			pk.setIdAttivita(id);
			pk.setIdViaggio(idViaggio);
			Viaggio_Attivita va = em.find(Viaggio_Attivita.class, pk);
			em.remove(va);
		}
		Viaggio v = em.find(Viaggio.class, idViaggio);
		em.merge(v);
	}

	@Override
	public void rimuoviAttivita(int idAttivita, int idViaggio) {
		Viaggio_AttivitaPK pk = new Viaggio_AttivitaPK();
		pk.setIdAttivita(idAttivita);
		pk.setIdViaggio(idViaggio);
		Viaggio_Attivita va = em.find(Viaggio_Attivita.class, pk);
		em.remove(va);
	}

	@Override
	public void gestoreDate(ViaggioDTO viaggio, int idVolo1, int idVolo2) {
		int idViaggio = viaggio.getIdViaggio();
		Viaggio v = em.find(Viaggio.class, idViaggio);
		Volo andata = v.getVolo1();
		Volo ritorno = v.getVolo2();
		if(andata.getIdVolo()==idVolo1 && ritorno.getIdVolo()==idVolo2){
			return;
		}
		cancellaTutteAttivita(idViaggio);
		
		Volo nuovaAndata = em.find(Volo.class, viaggio.getIdVoloAndata());
		Volo nuovoRitorno = em.find(Volo.class, viaggio.getIdVoloRitorno());
		
		v.setVolo1(nuovaAndata);
		v.setVolo2(nuovoRitorno);
		
		em.merge(v);
	}
	
	
}
