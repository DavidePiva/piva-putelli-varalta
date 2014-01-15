package bean;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import DTO.PacchettoDTO;
import DTO.UtenteDTO;

import java.util.List;

import model.Gruppo;
import model.Hotel;
import model.Pacchetto;
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

	@Override
	public void eliminaUtente() {
		remove(getUtenteAttuale());
	}
	
	public void remove(Utente u) {
		em.remove(u);
	}

	@Override
	@RolesAllowed({ "USER", "ADMIN", "IMPIEGATO" })
	public UtenteDTO getUtenteDTO() {
		UtenteDTO utenteDTO = convertiInDTO(getUtenteAttuale());
		return utenteDTO;
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
		uDTO.setAttivo(u.getAttivo());
		return uDTO;
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

	@Override
	public boolean isImpiegato() {
		return context.isCallerInRole("IMPIEGATO");
	}

	@Override
	public List<UtenteDTO> listaUtenti() {
		Query q = em.createNativeQuery("SELECT DISTINCT Utente.email FROM Utente, Utente_Gruppo WHERE Utente_Gruppo.idGruppo = 'USER' AND Utente.email=Utente_Gruppo.email");
		List s = new ArrayList<String>();
		Gruppo g = new Gruppo();
		g.setIdGruppo("ADMIN");
		s = q.getResultList();
		List<UtenteDTO> uList = new ArrayList<UtenteDTO>();
		for(int i=0; i<s.size(); i++){
			Utente u = em.find(Utente.class, s.get(i));
			if(!u.getGruppos().contains(g))
			{
				uList.add(convertiInDTO(u));
			}
		}
		
		
		
		return uList;
	}

	@Override
	public void rendiImpiegato(String email) {
		Utente u = find(email);
		Gruppo g = new Gruppo();
		g.setIdGruppo("IMPIEGATO");
		List<Gruppo> gruppi = new ArrayList<Gruppo>();
		gruppi.add(g);
		u.setGruppos(gruppi);
		em.merge(u);
		
	}

	@Override
	public List<UtenteDTO> listaImpiegati() {
		Query q = em.createNativeQuery("SELECT DISTINCT Utente.email FROM Utente, Utente_Gruppo WHERE Utente_Gruppo.idGruppo = 'IMPIEGATO' AND Utente.email=Utente_Gruppo.email");
		List s = new ArrayList<String>();
		Gruppo g = new Gruppo();
		g.setIdGruppo("ADMIN");
		s = q.getResultList();
		List<UtenteDTO> uList = new ArrayList<UtenteDTO>();
		for(int i=0; i<s.size(); i++){
			Utente u = em.find(Utente.class, s.get(i));
			if(!u.getGruppos().contains(g))
			{
				uList.add(convertiInDTO(u));
			}
		}
		
		
		
		return uList;
	}

	@Override
	public void rendiUtente(String email) {
		Utente u = find(email);
		Gruppo g = new Gruppo();
		g.setIdGruppo("USER");
		List<Gruppo> gruppi = new ArrayList<Gruppo>();
		gruppi.add(g);
		u.setGruppos(gruppi);
		em.merge(u);
	}

	
}
