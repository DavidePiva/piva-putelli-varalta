package bean;

public class InfoViaggio {

	private int idViaggio;
	private int numeroPersone;
	private String citta;
	
	public InfoViaggio(){
		
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
}
