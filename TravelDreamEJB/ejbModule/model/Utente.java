package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Utente database table.
 * 
 */
@Entity
@NamedQuery(name="Utente.findAll", query="SELECT u FROM Utente u")
public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;

	private byte attivo;

	private String cognome;

	private String nome;

	private String password;

	//bi-directional many-to-one association to Donazione_Attivita
	@OneToMany(mappedBy="utente")
	private List<Donazione_Attivita> donazioneAttivitas;

	//bi-directional many-to-one association to Donazione_Pernottamento
	@OneToMany(mappedBy="utente")
	private List<Donazione_Pernottamento> donazionePernottamentos;

	//bi-directional many-to-one association to Donazione_Volo
	@OneToMany(mappedBy="utente")
	private List<Donazione_Volo> donazioneVolos;

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

	//bi-directional many-to-one association to Viaggio
	@OneToMany(mappedBy="utente")
	private List<Viaggio> viaggios;

	public Utente() {
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

	public List<Donazione_Attivita> getDonazioneAttivitas() {
		return this.donazioneAttivitas;
	}

	public void setDonazioneAttivitas(List<Donazione_Attivita> donazioneAttivitas) {
		this.donazioneAttivitas = donazioneAttivitas;
	}

	public Donazione_Attivita addDonazioneAttivita(Donazione_Attivita donazioneAttivita) {
		getDonazioneAttivitas().add(donazioneAttivita);
		donazioneAttivita.setUtente(this);

		return donazioneAttivita;
	}

	public Donazione_Attivita removeDonazioneAttivita(Donazione_Attivita donazioneAttivita) {
		getDonazioneAttivitas().remove(donazioneAttivita);
		donazioneAttivita.setUtente(null);

		return donazioneAttivita;
	}

	public List<Donazione_Pernottamento> getDonazionePernottamentos() {
		return this.donazionePernottamentos;
	}

	public void setDonazionePernottamentos(List<Donazione_Pernottamento> donazionePernottamentos) {
		this.donazionePernottamentos = donazionePernottamentos;
	}

	public Donazione_Pernottamento addDonazionePernottamento(Donazione_Pernottamento donazionePernottamento) {
		getDonazionePernottamentos().add(donazionePernottamento);
		donazionePernottamento.setUtente(this);

		return donazionePernottamento;
	}

	public Donazione_Pernottamento removeDonazionePernottamento(Donazione_Pernottamento donazionePernottamento) {
		getDonazionePernottamentos().remove(donazionePernottamento);
		donazionePernottamento.setUtente(null);

		return donazionePernottamento;
	}

	public List<Donazione_Volo> getDonazioneVolos() {
		return this.donazioneVolos;
	}

	public void setDonazioneVolos(List<Donazione_Volo> donazioneVolos) {
		this.donazioneVolos = donazioneVolos;
	}

	public Donazione_Volo addDonazioneVolo(Donazione_Volo donazioneVolo) {
		getDonazioneVolos().add(donazioneVolo);
		donazioneVolo.setUtente(this);

		return donazioneVolo;
	}

	public Donazione_Volo removeDonazioneVolo(Donazione_Volo donazioneVolo) {
		getDonazioneVolos().remove(donazioneVolo);
		donazioneVolo.setUtente(null);

		return donazioneVolo;
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