package bean;

import javax.ejb.Local;

import DTO.*;

@Local
public interface DatiStaticiLocal {
	public String esempio();
	public AeroportoDTO getAeroportoDTO();
}
