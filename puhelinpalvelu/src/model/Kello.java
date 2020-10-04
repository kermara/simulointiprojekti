package model;

public class Kello {
	
	private long aika;
	private static Kello INSTANCE;
	
	private Kello() {
		aika = 0;
	}
	
	public static Kello getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Kello();
		}
		return INSTANCE;
	}
	
	public void setAika(long aika) {
		this.aika = aika;
	}
	
	public long getAika() {
		return aika;
	}

}
