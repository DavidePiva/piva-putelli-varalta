package DTO;

public class UtenteDTO {
	String email;
	String nome;
	String cognome;
	String criptoPassword;
	boolean attivo;
	
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

	public boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
}
