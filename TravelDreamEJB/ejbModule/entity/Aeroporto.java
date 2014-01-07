package entity;

import DTO.AeroportoDTO;

public class Aeroporto {
	
	int id;
	String nome;
	String citta;
	
	public Aeroporto() {
		super();
		this.id = 0;
		this.nome = null;
		this.citta = null;
	}
	/*public Aeroporto(int id, String nome, String citta) {
		super();
		this.id = id;
		this.nome = nome;
		this.citta = citta;
	}*/
	public Aeroporto(AeroportoDTO a){
		this.id=a.getId();
		this.nome=a.getNome();
		this.citta=a.getCitta();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
}
