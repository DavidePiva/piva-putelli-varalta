package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.UtenteDTO;

@ManagedBean(name = "gp")
@RequestScoped
public class GestioneProfiliBean {

	@EJB
	private GestioneProfiliLocal gestioneprofili;

	public void salva() {

		for (int i = 0; i < 10; i++) {
			Integer p=i;
			UtenteDTO u = new UtenteDTO();
			u.setEmail(p.toString() + "@gmail.com");
			u.setCognome(p.toString());
			u.setNome(p.toString());
			u.setAttivo(true);
			u.setPassword("password");

			gestioneprofili.salva(u);
		}
	}

}
