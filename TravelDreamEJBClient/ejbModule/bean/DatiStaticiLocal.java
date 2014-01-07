package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Local;


import DTO.AeroportoDTO;

@Local
public interface DatiStaticiLocal {
	public String esempio();

	public ArrayList<AeroportoDTO> aeroporti() throws InstantiationException,IllegalAccessException, ClassNotFoundException, SQLException;

	//public AeroportoDTO convertiADTO(Aeroporto aeroporto);

}
