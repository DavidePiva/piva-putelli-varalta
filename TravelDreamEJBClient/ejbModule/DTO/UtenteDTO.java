package DTO;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

public class UtenteDTO {
	
		
		@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message="Email non valida")
		@NotEmpty(message="Email obbligatoria")
		String email;
		@NotEmpty(message="Nome obbligatorio")
		String nome;
		@NotEmpty(message="Cognome obbligatorio")
        String cognome;
		@NotEmpty(message="Inserisci una password")
        String password;
        byte attivo;
       
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getNome() {
                return this.nome;
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

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public byte getAttivo() {
                return attivo;
        }

        public void setAttivo(byte attivo) {
                this.attivo = attivo;
        }
}
