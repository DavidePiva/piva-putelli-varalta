package model;

import java.io.Serializable;

import javax.persistence.*;

import DTO.UtenteDTO;

import java.util.List;


/**
 * The persistent class for the Utente database table.
 * 
 */
@Entity
@Table(name="Utente")
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private byte attivo;

	private String cognome;

	private String nome;

	private String password;

	//bi-directional many-to-one association to Partecipazione
	@OneToMany(mappedBy="utente")
	private List<Partecipazione> partecipaziones;

	//bi-directional many-to-many association to Attivita
	@ManyToMany
	@JoinTable(
		name="Donazione_Attivita"
		, joinColumns={
			@JoinColumn(name="emailDonatore")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAttivita")
			}
		)
	private List<Attivita> attivitas;

	//bi-directional many-to-many association to Gruppo
	@ManyToMany
	@JoinTable(
		name="Utente_Gruppo"
		, joinColumns={
			@JoinColumn(name="email")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idGruppo")
			}
		)
	private List<Gruppo> gruppos;

	//bi-directional many-to-many association to Pernottamento
	@ManyToMany
	@JoinTable(
		name="Donazione_Pernottamento"
		, joinColumns={
			@JoinColumn(name="emailDonatore")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idPernottamento")
			}
		)
	private List<Pernottamento> pernottamentos;

	//bi-directional many-to-many association to Volo
	@ManyToMany
	@JoinTable(
		name="Donazione_Volo"
		, joinColumns={
			@JoinColumn(name="emailDonatore")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idVolo")
			}
		)
	private List<Volo> volos;

	//bi-directional many-to-one association to Utente_Gruppo
	@OneToMany(mappedBy="utente")
	private List<Utente_Gruppo> utenteGruppos;

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="utente")
	private List<Viaggio> viaggios;

	public Utente() {
	}
	
	public Utente(UtenteDTO user){
		this.email=user.getEmail();
		this.nome=user.getNome();
		this.cognome=user.getCognome();
	//	this.password=DigestUtils.sha256Hex(user.getCriptoPassword());
		this.attivo=1;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getAttivo() {
		return this.attivo;
	}

	public void setAttivo(byte attivo) {
		this.attivo = attivo;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Partecipazione> getPartecipaziones() {
		return this.partecipaziones;
	}

	public void setPartecipaziones(List<Partecipazione> partecipaziones) {
		this.partecipaziones = partecipaziones;
	}

	public Partecipazione addPartecipazione(Partecipazione partecipazione) {
		getPartecipaziones().add(partecipazione);
		partecipazione.setUtente(this);

		return partecipazione;
	}

	public Partecipazione removePartecipazione(Partecipazione partecipazione) {
		getPartecipaziones().remove(partecipazione);
		partecipazione.setUtente(null);

		return partecipazione;
	}

	public List<Attivita> getAttivitas() {
		return this.attivitas;
	}

	public void setAttivitas(List<Attivita> attivitas) {
		this.attivitas = attivitas;
	}

	public List<Gruppo> getGruppos() {
		return this.gruppos;
	}

	public void setGruppos(List<Gruppo> gruppos) {
		this.gruppos = gruppos;
	}

	public List<Pernottamento> getPernottamentos() {
		return this.pernottamentos;
	}

	public void setPernottamentos(List<Pernottamento> pernottamentos) {
		this.pernottamentos = pernottamentos;
	}

	public List<Volo> getVolos() {
		return this.volos;
	}

	public void setVolos(List<Volo> volos) {
		this.volos = volos;
	}

	public List<Utente_Gruppo> getUtenteGruppos() {
		return this.utenteGruppos;
	}

	public void setUtenteGruppos(List<Utente_Gruppo> utenteGruppos) {
		this.utenteGruppos = utenteGruppos;
	}

	public Utente_Gruppo addUtenteGruppo(Utente_Gruppo utenteGruppo) {
		getUtenteGruppos().add(utenteGruppo);
		utenteGruppo.setUtente(this);

		return utenteGruppo;
	}

	public Utente_Gruppo removeUtenteGruppo(Utente_Gruppo utenteGruppo) {
		getUtenteGruppos().remove(utenteGruppo);
		utenteGruppo.setUtente(null);

		return utenteGruppo;
	}

	public List<Viaggio> getViaggios() {
		return this.viaggios;
	}

	public void setViaggios(List<Viaggio> viaggios) {
		this.viaggios = viaggios;
	}

	public Viaggio addViaggio(Viaggio viaggio) {
		getViaggios().add(viaggio);
		viaggio.setUtente(this);

		return viaggio;
	}

	public Viaggio removeViaggio(Viaggio viaggio) {
		getViaggios().remove(viaggio);
		viaggio.setUtente(null);

		return viaggio;
	}

}