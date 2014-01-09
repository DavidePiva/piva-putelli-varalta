package bean;

import javax.ejb.Local;

@Local
public interface AutenticazioneLocal {
	
	public String getAdminContent();

	public String getUserContent();
	
	public String getImpiegatoContent();
	
}
