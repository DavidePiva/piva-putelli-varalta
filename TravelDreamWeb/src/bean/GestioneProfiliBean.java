package bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import DTO.UtenteDTO;

@ManagedBean(name="gp")
@RequestScoped
public class GestioneProfiliBean {

	@EJB
	private GestioneProfiliLocal gestioneprofili;
	
	public void salva(){
		UtenteDTO u = new UtenteDTO();
		u.setEmail("ciccionissimome@gmail.com");
		u.setCognome("Pippo");
		u.setNome("Gianni");
		u.setAttivo(true);
		u.setCriptoPassword("password");
		
		gestioneprofili.salva(u);
	}

}
