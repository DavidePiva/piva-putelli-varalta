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

import model.Attivita;
import model.Pacchetto;
import model.Pernottamento;
import model.Volo;
import DTO.AttivitaDTO;
import DTO.PacchettoDTO;

/**
 * Session Bean implementation class GestioneOfferte
 */
@Stateful
@LocalBean
public class GestioneOfferte implements GestioneOfferteLocal {
	
	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	
	private int paginaSelezionata;
	private Pacchetto pacchetto;
	private String descrizione;

	public int getPaginaSelezionata() {
		return paginaSelezionata;
	}

	public void setPaginaSelezionata(int paginaSelezionata) {
		this.paginaSelezionata = paginaSelezionata;
	}

    /*public GestioneOfferte() {
       this.pacchetto=new Pacchetto();
       paginaSelezionata=1;
    }*/

	@Override
	public String getCittaSelezionata() {
		return pacchetto.getCitta();
	}

	public Pacchetto getPacchetto() {
		return pacchetto;
	}

	public void setPacchetto(Pacchetto pacchetto) {
		this.pacchetto = pacchetto;
	}

	@Override
	public void salvaTutto(PacchettoDTO p, AttivitaDTO a1, AttivitaDTO a2,
			AttivitaDTO a3, AttivitaDTO a4, AttivitaDTO a5) {

		pacchetto = new Pacchetto(p);	
		
		Pernottamento pernott = em.find(Pernottamento.class, p.getIdPernottamento());
		pacchetto.setPernottamentoBean(pernott);
		Volo v1 = em.find(Volo.class, p.getVoloAndata());
		pacchetto.setVolo1(v1);
		Volo v2 = em.find(Volo.class, p.getVoloRitorno());
		pacchetto.setVolo2(v2);
		
		em.persist(pacchetto);
		em.flush();
		em.clear();
		
		
		List<Attivita> attivitas = new ArrayList<Attivita>();
		if(a1.getId()!=0)
			attivitas.add(em.find(Attivita.class, a1.getId()));
		if(a2.getId()!=0)
			attivitas.add(em.find(Attivita.class, a2.getId()));
		if(a3.getId()!=0)
			attivitas.add(em.find(Attivita.class, a3.getId()));
		if(a4.getId()!=0)
			attivitas.add(em.find(Attivita.class, a4.getId()));
		if(a5.getId()!=0)
			attivitas.add(em.find(Attivita.class, a5.getId()));
		if(attivitas.size()>=1){
					pacchetto.setAttivitas(attivitas);

		}
		
		em.merge(pacchetto);
		

		

	}

	@Override
	public void setDescrizione(String descr) {
		descrizione = descr;
	}

	@Override
	public void eliminaPacchetto(int idPacchetto) {
		Pacchetto p=em.find(Pacchetto.class, idPacchetto);
		p.setSelezionabile(false);
		em.merge(p);
	}

	@Override
	public List<AttivitaDTO> attivitaAggiungibili(int idPacchetto) { //!!!!!
		Pacchetto p=em.find(Pacchetto.class, idPacchetto);
		
		Query q = em.createNativeQuery("SELECT idAttivita FROM Attivita WHERE selezionabile = 1 AND citta = '"+p.getCitta()+"' AND data BETWEEN '"+(p.getVolo1().getData().getYear()+1900)+"-"+(p.getVolo1().getData().getMonth()+1)+"-"+(p.getVolo1().getData().getDate())+"' AND '"+(p.getVolo2().getData().getYear()+1900)+"-"+(p.getVolo2().getData().getMonth()+1)+"-"+(p.getVolo2().getData().getDate())+"'");
		List<Integer> list=new ArrayList<Integer>();
		list=q.getResultList();
		List<AttivitaDTO> listDTO=new ArrayList<AttivitaDTO>();
		for(int i=0;i<list.size();i++){
			Attivita a = em.find(Attivita.class, list.get(i));
			if(!p.getAttivitas().contains(a))
				listDTO.add(convertiAttivitaDTO(a));
		}	
		return listDTO;
	}

	@Override
	public List<AttivitaDTO> attivitaEliminabili(int idPacchetto) {
		Pacchetto p=em.find(Pacchetto.class, idPacchetto);
		List<AttivitaDTO> listDTO=new ArrayList<AttivitaDTO>();
		for(int i=0;i<p.getAttivitas().size();i++){
			listDTO.add(convertiAttivitaDTO(p.getAttivitas().get(i)));
		}	
		return listDTO;
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
	public void eliminaAttivitaDaPacchetto(int idPacchetto, int idAttivita) {
		Pacchetto p=em.find(Pacchetto.class, idPacchetto);
		Attivita a=em.find(Attivita.class, idAttivita);
		System.out.println("ATTIVITÀ ELIMINATA "+a.getTitolo());
		List<Attivita> lAtt=p.getAttivitas();
		lAtt.remove(a);
		p.setAttivitas(lAtt);

		em.merge(p);
		
	}

	@Override
	public void aggiungiAttivitaAlPacchetto(int idPacchetto, int idAttivita) {
		Pacchetto p=em.find(Pacchetto.class, idPacchetto);
		Attivita a=em.find(Attivita.class, idAttivita);

		List<Attivita> lAtt=p.getAttivitas();
		lAtt.add(a);
		p.setAttivitas(lAtt);

		em.merge(p);

	}

	@Override
	public void modificaSalva(PacchettoDTO pDTO) {
		Pacchetto p=em.find(Pacchetto.class, pDTO.getIdPacchetto());
		p.setDescrizione(pDTO.getDescrizione());
		p.setPrezzo(pDTO.getPrezzo());
		p.setSelezionabile(pDTO.getSelezionabile());
		p.setTarget(pDTO.getTarget());
		p.setTipologia(pDTO.getTipologia());
		p.setTitolo(pDTO.getTitolo());
		p.setFoto1(pDTO.getFoto1());
		p.setFoto2(pDTO.getFoto2());
		p.setFoto3(pDTO.getFoto3());
		p.setFoto4(pDTO.getFoto4());
		p.setFoto5(pDTO.getFoto5());
		p.setFoto6(pDTO.getFoto6());
		
		Volo v1 = em.find(Volo.class, pDTO.getVoloAndata());
		p.setVolo1(v1);
		
		Volo v2 = em.find(Volo.class, pDTO.getVoloRitorno());
		p.setVolo2(v2);
		
		Pernottamento pernott = em.find(Pernottamento.class, pDTO.getIdPernottamento());
		p.setPernottamentoBean(pernott);
		
		
		System.out.println("Pacchetto "+p.getTitolo()+" aggiornato!");
		em.merge(p);

		
	}


}
