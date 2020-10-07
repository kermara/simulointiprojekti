package model;

public interface PalvelupisteDAO {
	
	public interface IPalvelupisteDAO {
		
		boolean createPalvelupiste(Palvelupiste palvelupiste);
		Palvelupiste readPalvelupiste(int id);
		Palvelupiste[] readpalvelupisteet();
		boolean updateValuutta(Palvelupiste palvelupiste);
		boolean deleteValuutta(int id);

	}


}
