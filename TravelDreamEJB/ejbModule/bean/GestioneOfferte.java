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
		System.out.println("ENTRATO NEL SALVATUTTO, P: "+pacchetto.getTitolo()+pacchetto.getIdPacchetto());
		List<Attivita> attivitas = new ArrayList<Attivita>();
		if(a1.getId()!=0)
			attivitas.add(new Attivita(a1));
		if(a2.getId()!=0)
			attivitas.add(new Attivita(a2));
		if(a3.getId()!=0)
			attivitas.add(new Attivita(a3));
		if(a4.getId()!=0)
			attivitas.add(new Attivita(a4));
		if(a5.getId()!=0)
			attivitas.add(new Attivita(a5));
		pacchetto.setAttivitas(attivitas);
		em.persist(pacchetto);
		

	}


}
