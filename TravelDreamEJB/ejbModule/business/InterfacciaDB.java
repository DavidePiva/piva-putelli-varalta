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
import java.util.GregorianCalendar;

import javax.persistence.TemporalType;

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
		insert = "INSERT INTO Viaggio_Pernottamento("+id+","+p.getId()+","+"0)";
		stmt.executeUpdate(insert);
		insert = "INSERT INTO Viaggio_Volo("+id+","+andata.getId()+",0)";
		stmt.executeUpdate(insert);
		insert =  "INSERT INTO Viaggio_Volo("+id+","+ritorno.getId()+",0)";
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
		String query = "SELECT * FROM Volo, Compagnia WHERE Volo.compagnia = Compagnia.idCompagnia AND aeroportoPartenza = "+s+" AND aeroportoArrivo = "+e+" AND data = "+anno+"-"+mese+"-"+giorno;
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
	
	public static float getPrezzoCamera(int idHotel, TipoCamera tc) throws SQLException{
		String query = "SELECT prezzo FROM TipoCamere_Hotel WHERE idHotel = "+idHotel+" tipoCamera = "+tc;
		ResultSet rs = stmt.executeQuery(query);
		float prezzo = rs.getFloat("prezzo");
		return prezzo;
	}
	
	public static float getPrezzoPacchetto(int idPacchetto) throws SQLException{
		String query = "SELECT prezzo FROM Pacchetto WHERE idPacchetto = "+idPacchetto;
		ResultSet rs = stmt.executeQuery(query);
		float prezzo = rs.getFloat("prezzo");
		return prezzo;
	}
	
	public static void modificaVolo(int idViaggio, int idVolo, boolean andata) throws SQLException{
		String update;
		if(andata){
			update = "UPDATE Viaggio SET voloAndata = " +idVolo+" WHERE idViaggio = "+idViaggio;
		}
		else{
			update = "UPDATE Viaggio SET voloRitorno = " +idVolo+" WHERE idViaggio = "+idViaggio;
		}
		stmt.executeUpdate(update);
	}
	
	public static void inserisciPacchetto(Pacchetto p) throws SQLException{
		int id = p.getId();
		int idPernottamento = p.getPernottamento().getId();
		int andata = p.getVoloAndata().getId();
		int ritorno = p.getVoloRitorno().getId();
		String citta = p.getCitta();
		String descrizione = p.getDescrizione();
		float prezzo = p.getPrezzo();
		TipologiaPacchetto tp = p.getTipologia();
		TargetPacchetto t = p.getTarget();
		if(!presenzaPernottamento(idPernottamento)){
			inserisciPernottamento(p.getPernottamento());
		}
		ArrayList<Attivita> att = p.getAttivita();
		String insert = "INSERT INTO Pacchetto VALUES("+id+","+idPernottamento+","+andata+","+ritorno+","+citta+","+descrizione+","+prezzo+","+tp.name()+","+t.name()+")";
		stmt.executeUpdate(insert);
		for(int i = 0; i < att.size(); i++){
			Attivita a = att.get(i);
			inserisciAttivitaInPacchetto(id,a.getId());
		}
	}
	
	public static void modificaVoloInPacchetto(int idPacchetto, int idVolo, boolean andata) throws SQLException{
		String update;
		if(andata){
			update = "UPDATE Pacchetto set voloAndata = "+idVolo+" WHERE idPacchetto = "+idPacchetto;
		}
		else{
			update = "UPDATE Pacchetto set voloRitorno = "+idVolo+" WHERE idPacchetto = "+idPacchetto;
		}
		stmt.executeUpdate(update);
	}
	
	public static void modificaPernottamentoInPacchetto(int idPacchetto, int idPernottamento) throws SQLException{
		String update = "UPDATE Pacchetto set pernottamento = "+idPernottamento+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void modificaTargetPacchetto(int idPacchetto, TargetPacchetto tp) throws SQLException{
		String update = "UPDATE Pacchetto set target = "+tp.name()+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void modificaTipologiaPacchetto(int idPacchetto, TipologiaPacchetto tp) throws SQLException{
		String update = "UPDATE Pacchetto set tipologia = "+tp.name()+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void inserisciAttivitaInPacchetto(int idPacchetto, int idAttivita) throws SQLException{
		String insert = "INSERT INTO Pacchetto_Attivita VALUES("+idPacchetto+","+idAttivita+")";
		stmt.executeUpdate(insert);
	}
	
	public static void rimuoviAttivitaDaPacchetto(int idPacchetto, int idAttivita) throws SQLException{
		String delete = "DELETE FROM Pacchetto_Attivita WHERE idPacchetto = "+idPacchetto+"AND idAttivita ="+idAttivita;
		stmt.executeUpdate(delete);
	}
	
	public static void modificaPrezzoPacchetto(int idPacchetto, float prezzo) throws SQLException{
		String update = "UPDATE Pacchetto SET prezzo = "+prezzo+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void modificaTitoloPacchetto(int idPacchetto, String titolo) throws SQLException{
		String update = "UPDATE Pacchetto SET titolo = "+titolo+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void modificaDescrizionePacchetto(int idPacchetto, String descrizione) throws SQLException{
		String update = "UPDATE Pacchetto SET descrizione = "+descrizione+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void modificaFotoPacchetto(int idPacchetto, String urlFoto, int numeroFoto) throws SQLException{
		String update = "UPDATE Pacchetto SET foto"+ numeroFoto+ " = "+urlFoto+" WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static void cancellaFotoPacchetto(int idPacchetto, int numeroFoto) throws SQLException{
		modificaFotoPacchetto(idPacchetto, "NULL", numeroFoto);
	}
	
	public static void creaPernottamento(int idHotel, TipoCamera tc) throws SQLException{
		String insert = "INSERT INTO Pernottamento ('hotel','tipo','selezionabile') VALUES ("+idHotel+", "+tc.name()+",1)";
		stmt.executeUpdate(insert);
	}
	
	public static void creaHotel(String nome, String citta, String indirizzo, String tel, String descrizione, float prezzoCamere[], String urlFoto1, String urlFoto2, String urlFoto3) throws SQLException{
		String insert = "INSERT INTO Hotel (`idHotel`, `nome`, `citta`, `indirizzo`, `telefono`, `descrizione`,'selezionabile') VALUES (NULL,"+nome+","+citta+","+indirizzo+","+tel+","+descrizione+"1,)";
		stmt.executeUpdate(insert);
		//L'idHotel non ce l'abbiamo, va recuperato! Faccio una query su nome, citt� e indirizzo.
		String select = "SELECT idHotel FROM Hotel WHERE nome ="+nome+" AND citta ="+citta+" AND indirizzo = "+indirizzo;
		ResultSet rs = stmt.executeQuery(select);
		int idHotel =rs.getInt("idHotel");
		insert = "INSERT INTO TipoCamere_Hotel VALUES ("+idHotel+","+"LOWCOST"+","+prezzoCamere[0]+")";
		stmt.executeUpdate(insert);
		insert = "INSERT INTO TipoCamere_Hotel VALUES ("+idHotel+","+"SMART"+","+prezzoCamere[1]+")";
		stmt.executeUpdate(insert);
		insert = "INSERT INTO TipoCamere_Hotel VALUES ("+idHotel+","+"DREAM"+","+prezzoCamere[2]+")";
		stmt.executeUpdate(insert);
	}
	
	public static void creaAttivita(int anno, int mese, int giorno, int ora, int minuti, String titolo, String descrizione, String citta, float prezzo, String foto1, String foto2, String foto3) throws SQLException{
		String data = anno+"-"+mese+"-"+giorno;
		String time = ora+":"+minuti+":00";
		String insert = "INSERT INTO Attivita(`idAttivita`, `data`, `ora`, `titolo`, `descrizione`, `citta`, `prezzo`,'selezionabile') VALUES (NULL,"+data+","+ora+","+time+","+titolo+","+descrizione+","+citta+","+prezzo+"1,)";
		stmt.executeUpdate(insert);
	}
	
	public static void modificaFotoComponente(TipoComponente tc, int idComponente, int numeroFoto, String urlFoto) throws SQLException{
		String update = "UPDATE "+ tc.name()+ " SET foto"+ numeroFoto+ " = "+urlFoto+" WHERE id"+tc.name()+" = "+idComponente;
		stmt.executeUpdate(update);
	}
	
	public static void eliminaFotoComponente(TipoComponente tc, int idComponente, int numeroFoto) throws SQLException{
		modificaFotoComponente(tc,idComponente,numeroFoto,"NULL");
	}
	
	public static void modificaPernottamento(int idPernottamento, int idHotel, TipoCamera tc) throws SQLException{
		String update = "UPDATE Pernottamento SET hotel = " +idHotel+ ",tipo = "+ tc.name()+ " WHERE idPernottamento = "+idPernottamento;
		stmt.executeUpdate(update);
	}
	
	public static void modificaHotel(int idHotel, String nome, String citta, String indirizzo, String tel, String descrizione, float prezziCamere[]) throws SQLException{
		String update = "UPDATE Hotel SET nome = "+nome+", citta = "+citta+", indirizzo = "+indirizzo+", telefono = "+tel+", descrizione ="+descrizione+" WHERE idHotel = "+idHotel;
		stmt.executeUpdate(update);	
		update = "UPDATE TipoCamere_Hotel SET prezzo="+prezziCamere[0]+" WHERE tipoCamera=LOWCOST AND  idHotel = "+idHotel;
		stmt.executeUpdate(update);	
		update = "UPDATE TipoCamere_Hotel SET prezzo="+prezziCamere[1]+" WHERE tipoCamera=SMART AND  idHotel = "+idHotel;
		stmt.executeUpdate(update);
		update = "UPDATE TipoCamere_Hotel SET prezzo="+prezziCamere[2]+" WHERE tipoCamera=DREAM AND  idHotel = "+idHotel;
		stmt.executeUpdate(update);
	}
	
	public static void modificaAttivita(int idAttivita,int anno, int mese, int giorno, int ora, int minuti, String titolo, String descrizione, String citta, float prezzo) throws SQLException{
		String data = anno+"-"+mese+"-"+giorno;
		String time = ora+":"+minuti+":00";
		String update = "UPDATE Attivita SET data = "+data+", ora ="+time+", titolo = "+titolo+", descrizione = "+descrizione+", citta = "+citta+", prezzo ="+prezzo+"WHERE idAttivita = "+idAttivita;
		stmt.executeUpdate(update);
	}
	
	public static void eliminaComponente(TipoComponente tc, int idComponente) throws SQLException{
		String delete = "DELETE FROM "+tc.name()+ "WHERE id"+tc.name()+" = "+idComponente;
		stmt.executeUpdate(delete);
	}
	
	public static void eliminaPacchetto(int idPacchetto) throws SQLException{
		String update = "UPDATE Pacchetto SET selezionabile = 0 WHERE idPacchetto = "+idPacchetto;
		stmt.executeUpdate(update);
	}
	
	public static Hotel getHotel(int idHotel) throws SQLException{
		String query = "SELECT * FROM Hotel WHERE idHotel = "+idHotel;
		ResultSet rs = stmt.executeQuery(query);
		String nome = rs.getString("nome");
		String citta = rs.getString("citta");
		String indirizzo = rs.getString("indirizzo");
		String telefono = rs.getString("telefono");
		String descrizione = rs.getString("descrizione");
		ArrayList<TipoCamera> a = getCamereHotel(idHotel);
		Hotel h = new Hotel(idHotel,nome,citta,indirizzo,telefono,descrizione,a);
		return h;
	}
	
	public static ArrayList<TipoCamera> getCamereHotel(int idHotel) throws SQLException{
		String query = "SELECT * FROM TipoCamere_Hotel WHERE idHotel = "+idHotel;
		ResultSet rs =stmt.executeQuery(query);
		ArrayList<TipoCamera> a = new ArrayList<TipoCamera>();
		while(rs.next()){
			String tipo = rs.getString("tipoCamera");
			TipoCamera tc = TipoCamera.valueOf(tipo);
			a.add(tc);
		}
		return a;
	}
	
	@SuppressWarnings("deprecation")
	public static Attivita getAttivita(int idAttivita) throws SQLException{
		String query = "SELECT * FROM Attivita WHERE idAttivita = "+idAttivita;
		ResultSet rs = stmt.executeQuery(query);
		Date d = rs.getDate("data");
		int anno = d.getYear();
		int mese = d.getMonth();
		int giorno = d.getDay();
		Time e = rs.getTime("ora");
		int ora = e.getHours();
		int minuti = e.getMinutes();
		int secondi = e.getSeconds();
		String titolo = rs.getString("titolo");
		String descrizione = rs.getString("descrizione");
		String citta = rs.getString("citta");
		float prezzo = rs.getFloat("prezzo");
		Attivita a = new Attivita(idAttivita, anno,mese,giorno,ora,minuti,secondi,titolo,descrizione,citta,prezzo);
		return a;
	}
	
	public static Pernottamento getPernottamento(int idPernottamento, int idViaggio) throws SQLException{
		String query = "SELECT * FROM Pernottamento WHERE idPernottamento = "+idPernottamento;
		ResultSet rs = stmt.executeQuery(query);
		Hotel h = getHotel(rs.getInt("hotel"));
		TipoCamera tc = TipoCamera.valueOf(rs.getString("tipo"));
		query = "SELECT regalabile FROM Viaggio_Pernottamento WHERE idPernottamento = "+idPernottamento+" AND idViaggio = "+idViaggio;
		int i = rs.getInt("regalabile");
		boolean regalabile=false;
		if(i==1){
			regalabile=true;
		}
		Pernottamento p = new Pernottamento(idPernottamento,h,tc,regalabile);
		return p;
	}
	
}
