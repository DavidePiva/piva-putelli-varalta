package bean;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import DTO.UtenteDTO;

import java.util.List;

import model.Gruppo;
import model.Utente;
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

	@Override
	public void salva(UtenteDTO utente) {
		Utente nuovoUtente = new Utente(utente);

		List<Gruppo> gruppi = new ArrayList<Gruppo>();
		Gruppo g = new Gruppo();
		g.setIdGruppo("USER");
		gruppi.add(g);
		nuovoUtente.setGruppos(gruppi);

		em.persist(nuovoUtente);
	}

	@Override
	public void aggiorna(UtenteDTO utente) {
		// TODO Auto-generated method stub

	}

	@RolesAllowed({})
	@Override
	public void eliminaUtente() {
		remove(getUtenteAttuale());

	}

	@Override
	public UtenteDTO getUtenteDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	public Utente find(String email) {
		return em.find(Utente.class, email);
	}

	public Utente getUtenteAttuale() {
		return find(getUtenteAttualeEmail());
	}

	public String getUtenteAttualeEmail() {
		return context.getCallerPrincipal().getName();
	}

	public void remove(Utente u) {
		em.remove(u);
	}
}
