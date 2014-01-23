package bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		System.out.println("SALVATUTTO CON DeScRiZiOnE "+descrizione);
		pacchetto = new Pacchetto(p);
		//pacchetto.setIdPacchetto(100);
		System.out.println("ENTRATO NEL SALVATUTTO, P: "+pacchetto.getTitolo()+pacchetto.getIdPacchetto());
		
		
		Pernottamento pernott = em.find(Pernottamento.class, p.getIdPernottamento());
		pacchetto.setPernottamentoBean(pernott);
		Volo v1 = em.find(Volo.class, p.getVoloAndata());
		pacchetto.setVolo1(v1);
		Volo v2 = em.find(Volo.class, p.getVoloRitorno());
		pacchetto.setVolo2(v2);
		
		em.persist(pacchetto);
		em.flush();
		
		System.out.println("ID PACCK: "+pacchetto.getIdPacchetto());
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
		pacchetto.setAttivitas(attivitas);
		System.out.println("ATTIVITA AGGGGGGG: "+pacchetto.getAttivitas().get(0).getTitolo());
		
		em.merge(pacchetto);
		

		

	}

	@Override
	public void setDescrizione(String descr) {
		descrizione = descr;
		System.out.println("JAVAAAAAAAAAAAAAAAAAAAA, descrizione settata: "+descrizione);
	}


}
