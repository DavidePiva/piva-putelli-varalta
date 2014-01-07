package bean;
//ManageBean statico
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="ds")
@RequestScoped
public class DatiStaticiBean {
	
	@EJB
	private DatiStaticiLocal datistatici;
	
	public String esempio(){
		return datistatici.esempio();
	}
	
	public String aeroporti() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return datistatici.aeroporti().toString();
	}
}
