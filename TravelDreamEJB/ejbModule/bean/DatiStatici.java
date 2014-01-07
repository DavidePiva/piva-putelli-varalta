package bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.Stateless;

import entity.Aeroporto;
import DTO.AeroportoDTO;
import business.InterfacciaDB;


/**
 * Session Bean implementation class DatiStatici
 */
@Stateless
public class DatiStatici implements DatiStaticiLocal {

    public DatiStatici() {
    }
    
    @Override
    public String esempio(){
    	return "Hello world!";
    }

	
	@Override
	public ArrayList<AeroportoDTO> aeroporti() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		ArrayList<AeroportoDTO> aeroportiDTO = new ArrayList<>();
		ArrayList<Aeroporto> aeroporti = InterfacciaDB.aeroporti();
		for(Aeroporto a : aeroporti){
//			aeroportiDTO.add(convertiADTO(a));
		}
		
		return aeroportiDTO;
	}
/*
	@Override
	public AeroportoDTO convertiADTO(Aeroporto aeroporto) {
		AeroportoDTO aDTO = new AeroportoDTO();
		aDTO.setCitta(aeroporto.getCitta());
		aDTO.setId(aeroporto.getId());
		aDTO.setNome(aeroporto.getNome());
		
		return aDTO;
	}
*/

}
