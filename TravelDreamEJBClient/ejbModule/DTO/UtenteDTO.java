package DTO;

public class UtenteDTO {
        String email;
        String nome;
        String cognome;
        String password;
        Boolean attivo;
       
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

        public Boolean getAttivo() {
                return attivo;
        }

        public void setAttivo(Boolean attivo) {
                this.attivo = attivo;
        }
}
