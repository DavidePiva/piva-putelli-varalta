package bean;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import java.util.List;

import entity.Utente;
import enums.TipoUtente;


/**
 * Session Bean implementation class GestioneProfili
 */
@Stateless
public class GestioneProfili implements GestioneProfiliLocal {

	@PersistenceContext
    private EntityManager em;
	
	@Resource
	private EJBContext context;

	
    public GestioneProfili() {
        // TODO Auto-generated constructor stub
    }
/*
	@Override
	public void salva(UtenteDTO utente) {
		Utente nuovoUtente = new Utente(utente);
		List<TipoUtente> gruppi = new ArrayList<TipoUtente>();
		gruppi.add(TipoUtente.USER);
		nuovoUtente.setGruppi(gruppi);
		em.persist(nuovoUtente);
	}

	@Override
	public void aggiorna(UtenteDTO utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminaUtente() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UtenteDTO getUtenteDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salva(UtenteDTO utente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiorna(UtenteDTO utente) {
		// TODO Auto-generated method stub
		
	}
*/
}
