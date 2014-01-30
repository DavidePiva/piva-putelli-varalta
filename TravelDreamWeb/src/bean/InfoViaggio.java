package bean;

public class InfoViaggio {

	private int idViaggio;
	private int numeroPersone;
	private String citta;
	private String titolare;
	
	public InfoViaggio(){
		
	}
	
	public void setTitolare(String email){
		titolare = email;
	}
	
	public String getTitolare(){
		return titolare;
	}
	
	public int getIdViaggio() {
		return idViaggio;
	}
	public void setIdViaggio(int idViaggio) {
		this.idViaggio = idViaggio;
	}
	public int getNumeroPersone() {
		return numeroPersone;
	}
	public void setNumeroPersone(int numeroPersone) {
		this.numeroPersone = numeroPersone;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public String toString(){
		return "Viaggio a "+citta+" per "+numeroPersone+" persone";
	}
	
	public String riepilogo(){
		return titolare + " ti ha invitato a " + citta;
	}
	
	public String regalo(){
		return titolare + " farà un viaggio a " + citta + ", vuoi regalare qualcosa?";
	}
}
