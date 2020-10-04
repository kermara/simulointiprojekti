package model;

import Controller.IKontrolleri;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;

public class Moottori extends Thread implements IMoottori {
	
	private IKontrolleri kontrolleri;
	private long simulointiaika = 0;
	private long viive = 0;
	
	private Palvelupiste[] palvelupisteet = new Palvelupiste[3];
	private Kello kello;
	
	private Saapumisprosessi saapumisprosessi;
	private Tapahtumalista tapahtumalista;
	
	
	public Moottori(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		
		palvelupisteet[0] = new Palvelupiste(new Normal(10,6), this, TapahtumanTyyppi.POISTUMINEN1);
		palvelupisteet[1] = new Palvelupiste(new Normal(10,2), this, TapahtumanTyyppi.POISTUMINEN2);
		palvelupisteet[2] = new Palvelupiste(new Normal(15,3), this, TapahtumanTyyppi.POISTUMINEN3);
		palvelupisteet[3] = new Palvelupiste(new Normal(15,3), this, TapahtumanTyyppi.POISTUMINEN4);
		
		kello = Kello.getInstance();
		kello.setAika(0);
		
		saapumisprosessi = new Saapumisprosessi(this, new Negexp(10,131), TapahtumanTyyppi.SAAPUMINEN1);
		tapahtumalista = new Tapahtumalista();
		saapumisprosessi.luoSeuraavaSaapuminen(); //on eka saapuminen
	}
	
@Override
public void setSimulointiaika(long aika) {
	simulointiaika = aika;
}


@Override
public void setViive(long viive) {
	this.viive = viive;
}

@Override
public long getViive() {
	return viive;
}

public void uusiTapahtuma(Tapahtuma t) {
	tapahtumalista.lisaa(t);
}

@Override
public void run() {
	while(simuloidaan()) {
		viive();
		kello.setAika(nykyaika());
		suoritaBTapahtumat();
		yritaCTapahtumat();
	}
	tulokset();
}
	
private boolean simuloidaan() {
	Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
	return kello.getAika() < simulointiaika;
}

private void viive() {
	System.out.println("Viive "  + viive);
	try {
		sleep(viive);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

private long nykyaika() {
	return tapahtumalista.getSeuraavanAika();
}

private void suoritaBTapahtumat() {
	while(tapahtumalista.getSeuraavanAika() == kello.getAika()) {
		suoritaTapahtuma(tapahtumalista.poista());
	}
}

public void suoritaTapahtuma(Tapahtuma t) {
	Asiakas a;
	switch (t.getTyyppi()) {
		case SAAPUMINEN1: palvelupisteet[0].lisaaJonoon(new Asiakas());
			saapumisprosessi.luoSeuraavaSaapuminen();
			kontrolleri.visualisoiAsiakas();
			break;
			
		case POISTUMINEN1: a = palvelupisteet[0].otajonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
			
		case POISTUMINEN2: a = palvelupisteet[1].otajonosta();
			palvelupisteet[2].lisaaJonoon(a);
			break;
		
		case POISTUMINEN3: a = palvelupisteet[2].otajonosta();
			palvelupisteet[3].lisaaJonoon(a);
			break;
		
		case POISTUMINEN4: a = palvelupisteet[3].otajonosta();
			a.setPoistumisaika(kello.getAika());
			break;
	}
}

public void yritaCTapahtumat() {
	for (Palvelupiste p : palvelupisteet) {
		if(!p.onVarattu() && p.onJonossa()) {
			p.aloitaPalvelu();
		}
	}
}

private void tulokset() {
	System.out.println("Simulointi päättyi kello " + kello.getAika());
	System.out.println("Tulokset");
	
	kontrolleri.naytaLoppuaika(kello.getAika());
}















}