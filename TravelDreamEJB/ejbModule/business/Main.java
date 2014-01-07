package business;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.UtenteDTO;
import bean.GestioneProfili;
import entity.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		InterfacciaDB interfaccia=new InterfacciaDB();
		/*try {
			ArrayList<Aeroporto> viaggi;
			try {
				viaggi = interfaccia.aeroporti();
				for(int i = 0; i < viaggi.size(); i++)
				{
					System.out.println(viaggi.get(i).getNome());
				}
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		GestioneProfili gp = new GestioneProfili();
		UtenteDTO u = new UtenteDTO();
		u.setEmail("gfhfh@gmail.com");
		u.setCriptoPassword("passwordcriptica");
		u.setCognome("Rodari");
		u.setNome("Gianni");
		u.setAttivo(true);
		
		gp.salva(u);
	}

}
