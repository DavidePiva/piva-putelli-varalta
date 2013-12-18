package entity;

public class Compagnia {
	int id;
	String nome;
	String descrizione;
	
	public Compagnia(){
		setId(0);
		setNome(null);
		setDescrizione(null);
	}
	
	public Compagnia(int id, String nome, String descrizione) {
		super();
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
}