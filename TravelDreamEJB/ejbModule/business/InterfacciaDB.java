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
import enums.*;
import entity.*;

public class InterfacciaDB {
	
	static Connection conn;
	static Statement stmt;
	static boolean isConnesso;
	
	public InterfacciaDB(){
		isConnesso=false;
	}
	
	public boolean isConnesso(){
		return isConnesso;
	}
	
	private static void connetti() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String connectionUrl = "jdbc:mysql://localhost:3306/TravelDreamDB";
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
	
	public static ArrayList<Viaggio> viaggiPerDestinazione(String citta) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
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
	
	public static ArrayList<Aeroporto> aeroporti() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(!isConnesso){
			connetti();
		}
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
	
	public static void inserisciViaggio(Viaggio viaggio) throws SQLException{
		int id = viaggio.getId();
		Pernottamento p = viaggio.getPernottamento();
		Volo andata = viaggio.getVoloAndata();
		Volo ritorno = viaggio.getVoloRitorno();
		String citta = viaggio.getCitta();
		float prezzo = viaggio.getPrezzo();
		Utente titolare = viaggio.getTitolare();
		boolean pagato = viaggio.isPagato();
		int numeroPersone = viaggio.getNumeroPersone();
		ArrayList<Attivita> att = viaggio.getAttivita();
		if(!presenzaPernottamento(p.getId())){
			inserisciPernottamento(p);
		}
		String insert = "INSERT INTO Viaggio VALUES("+id+","+p.getId()+","+andata.getId()+","+ritorno.getId()+","+citta+","+prezzo+","+titolare.getEmail()+","+pagato+","+numeroPersone+")";
		stmt.executeUpdate(insert);
		for(int i = 0; i < att.size(); i++){
			Attivita a = att.get(i);
			inserisciAttivita(id,a.getId());
		}
		insert = "INTO INTO Viaggio_Pernottamento("+id+","+p.getId()+","+"0)";
		stmt.executeUpdate(insert);
	}
	
	private static boolean presenzaPernottamento(int idPernottamento) throws SQLException{
		String query = "SELECT * FROM Pernottamento WHERE idPernottamento="+idPernottamento;
		ResultSet rs = stmt.executeQuery(query);
		if(rs!=null){
			return true;
		}
		return false;
	}
	
	private static void inserisciPernottamento(Pernottamento p) throws SQLException{
		int id = p.getId();
		Hotel h = p.getHotel();
		TipoCamera tc = p.getTipoCamera();
		String insert = "INSERT INTO Pernottamento VALUES("+id+","+h.getId()+","+tc.toString()+")";
		stmt.executeUpdate(insert);
	}
	
	public static void inserisciAttivita(int idViaggio, int idAttivita) throws SQLException{
		String insert = "INSERT INTO Viaggio_Attivita VALUES("+idViaggio+","+idAttivita+","+"0)";
		stmt.executeUpdate(insert);
	}
	
	
}
