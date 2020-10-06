package model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;

public class Palvelupiste {
	
	private Moottori moottori;
	
	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	private ContinuousGenerator generaattori;
	private TapahtumanTyyppi palvelutyyppi;
	private boolean varattu = false;
	private int asiakkaidenmaara = 0;
	private long B = 0;
	public Palvelupiste(ContinuousGenerator generaattori, Moottori moottori, TapahtumanTyyppi palvelutyyppi) {
		this.generaattori = generaattori;
		this.moottori = moottori;
		this.palvelutyyppi = palvelutyyppi;
	}
	
	public void lisaaJonoon(Asiakas a) {
		asiakkaidenmaara++;
		jono.add(a);
	}
	
	public Asiakas otajonosta() {
		varattu = false;
		//asiakkaidenmaara--;
		return jono.poll();
	}
	
	public void aloitaPalvelu() {
		varattu = true;
		long palveluaika = (long)generaattori.sample();
		B += palveluaika;
		moottori.uusiTapahtuma(new Tapahtuma(palvelutyyppi, Kello.getInstance().getAika() + palveluaika));
	}
	
	public boolean onVarattu() {
		return varattu;
	}
	
	public boolean onJonossa() {
		return jono.size() !=0;
	}
	
	public int jononPituus() {
		return jono.size();
	}
	
	public int getAsiakkaidenmaara() {
		return asiakkaidenmaara;
	}
	
	public long getB() {
		return B;
	}

}
