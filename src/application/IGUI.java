package application;

public interface IGUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public long getAika();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(long aika);
	public View getVisualisointi();
	public void setAsiakasmaara(int i);
	public void setJononpituus(int i);

	

}
