package business;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		InterfacciaDB interfaccia=new InterfacciaDB();
		try {
			interfaccia.connetti();
			ArrayList<Aeroporto> viaggi =	interfaccia.aeroporti();
			for(int i = 0; i < viaggi.size(); i++)
			{
				System.out.println(viaggi.get(i).getNome());
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
