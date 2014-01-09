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

		 {
	
			UtenteDTO u = new UtenteDTO();
			u.setEmail("ddd@gmail.com");
			u.setCognome("b");
			u.setNome("a");
			u.setAttivo(true);
			u.setPassword("password");

			gestioneprofili.salva(u);
		}
	}

}
