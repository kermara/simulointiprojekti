package Controller;

public interface IKontrolleri {
	
	// käyttöliittymä:
	
	public void kaynnistaSimulointi();
	public void nopeuta();
	public void hidasta();
	
	// moottori:
	
	public void naytaLoppuaika(long aika);
	public void visualisoiAsiakas();

}
