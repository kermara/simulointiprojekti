package model;

import java.util.LinkedList;
import eduni.distributions.ContinuousGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="palvelupistekanta")



public class Palvelupiste {
	
	private Moottori moottori;
	
	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>();
	private ContinuousGenerator generaattori;
	
	private boolean varattu = false;
	@Id
	@Column
	private int id = 0;
	@Column
	private TapahtumanTyyppi palvelutyyppi;
	@Column
	private int asiakkaidenlkm = 0;
	@Column
	private long palveluaikaB = 0;
	
	
	public Palvelupiste(ContinuousGenerator generaattori, Moottori moottori, TapahtumanTyyppi palvelutyyppi) {
		this.generaattori = generaattori;
		this.moottori = moottori;
		this.palvelutyyppi = palvelutyyppi;
	}
	
	public Palvelupiste(int id, TapahtumanTyyppi palvelutyyppi, int asiakkaidenlkm, long palveluaikaB) {
		this.id= id;
		this.palvelutyyppi = palvelutyyppi;
		this.asiakkaidenlkm = asiakkaidenlkm;
		this.palveluaikaB = palveluaikaB;
	}
	
	public Palvelupiste() {
		
	}
	
	public void lisaaJonoon(Asiakas a) {
		asiakkaidenlkm++;
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
		palveluaikaB += palveluaika;
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
	
	public int getId() {
		id++;
		return id;
	}
	
	public TapahtumanTyyppi getPalvelutyyppi() {
		return palvelutyyppi;
	}
	
	public void setPalvelutyyppi(TapahtumanTyyppi palvelutyyppi) {
		this.palvelutyyppi = palvelutyyppi;
	}

	public int getAsiakkaidenmaara() {
		return asiakkaidenlkm;
	}
	
	public long getPalveluaikaB() {
		return palveluaikaB;
	}
	
	public void setAsiakkaidenlkm(int asiakkaidenlkm) {
		this.asiakkaidenlkm = asiakkaidenlkm;
	}
	
	public long getB() {
		return palveluaikaB;
	}
	
	public void setPalveluaikaB(long palveluaikaB) {
		this.palveluaikaB = palveluaikaB;
	}
	
	
}
