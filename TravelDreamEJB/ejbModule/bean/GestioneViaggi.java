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
		v2.setVolo(andata);
		v.addViaggioVolo(v2);
		em.merge(v);
	}

}
