package model;

import eduni.distributions.ContinuousGenerator;

public class Saapumisprosessi {
	
	private Moottori saapumismoottori;
	private ContinuousGenerator saapumisgeneraattori;
	private TapahtumanTyyppi saapumistyyppi;
	
	/*Testejä varten,  ks. alla oleva kommentoitu osuus  
	int i = 0;
	 int i = 0;
	 long sum = 0;
	 long max = 0;
	 long min = 100;
	 */
	
	public Saapumisprosessi(Moottori saapumismoottori, ContinuousGenerator saapumisgeneraattori, TapahtumanTyyppi saapumistyyppi) {
		this.saapumismoottori = saapumismoottori;
		this.saapumisgeneraattori = saapumisgeneraattori;
		this.saapumistyyppi = saapumistyyppi;
	}
	
	public void luoSeuraavaSaapuminen() {
		Tapahtuma t = new Tapahtuma(saapumistyyppi, Kello.getInstance().getAika()+(long)saapumisgeneraattori.sample());
		saapumismoottori.uusiTapahtuma(t);
		
		/* Generaattorin tuottamien lukujen tutkimista (keskiarvo, peinin, suurin) 		
		double aika = generaattori.sample();
		sum = sum + aika;
		i++;
		System.out.println(sum/i);
		System.out.println(min);
		System.out.println(max);
		System.out.println();
		if (max < aika) max = aika;
		if (min > aika) min = aika;
		*/
	}

}
