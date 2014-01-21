package bean;

import javax.ejb.Local;

@Local
public interface GestioneViaggiLocal {

	public void creaViaggio(String nomePacchetto, String emailUtente);

}
