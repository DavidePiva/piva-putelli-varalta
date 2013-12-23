package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.*;

public class InterfacciaDB {
	
	Connection conn;
	Statement stmt;
	boolean isConnesso;
	
	public InterfacciaDB(){
		isConnesso=false;
	}
	
	public boolean isConnesso(){
		return isConnesso;
	}
	
	public void connetti() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/traveldreamdb";
		String connectionUser = "root";
		System.out.println("Inserisci password");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String connectionPassword = "";
		try {
			connectionPassword = br.readLine();
		} catch (IOException e) {
			return;
		}
		conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		stmt = conn.createStatement();
		isConnesso=true;
	}
	
	public void disconnetti() throws SQLException{
		stmt.close();
		conn.close();
	}
	
	public ArrayList<Viaggio> viaggiPerDestinazione(String citta) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(!isConnesso){
			connetti();
		}
		ArrayList<Viaggio> viaggi = new ArrayList<Viaggio>();
		String query = "SELECT * FROM Viaggio WHERE citta="+citta;
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			int id = rs.getInt("id");
			Viaggio viaggio = new Viaggio(id);
			viaggi.add(viaggio);
		}
		return viaggi;
	}
	
	public ArrayList<Aeroporto> aeroporti() throws SQLException{
		ArrayList<Aeroporto> a = new ArrayList<Aeroporto>();
		String query = "SELECT * FROM Aeroporto";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			int id = rs.getInt("idAeroporto");
			String nome = rs.getString("nome");
			String citta = rs.getString("citta");
			Aeroporto ar = new Aeroporto(id, nome, citta);
			a.add(ar);
		}
		return a;
	}
	
	
	
}
