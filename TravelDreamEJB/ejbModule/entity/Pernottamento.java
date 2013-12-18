package entity;

import enums.TipoCamera;

public class Pernottamento {
	
	int id;
	Hotel hotel;
	TipoCamera tipoCamera;
	boolean regalabile;
	
	public Pernottamento() {
		super();
		this.id = 0;
		this.hotel = null;
		this.tipoCamera = null;
		this.regalabile = false;
	}
	
	public Pernottamento(int id, Hotel hotel, TipoCamera tipoCamera, boolean regalabile) {
		super();
		this.id = id;
		this.hotel = hotel;
		this.tipoCamera = tipoCamera;
		this.regalabile = regalabile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public TipoCamera getTipoCamera() {
		return tipoCamera;
	}

	public void setTipoCamera(TipoCamera tipoCamera) {
		this.tipoCamera = tipoCamera;
	}

	public boolean isRegalabile() {
		return regalabile;
	}

	public void setRegalabile(boolean regalabile) {
		this.regalabile = regalabile;
	}
	
	
	
	
}
