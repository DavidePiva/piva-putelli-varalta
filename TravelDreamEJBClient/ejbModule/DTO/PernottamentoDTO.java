package DTO;

public class PernottamentoDTO {

	private int idPernottamento;

	private byte selezionabile;

	private String tipo;
	
	private int idHotel;

	public int getIdPernottamento() {
		return idPernottamento;
	}

	public void setIdPernottamento(int idPernottamento) {
		this.idPernottamento = idPernottamento;
	}

	public byte getSelezionabile() {
		return selezionabile;
	}

	public void setSelezionabile(byte selezionabile) {
		this.selezionabile = selezionabile;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	
	
	
	
}
