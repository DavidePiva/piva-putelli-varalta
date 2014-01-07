package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import DTO.UtenteDTO;
import enums.TipoUtente;

@Table(name="Utente")

public class Utente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	String email;
	String nome;
	String cognome;
	String criptoPassword;
	Boolean attivo;
	
	@ElementCollection(targetClass = TipoUtente.class)
    @CollectionTable(name = "Utente_Gruppo",
                    joinColumns = @JoinColumn(name = "email"))
    @Enumerated(EnumType.STRING)
    @Column(name="gruppo")
    private List<TipoUtente> gruppi;

	public Utente() {
		super();
		this.email = null;
		this.nome = null;
		this.cognome = null;
		this.criptoPassword = null;
		this.attivo = false;
	}
	
	public Utente(String email, String nome, String cognome, String criptoPassword, Boolean attivo) {
		super();
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.criptoPassword = criptoPassword;
		this.attivo = attivo;
	}
	
	public Utente(UtenteDTO user){
		super();
		this.email=user.getEmail();
		this.nome=user.getNome();
		this.cognome=user.getCognome();
		this.criptoPassword=user.getCriptoPassword();
		this.attivo=user.getAttivo();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCriptoPassword() {
		return criptoPassword;
	}

	public void setCriptoPassword(String criptoPassword) {
		this.criptoPassword = criptoPassword;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}

	public List<TipoUtente> getGruppi() {
		return gruppi;
	}

	public void setGruppi(List<TipoUtente> gruppi) {
		this.gruppi = gruppi;
	}
	
}
