package bean;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class Autenticazione
 */
@Stateless
public class Autenticazione implements AutenticazioneLocal {

    /**
     * Default constructor. 
     */
    public Autenticazione() {
    
    }
    
    
	@Override
	@RolesAllowed({"ADMIN"})
	public String getAdminContent() {

		return "hello admin";
	}
	
	@Override
	@RolesAllowed({"USER", "ADMIN", "IMPIEGATO"})
	public String getUserContent() {

		return "hello zio";
	}


	@Override
	@RolesAllowed({"IMPIEGATO"})
	public String getImpiegatoContent() {
		
		return "hello stupido lavoratore";
	}

}
