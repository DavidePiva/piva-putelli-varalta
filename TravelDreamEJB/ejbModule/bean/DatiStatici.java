package bean;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Aeroporto;
import DTO.AeroportoDTO;


/**
 * Session Bean implementation class DatiStatici
 */
@Stateless
public class DatiStatici implements DatiStaticiLocal {

	@PersistenceContext
	private EntityManager em;

	@Resource
	private EJBContext context;
	
    /**
     * Default constructor. 
     */
    public DatiStatici() {
    }
    
    @Override
    public String esempio(){
    	return "Hello world!";
    }

	@Override
	public AeroportoDTO getAeroportoDTO() {
		AeroportoDTO aeroportoDTO = convertiInDTO(getAeroporto(1));
		return aeroportoDTO;
	}
	
	private AeroportoDTO convertiInDTO(Aeroporto aeroporto) {
		AeroportoDTO a = new AeroportoDTO();
		a.setId(aeroporto.getIdAeroporto());
		a.setNome(aeroporto.getNome());
		a.setCitta(aeroporto.getCitta());
		return a;
	}

	public Aeroporto getAeroporto(int id){
		Aeroporto a = em.find(Aeroporto.class,id);
		return a;
	}

}
