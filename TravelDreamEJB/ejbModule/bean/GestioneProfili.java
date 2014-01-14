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
import model.Hotel;
import model.Utente;

/**
 * Session Bean implementation class GestioneProfili
 */
@Stateless
public class GestioneProfili implements GestioneProfiliLocal {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;

	/*
	 * public GestioneProfili() { // TODO Auto-generated constructor stub }
	 */

	@Override
	public void salva(UtenteDTO utente) {
		Utente nuovoUtente = new Utente(utente);
		nuovoUtente.setAttivo(utente.getAttivo());

		List<Gruppo> gruppi = new ArrayList<Gruppo>();
		Gruppo g = new Gruppo();
		g.setIdGruppo("USER");
		gruppi.add(g);
		nuovoUtente.setGruppos(gruppi);
		// nuovoUtente.setAttivo(true);

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
	@RolesAllowed({ "USER", "ADMIN", "IMPIEGATO" })
	public UtenteDTO getUtenteDTO() {
		UtenteDTO utenteDTO = convertiInDTO(getUtenteAttuale());
		return utenteDTO;
	}

	@RolesAllowed({ "ADMIN" })
	private boolean metodoAdmin() {
		System.out.println("ROmbadflg ");
		return true;
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

	private UtenteDTO convertiInDTO(Utente u) {
		UtenteDTO uDTO = new UtenteDTO();
		uDTO.setEmail(u.getEmail());
		uDTO.setNome(u.getNome());
		uDTO.setCognome(u.getCognome());
		uDTO.setAttivo(u.getAttivo()); // Conversione alla bruttodio, e infatti
										// ci sono problemi...
		/*
		 * if(u.getAttivo()==1){ uDTO.setAttivo(true); }else{
		 * uDTO.setAttivo(false); }
		 */

		return uDTO;
	}

	public void remove(Utente u) {
		em.remove(u);
	}

	@Override
	public boolean loggato() {
		if (getUtenteAttualeEmail() != "ANONYMOUS")
			return true;
		return false;

	}

	@Override
	public boolean isAdmin() {
		return context.isCallerInRole("ADMIN");
	}

	
}
