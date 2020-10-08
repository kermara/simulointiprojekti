package application;

import javafx.collections.ObservableList;

public interface IGUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public long getAika();
	public long getViive();
	public int getSyoteNormalVasen();
	public int getSyoteNormalOikea();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(long aika);
	public View getVisualisointi();
	public void naytaTulokset(ObservableList<String> tiedot);

	

}
