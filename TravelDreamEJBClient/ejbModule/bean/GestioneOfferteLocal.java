package bean;

import javax.ejb.Local;

@Local
public interface GestioneOfferteLocal {

	void setCitta(String citta);

	String getCitta();

}
