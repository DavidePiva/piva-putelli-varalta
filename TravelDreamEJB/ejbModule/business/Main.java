package business;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Viaggio;

public class Main {

	public static void main(String[] args) throws SQLException {
		InterfacciaDB interfacciaDB=new InterfacciaDB();
		ArrayList<Viaggio> viaggi =	interfacciaDB.viaggiPerDestinazione("New York");
		System.out.println(viaggi.get(0).getCitta());
		
	}

}
