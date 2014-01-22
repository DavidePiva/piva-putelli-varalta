package bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Attivita;
import DTO.AttivitaDTO;

/**
 * Session Bean implementation class ShowAttivita
 */
@Stateless
@LocalBean
public class ShowAttivita implements ShowAttivitaLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private EJBContext context;
	
	
    public ShowAttivita() {
    }

//////////////////////DAAAAAAAAAAAAAAAAA SISTEMAREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
/*	@Override
	public AttivitaDTO getAttivita(String nomeAttivita) {
		Query q = em.createNativeQuery("SELECT idAttivita FROM Attivita WHERE titolo = \""+nomeAttivita+"\"");
		List<Integer> l = q.getResultList();
	
			Attivita a = em.find(Attivita.class, l.get(0));
			return convertiAttivitaDTO(a);
	}*/
		
	@Override
	
	public List<String> getListaTitoliAttivita(){
		Query q = em.createNativeQuery("SELECT distinct titolo FROM Attivita");
		List<String> list=q.getResultList();
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

}
