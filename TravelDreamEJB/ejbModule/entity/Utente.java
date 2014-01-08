package entity;


public class Utente {

	String email;
	String nome;
	String cognome;
	String criptoPassword;
	Boolean attivo;

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
	
}
