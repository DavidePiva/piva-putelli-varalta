package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="autenticazione")
@RequestScoped
public class AutenticazioneBean {

	@EJB
	private AutenticazioneLocal aut;

	public AutenticazioneBean() {

	}

	public String getAdminContent() {
		return aut.getAdminContent();
	}

	public String getUserContent() {
		return aut.getUserContent();
	}

	public String getImpiegatoContent() {
		return aut.getImpiegatoContent();
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "/index?faces-redirect=true";
	}

}
