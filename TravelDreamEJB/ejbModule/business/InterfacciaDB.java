package business;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

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
	
	public static void rimuoviAttivita(int idViaggio, int idAttivita) throws SQLException{
		String delete = "DELETE FROM Viaggio_Attivita WHERE idViaggio = "+idViaggio+"AND idAttivita ="+idAttivita;
		stmt.executeUpdate(delete);
	}
	
	public static void aggiungiPartecipante(int idViaggio, String emailPartecipante) throws SQLException{
		String insert = "INSERT INTO Partecipazione VALUES("+idViaggio+","+emailPartecipante+",0)";
		stmt.executeUpdate(insert);
	}
	
	public static void rimuoviPartecipante(int idViaggio, String emailPartecipante) throws SQLException{
		String delete = "DELETE FROM Partecipazione WHERE idViaggio = "+idViaggio+"AND emailPartecipante ="+emailPartecipante;
		stmt.executeUpdate(delete);
	}
	
	/*
	 * metodi deprecati per gestire l'oggetto Time, bisogna trovare un'alternativa
	 */
	@SuppressWarnings("deprecation")
	public static ArrayList<Volo> voliPerData(Aeroporto partenza,Aeroporto arrivo,int anno, int mese, int giorno) throws SQLException{
		int s = partenza.getId();
		int e = arrivo.getId();
		String query = "SELECT * FROM Volo, Compagnia WHERE Volo.compagnia = Compagnia.idCompagnia AND aeroportoPartenza = "+s+" AND aeroportoArrivo = "+e+" AND data = "+anno+"-"+mese+"/"+giorno;
		ArrayList<Volo> voli = new ArrayList<Volo>();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){
			int id = rs.getInt("id");
			float prezzo = rs.getFloat("prezzo");
			int idCompagnia = rs.getInt("compagnia");
			Date tempoPartenza = rs.getDate("oraPartenza");
			int h = tempoPartenza.getHours();
			int m = tempoPartenza.getMinutes();
			int se = tempoPartenza.getSeconds();
			Date tempoArrivo = rs.getDate("oraArrivo");
			int h1 = tempoArrivo.getHours();
			int m1 = tempoArrivo.getMinutes();
			int s1 = tempoArrivo.getSeconds();
			String nomeCompagnia = rs.getString("nome");
			String descrizione = rs.getString ("descrizione");
			Compagnia c = new Compagnia(idCompagnia,nomeCompagnia,descrizione);
			Volo v = new Volo(id, partenza, arrivo, prezzo, c, anno, mese, giorno,h,m,se,h1,m1,s1);
			voli.add(v);
		}
		return voli;
	}
	
	public static void modificaNumeroPersone(Viaggio v, int numeroPersone) throws SQLException{
		int id = v.getId();
		String query = "UPDATE Viaggio SET numeroPersone = "+numeroPersone +" WHERE idViaggio = "+id;
		stmt.executeQuery(query);
	}
	
	public static void modificaPernottamento(int idViaggio, int idPernottamento) throws SQLException{
		String update = "UPDATE Viaggio_Pernottamento SET idPernottamento="+idPernottamento + " WHERE idViaggio = " + idViaggio;
		stmt.executeUpdate(update);
	}
	
	public static void viaggioPagato(int idViaggio) throws SQLException{
		String update = "UPDATE Viaggio SET pagato = 1 WHERE idViaggio = "+idViaggio;
		stmt.executeUpdate(update);
	}
	
	public static void rendiRegalabile(int idViaggio) throws SQLException{
		String update = "UPDATE Viaggio_Pernottamento set regalabile = 1 WHERE idViaggio = "+ idViaggio;
		stmt.executeUpdate(update);
		update = "UPDATE Viaggio_Attivita set regalabile = 1 WHERE idViaggio = "+ idViaggio;
		stmt.executeUpdate(update);
		update = "UPDATE Viaggio_Volo set regalabile = 1 WHERE idViaggio = "+ idViaggio;
		stmt.executeUpdate(update);
	}
	
	
}
