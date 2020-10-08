package Controller;

import javafx.collections.ObservableList;

public interface IKontrolleri {
	
	// käyttöliittymä:
	
	public void kaynnistaSimulointi();
	public void nopeuta();
	public void hidasta();
	
	// moottori:
	
	public void naytaLoppuaika(long aika);
	public void visualisoiAsiakas(int id);
	
	public void naytaAsiakkaat(int lkm, int lkm1, int lkm2, int lkm3, int lkm4);

	public void jono1(int jononPituus);
	public void jono2(int jononPituus);
	public void jono3(int jononPituus);
	public void jono4(int jononPituus);
	
	
	public int syoteNormalVasen();
	public int syoteNormalOikea();
	
	public void naytaTulokset(ObservableList<String> tiedot);

}
