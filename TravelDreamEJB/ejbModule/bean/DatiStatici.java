package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Attivita;
import model.Aeroporto;
import model.Compagnia;
import model.Hotel;
import model.Pacchetto;
import model.Pernottamento;
import model.TipoCamere_Hotel;
import model.TipoCamere_HotelPK;
import model.Viaggio;
import model.Volo;
import DTO.AeroportoDTO;
import DTO.AttivitaDTO;
import DTO.HotelDTO;
import DTO.PacchettoDTO;
import DTO.PernottamentoDTO;
import DTO.TipoCamera;
import DTO.TipoCamere_HotelDTO;
import DTO.ViaggioDTO;
import DTO.VoloDTO;


/**
 * Session Bean implementation class DatiStatici
 */
@Stateless
public class DatiStatici implements DatiStaticiLocal {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public DatiStatici() {
    }
    
    @Override
    public String esempio(){
    	return "Hello world!";
    }

	@Override
	public AeroportoDTO getAeroportoDTO() {
		AeroportoDTO aeroportoDTO = convertiInDTO(getAeroporto(1));
		return aeroportoDTO;
	}
	
	private AeroportoDTO convertiInDTO(Aeroporto aeroporto) {
		AeroportoDTO a = new AeroportoDTO();
		a.setId(aeroporto.getIdAeroporto());
		a.setNome(aeroporto.getNome());
		a.setCitta(aeroporto.getCitta());
		return a;
	}

	public Aeroporto getAeroporto(int id){
		Aeroporto a = em.find(Aeroporto.class,id);
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getDestinazioni() {
		Query q = em.createNativeQuery("SELECT DISTINCT citta FROM Aeroporto ORDER BY citta ASC");
		@SuppressWarnings("rawtypes")
		List a = new ArrayList<String>();
		a = q.getResultList();
		return a;
	}
	
	@SuppressWarnings("unchecked")
	public List<HotelDTO> hotelPerCitta(String s){
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE selezionabile = 1 AND citta = '"+s+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<HotelDTO> lista = new ArrayList<HotelDTO>();
		for(int index = 0; index < i.size(); index++){
			Hotel h = em.find(Hotel.class, i.get(index));
			lista.add(convertiHotelDTO(h));
		}
		return lista;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerCitta(String s) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND citta = '"+s+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}
	
	public PacchettoDTO convertiPacchettoDTO(Pacchetto p){
		PacchettoDTO p1 = new PacchettoDTO();
		int idPacchetto = p.getIdPacchetto();
		Pernottamento pe = p.getPernottamentoBean();
		Volo v1 = p.getVolo1();
		Volo v2 = p.getVolo2();
		String citta = p.getCitta();
		String titolo = p.getTitolo();
		String descrizione = p.getDescrizione();
		BigDecimal prezzo = p.getPrezzo();
		boolean selezionabile = p.getSelezionabile();
		p1.setIdPacchetto(idPacchetto);
		p1.setCitta(citta);
		p1.setTitolo(titolo);
		p1.setPrezzo(prezzo);
		p1.setDescrizione(descrizione);
		p1.setVoloAndata(v1.getIdVolo());
		p1.setVoloRitorno(v2.getIdVolo());
		p1.setIdPernottamento(pe.getIdPernottamento());
		p1.setSelezionabile(selezionabile);
		p1.setTarget(p.getTarget());
		p1.setTipologia(p.getTipologia());
		p1.setFoto1(p.getFoto1());
		p1.setFoto2(p.getFoto2());
		p1.setFoto3(p.getFoto3());
		p1.setFoto4(p.getFoto4());
		p1.setFoto5(p.getFoto5());
		p1.setFoto6(p.getFoto6());
		return p1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> tipiPacchetto() {
		Query q = em.createNativeQuery("SELECT DISTINCT tipologia FROM Pacchetto WHERE selezionabile = 1");
		List<String> l1 = new ArrayList<String>();
		l1 = q.getResultList();
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerTipo(String tipoSelezionato) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND tipologia = '"+tipoSelezionato+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo,int prezzoMassimo) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND prezzo BETWEEN " + prezzoMinimo+ " AND "+ prezzoMassimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerPrezzo(int prezzoMinimo) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND prezzo > " + prezzoMinimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerHotel(String hotelSelezionato) {
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE selezionabile = 1 AND nome = '"+hotelSelezionato+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		int id = 0;
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		if(i.size()==1){
			id = i.get(0);
		}
		Query q2 = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto,Pernottamento WHERE Pacchetto.selezionabile = 1 AND Pacchetto.pernottamento = Pernottamento.idPernottamento AND Pernottamento.hotel = "+id);
		List<Integer> j = new ArrayList<Integer>();
		j = q2.getResultList();
		for(int index = 0; index < j.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, j.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	public int getIdPernottamento(String tipoCamera,int idHotel){
		Query q = em.createNativeQuery("SELECT idPernottamento FROM Pernottamento WHERE selezionabile = 1 AND hotel = "+idHotel+" AND tipo = "+tipoCamera);
		List<Integer> list=new ArrayList<Integer>();
		list=q.getResultList();
		
		int i = list.get(0);
		return i;
	}
	
	@SuppressWarnings("unchecked")
	public TipoCamere_Hotel getTipoCamere_Hotel(int idHotel,String tipoCamera){
		Query q = em.createNativeQuery("SELECT  FROM TipoCamere_Hotel WHERE idHotel = "+idHotel+" AND tipoCamera = '"+tipoCamera+"'");
		List<TipoCamere_Hotel> list = new ArrayList<TipoCamere_Hotel>();
		list=q.getResultList();
		TipoCamere_Hotel t=list.get(0);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public BigDecimal getPrezzoCamera(int idHotel,String tipoCamera){
		Query q = em.createNativeQuery("SELECT prezzo FROM TipoCamere_Hotel WHERE idHotel = "+idHotel+" AND tipoCamera = '"+tipoCamera+"'");
		List<TipoCamere_Hotel> list = new ArrayList<TipoCamere_Hotel>();
		list=q.getResultList();
		BigDecimal prezzo=list.get(0).getPrezzo();
		return prezzo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoloDTO> getVoliPossibili(String cittaPartenza, String cittaArrivo, int anno, int mese, int giorno) {
		Query q = em.createNativeQuery("SELECT idVolo FROM Volo,Aeroporto WHERE (Aeroporto.idAeroporto = Volo.aeroportoPartenza AND Aeroporto.citta = '"+cittaPartenza+"') AND Volo.idVolo IN (SELECT idVolo FROM Volo, Aeroporto WHERE Aeroporto.idAeroporto = Volo.aeroportoArrivo AND Aeroporto.citta = '"+cittaArrivo+"')AND data = '"+anno+"-"+mese+"-"+giorno+"'");
		System.out.println("ECCO: SELECT idVolo FROM Volo,Aeroporto WHERE (Aeroporto.idAeroporto = Volo.aeroportoPartenza AND Aeroporto.citta = '"+cittaPartenza+"') AND Volo.idVolo IN (SELECT idVolo FROM Volo, Aeroporto WHERE Aeroporto.idAeroporto = Volo.aeroportoArrivo AND Aeroporto.citta = '"+cittaArrivo+"')AND data = '"+anno+"-"+mese+"-"+giorno+"'");
		List<Integer> i = new ArrayList<Integer>();
		List<VoloDTO> l = new ArrayList<VoloDTO>();
		i = q.getResultList();
		for(int j = 0; j < i.size(); j++){
			Volo v = em.find(Volo.class, i.get(j));
			VoloDTO v2 = convertiVoloDTO(v);
			l.add(v2);
		}
		return l;
	}

	private VoloDTO convertiVoloDTO(Volo v) {
		VoloDTO v2 = new VoloDTO();
		int idVolo = v.getIdVolo();
		Aeroporto a1 = v.getAeroporto1();
		Aeroporto a2 = v.getAeroporto2();
		BigDecimal prezzo = v.getPrezzo();
		Compagnia c = v.getCompagniaBean();
		Date data = v.getData();
		Time oraPartenza = v.getOraPartenza();
		Time oraArrivo = v.getOraArrivo();
		v2.setIdVolo(idVolo);
		v2.setData(data);
		v2.setIdAeroportoPartenza(a1.getIdAeroporto());
		v2.setIdAeroportoArrivo(a2.getIdAeroporto());
		v2.setIdCompagnia(c.getIdCompagnia());
		v2.setPrezzo(prezzo);
		v2.setOraArrivo(oraArrivo);
		v2.setOraPartenza(oraPartenza);
		return v2;
	}

	@Override
	public List<AttivitaDTO> getAttivitaPossibili(String citta, int anno1,
			int mese1, int giorno1, int anno2, int mese2, int giorno2) {
		Query q = em.createNativeQuery("SELECT idAttivita FROM Attivita WHERE selezionabile = 1 AND citta = '"+citta+"' AND data BETWEEN '"+anno1+"-"+mese1+"-"+giorno1+"' AND '"+anno2+"-"+mese2+"-"+giorno2+"'");
		@SuppressWarnings("unchecked")
		List<Integer> l = q.getResultList();
		List<AttivitaDTO> l1 = new ArrayList<AttivitaDTO>();
		for(int i = 0; i < l.size(); i++){
			Attivita a = em.find(Attivita.class, l.get(i));
			l1.add(convertiAttivitaDTO(a));
		}
		return l1;
	}

	private AttivitaDTO convertiAttivitaDTO(Attivita a) {
		int id = a.getIdAttivita();
		String titolo = a.getTitolo();
		String citta = a.getCitta();
		String descrizione = a.getDescrizione();
		String foto1 = a.getFoto1();
		String foto2 = a.getFoto2();
		String foto3 = a.getFoto3();
		boolean selezionabile = a.getSelezionabile();
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
		a2.setSelezionabile(selezionabile);
		return a2;
	}

	@Override
	public List<PernottamentoDTO> getPernottamentiPossibili(String citta) {
		Query q = em.createNativeQuery("SELECT idPernottamento FROM Pernottamento,Hotel WHERE Pernottamento.hotel = Hotel.idHotel AND Pernottamento.selezionabile = 1 AND Hotel.citta = \""+citta+"\"");
		@SuppressWarnings("unchecked")
		List<Integer> l = q.getResultList();
		List<PernottamentoDTO> l1 = new ArrayList<PernottamentoDTO>();
		for(int i = 0; i < l.size(); i++){
			Pernottamento p = em.find(Pernottamento.class, l.get(i));
			l1.add(convertiPernottamentoDTO(p));
		}
		return l1;
	}

	private PernottamentoDTO convertiPernottamentoDTO(Pernottamento p) {
		PernottamentoDTO p2 = new PernottamentoDTO();
		int id = p.getIdPernottamento();
		Hotel h = p.getHotelBean();
		String tipo = p.getTipo();
		boolean selezionabile = p.getSelezionabile();
		p2.setIdHotel(h.getIdHotel());
		p2.setTipo(tipo);
		p2.setIdPernottamento(id);
		p2.setSelezionabile(selezionabile);
		return p2;
	}

	@Override
	public List<String> getTarget() {
		Query q = em.createNativeQuery("SELECT DISTINCT target FROM Pacchetto");
		@SuppressWarnings("unchecked")
		List<String> l = q.getResultList();
		return l;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiPerTarget(String targetSelezionato) {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 AND target = '"+targetSelezionato+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDTO> getHotelPerPrezzo(int prezzoMinimo, int prezzoMassimo) {
		Query q = em.createNativeQuery("SELECT Hotel.idHotel FROM Hotel, TipoCamere_Hotel WHERE Hotel.idHotel = TipoCamere_Hotel.idHotel AND prezzo<>0 GROUP BY Hotel.idHotel HAVING AVG(prezzo) BETWEEN "+prezzoMinimo+" AND "+prezzoMassimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<HotelDTO> l1 = new ArrayList<HotelDTO>();
		for(int index = 0; index < i.size(); index++){
			Hotel h = em.find(Hotel.class, i.get(index));
			l1.add(convertiHotelDTO(h));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelDTO> getHotelPerPrezzo(int prezzoMinimo) {
		Query q = em.createNativeQuery("SELECT Hotel.idHotel FROM Hotel, TipoCamere_Hotel WHERE Hotel.idHotel = TipoCamere_Hotel.idHotel AND prezzo<>0 GROUP BY Hotel.idHotel HAVING AVG(prezzo) > "+prezzoMinimo);
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<HotelDTO> l1 = new ArrayList<HotelDTO>();
		for(int index = 0; index < i.size(); index++){
			Hotel h = em.find(Hotel.class, i.get(index));
			l1.add(convertiHotelDTO(h));
		}
		return l1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttivitaDTO> attivitaPerTitolo(String titolo) {
		Query q=em.createNativeQuery("SELECT idAttivita FROM Attivita WHERE selezionabile = 1 AND titolo = '"+titolo+"'");
		List<Integer> list=new ArrayList<Integer>();
		list=q.getResultList();
		List<AttivitaDTO> listDTO=new ArrayList<AttivitaDTO>();
		for(int i=0;i<list.size();i++){
			Attivita a = em.find(Attivita.class, list.get(i));
			listDTO.add(convertiAttivitaDTO(a));
		}	
		return listDTO;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AttivitaDTO> attivitaDTO() {
		Query q=em.createNativeQuery("SELECT idAttivita FROM Attivita WHERE selezionabile = 1");
		List<Integer> list=new ArrayList<Integer>();
		list=q.getResultList();
		List<AttivitaDTO> listDTO=new ArrayList<AttivitaDTO>();
		for(int i=0;i<list.size();i++){
			Attivita a = em.find(Attivita.class, list.get(i));
			listDTO.add(convertiAttivitaDTO(a));
		}	
		return listDTO;
	}

	@Override
	public HotelDTO getHotelPerId(int idHotel) {
		Hotel h = em.find(Hotel.class, idHotel);
		return convertiHotelDTO(h);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HotelDTO getHotelPerNome(String nome){
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE selezionabile = 1 AND nome = '"+nome+"'");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		HotelDTO l1 = new HotelDTO();
		Hotel h = em.find(Hotel.class, i.get(0));
		l1=convertiHotelDTO(h);
		return l1;
	}
	
	@Override
	public ViaggioDTO getViaggioDTO(int id){
		Viaggio v=new Viaggio();
		v=em.find(Viaggio.class, id);
		ViaggioDTO vDTO=convertiViaggioInDTO(v);
		return vDTO;
	}
	
	private ViaggioDTO convertiViaggioInDTO(Viaggio v) {
		ViaggioDTO viaggio=new ViaggioDTO();
		viaggio.setIdViaggio(v.getIdViaggio());
		viaggio.setCitta(v.getCitta());
		viaggio.setIdPacchettoPadre(v.getPacchetto().getIdPacchetto());
		viaggio.setIdPernottamento(v.getPernottamentoBean().getIdPernottamento());
		viaggio.setIdVoloAndata(v.getVolo1().getIdVolo());
		viaggio.setIdVoloRitorno(v.getVolo2().getIdVolo());
		viaggio.setPagato(v.getPagato());
		viaggio.setNumeroPersone(v.getNumeroPersone());
		viaggio.setTitolare(v.getUtente().getEmail());
		viaggio.setPrezzo(v.getPrezzo());
		return viaggio;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PacchettoDTO> pacchettiTutti() {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile=1");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@Override
	public PacchettoDTO getPacchettoDTO(int id) {
		Pacchetto p = new Pacchetto();
		p = em.find(Pacchetto.class, id);
		return convertiPacchettoDTO(p);
	}

	@Override
	public List<TipoCamere_HotelDTO> camerePerHotel(int idHotelScelto) {
		Hotel h = em.find(Hotel.class, idHotelScelto);
		List<TipoCamere_Hotel> l = h.getTipoCamereHotels();
		List<TipoCamere_HotelDTO> lista = new ArrayList<TipoCamere_HotelDTO>();
		for(int i = 0; i < l.size(); i++){
			lista.add(convertiTipoCamere_HotelDTO(l.get(i)));
		}
		return lista;
	}

	private TipoCamere_HotelDTO convertiTipoCamere_HotelDTO(TipoCamere_Hotel t) {
		TipoCamere_HotelPK pk = t.getId();
		int idHotel = pk.getIdHotel();
		String tipoCamera = pk.getTipoCamera();
		BigDecimal prezzo = t.getPrezzo();
		TipoCamere_HotelDTO t2 = new TipoCamere_HotelDTO();
		t2.setId(idHotel);
		t2.setPrezzo(prezzo);
		t2.setTipo(TipoCamera.valueOf(tipoCamera.toUpperCase()));
		return t2;
	}

	@Override
	public VoloDTO getVoloDTO(int id) {
		Volo v = new Volo();
		v = em.find(Volo.class, id);
		return convertiVoloDTO(v);
	}
	
	@Override
	public AeroportoDTO getAeroportoDTO(int id) {
		Aeroporto a = em.find(Aeroporto.class, id);
		return convertiAeroportoDTO(a);
	}

	private AeroportoDTO convertiAeroportoDTO(Aeroporto a) {
		AeroportoDTO aDTO = new AeroportoDTO();
		aDTO.setCitta(a.getCitta());
		aDTO.setId(a.getIdAeroporto());
		aDTO.setNome(a.getNome());
		return aDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getListaUtenti() {
		Query q = em.createNativeQuery("SELECT email FROM Utente");
		return q.getResultList();
	}

	@Override
	public List<PacchettoDTO> ultimiPacchetti() {
		int numeroPacchetti = 4;
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1 ORDER BY idPacchetto DESC");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<PacchettoDTO> l1 = new ArrayList<PacchettoDTO>();
		if(numeroPacchetti > i.size())
			numeroPacchetti = i.size();
		for(int index = 0; index < numeroPacchetti; index++){
			Pacchetto p = em.find(Pacchetto.class, i.get(index));
			l1.add(convertiPacchettoDTO(p));
		}
		return l1;
	}

	@Override
	public List<String> getCittaConHotel() {
		Query q = em.createNativeQuery("SELECT idHotel FROM Hotel WHERE selezionabile = 1");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<String> lista = new ArrayList<String>();
		for(int index = 0; index < i.size(); index++){
			Hotel h = em.find(Hotel.class, i.get(index));
			if(!lista.contains(h.getCitta()))
				lista.add(h.getCitta());
		}
		return lista;
	}

	@Override
	public List<String> getCittaConPacchetto() {
		Query q = em.createNativeQuery("SELECT idPacchetto FROM Pacchetto WHERE selezionabile = 1");
		List<Integer> i = new ArrayList<Integer>();
		i = q.getResultList();
		List<String> lista = new ArrayList<String>();
		for(int index = 0; index < i.size(); index++){
			Pacchetto h = em.find(Pacchetto.class, i.get(index));
			if(!lista.contains(h.getCitta()))
				lista.add(h.getCitta());
		}
		return lista;
	}
}
