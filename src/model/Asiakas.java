package model;

public class Asiakas {
	
	private long saapumisaika;
	private long poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private static int poistumislkm = 0;
	
	private long tuloaika;
	private long menoaika;
	
	public Asiakas() {
		id = i++;
		saapumisaika = (long)Kello.getInstance().getAika();
		tuloaika = (long)Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas id : " + id + " saapui jonoon klo: " + saapumisaika);
	}
	
	public int getId() {
		return id;
	}
	
	
	
	public int getPoistumislkm() {
		return poistumislkm;
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
	
	public static void setPoistumislkm(int poistumislkm) {
		Asiakas.poistumislkm += poistumislkm;
	}
	public long getPoistumisaika() {
		setPoistumisaika(poistumisaika);
		return poistumisaika;
	}
	
	public long getTuloaika() {
		return tuloaika;
	}
	
	public void setTuloaika(long tuloaika) {
		this.tuloaika = tuloaika;
	}
	
	public long getMenoaika() {
		return menoaika;
	}
	
	public long setMenoaika(long menoaika) {
		return this.menoaika = menoaika;
	}
	
	public long palveluaika() {
		return menoaika - tuloaika;
	}
	public void palveluraportti() {
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui palvelupisteeseen: " + tuloaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui palvelupisteestä: " + menoaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi palvelupisteessä: " + (menoaika-tuloaika));
		//sum += (menoaika-tuloaika);
		//long keskiarvo = sum/id;
		//System.out.println("Asiakkaan läpimenoaikojen keskiarvo "+ keskiarvo);
		
	}
	
		
	public String toString() {
		return "Palveltu asiakas " + id + " " + poistumislkm;
	}

}

