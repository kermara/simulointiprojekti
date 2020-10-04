package model;

public class Asiakas {
	
	private long saapumisaika;
	private long poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	
	public Asiakas() {
		id = i++;
		saapumisaika = (long)Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas: " + id + " saapui: " + saapumisaika);
	}
	
	public long getPoistumisaika() {
		return poistumisaika;
	}
	
	public void setPoistumisaika(long poistumisaika) {
		this.poistumisaika = poistumisaika;
	}
	
	public long getSaapumisaika() {
		return saapumisaika;
	}
	
	public void setSaapumisaika(long saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public void raportti() {
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui: " + saapumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui: " + poistumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi: " + (poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}

}
