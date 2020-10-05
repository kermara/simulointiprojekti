package Controller;

public interface IKontrolleri {
	
	// käyttöliittymä:
	
	public void kaynnistaSimulointi();
	public void nopeuta();
	public void hidasta();
	
	// moottori:
	
	public void naytaLoppuaika(long aika);
	public void visualisoiAsiakas(int id);
	public void naytaAsiakasmaara(int i);
	public void naytaJononpituus(int i);
	public void paivitaNaytto(int jononPituus, int jononPituus2, int jononPituus3, int jononPituus4);
	public void naytaAsiakkaat(int jononPituus, int jononPituus2, int jononPituus3, int jononPituus4);

}
