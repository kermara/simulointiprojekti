package model;

public class Tapahtuma implements Comparable<Tapahtuma> {
	
	private TapahtumanTyyppi tyyppi;
	private long aika;
	
	public Tapahtuma(TapahtumanTyyppi tyyppi, long aika) {
		this.tyyppi = tyyppi;
		this.aika = aika;
	}
	
	public void setTyyppi(TapahtumanTyyppi tyyppi) {
		this.tyyppi = tyyppi;
	}
	
	public TapahtumanTyyppi getTyyppi() {
		return tyyppi;
	}
	
	public void setAika(long aika) {
		this.aika = aika;
	}
	
	public long getAika() {
		return aika;
	}
	
	public int compareTo(Tapahtuma arg) {
		if(this.aika < arg.aika) return -1;
		else if(this.aika > arg.aika) return 1;
		return 0;
		
	}

}
