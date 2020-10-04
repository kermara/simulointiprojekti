package model;

import java.util.Random;

import Controller.IKontrolleri;
import eduni.distributions.Normal;

public class Moottori extends Thread implements IMoottori {
	
	private IKontrolleri kontrolleri;
	private long simulointiaika = 0;
	private long viive = 0;
	
	private Palvelupiste[] palvelupisteet = new Palvelupiste[4];
	private Kello kello;
	
	private Saapumisprosessi saapumisprosessi;
	private Tapahtumalista tapahtumalista;
	
	private Asiakas a;
	
	
	public Moottori(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		
		palvelupisteet[0] = new Palvelupiste(new Normal(6,5), this, TapahtumanTyyppi.VALIKKO);
		palvelupisteet[1] = new Palvelupiste(new Normal(4,2), this, TapahtumanTyyppi.VARAUS);
		palvelupisteet[2] = new Palvelupiste(new Normal(3,2), this, TapahtumanTyyppi.PERUUTUS);
		palvelupisteet[3] = new Palvelupiste(new Normal(4,3), this, TapahtumanTyyppi.NEUVONTA);
		
		kello = Kello.getInstance();
		kello.setAika(0);
		
		saapumisprosessi = new Saapumisprosessi(this, new Normal(6,2), TapahtumanTyyppi.SAAPUMINENJONOON);
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
		

		
		kontrolleri.paivitaNaytto(palvelupisteet[0].jononPituus(), palvelupisteet[1].jononPituus(), palvelupisteet[2].jononPituus(), palvelupisteet[3].jononPituus(), palvelupisteet[3].getAsiakkaidenmaara());
		Trace.out(Trace.Level.INFO, "0: " +(palvelupisteet[0].jononPituus()));
		Trace.out(Trace.Level.INFO, "1: " +(palvelupisteet[1].jononPituus()));
		Trace.out(Trace.Level.INFO, "2: " +(palvelupisteet[2].jononPituus()));
		Trace.out(Trace.Level.INFO, "3: " +(palvelupisteet[3].jononPituus()));
	}
	tulokset();
}
	
private boolean simuloidaan() {
	//Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
	return kello.getAika() < simulointiaika;
}

private void viive() {
	//System.out.println("Viive "  + viive);
	try {
		sleep(viive);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

private long nykyaika() {
	return tapahtumalista.getSeuraavanAika();
}

private void suoritaBTapahtumat()  {
	while(tapahtumalista.getSeuraavanAika() == kello.getAika()) {
		try {
			suoritaTapahtuma(tapahtumalista.poista());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public void suoritaTapahtuma(Tapahtuma t) throws InterruptedException {
	switch (t.getTyyppi()) {
		case SAAPUMINENJONOON: 
			a  = new Asiakas();
			palvelupisteet[0].lisaaJonoon(a);
			Kello.getInstance().getAika();
			saapumisprosessi.luoSeuraavaSaapuminen();
			//kontrolleri.visualisoiAsiakas(a.getId());
			break;
			
		case VALIKKO: a = palvelupisteet[0].otajonosta();
		
			int luku = (int) (Math.random() * 30) + 1;
			
			if(luku > 0 && luku <11) {
				palvelupisteet[1].lisaaJonoon(a);
				System.out.println(a);
			}
			
			if(luku > 10 && luku <21) {
				palvelupisteet[2].lisaaJonoon(a);
				System.out.println(a);
			}
			
			if(luku > 20 && luku <31) {
				palvelupisteet[3].lisaaJonoon(a);
				System.out.println(a);
			}
			
		
			/*
		
			if (palvelupisteet[1].jononPituus() < palvelupisteet[2].jononPituus() 
					&& palvelupisteet[1].jononPituus() < palvelupisteet[3].jononPituus() ) {
					palvelupisteet[1].lisaaJonoon(a);
					System.out.println(a);
				}
			else if (palvelupisteet[2].jononPituus() < palvelupisteet[3].jononPituus()) {
					palvelupisteet[2].lisaaJonoon(a);
					System.out.println(a);
				} 
			else {
					palvelupisteet[3].lisaaJonoon(a);
					System.out.println(a);
			}
			*/
			break;
			
		case VARAUS: a = palvelupisteet[1].otajonosta();
			a.setPoistumisaika(kello.getAika());
			System.out.println(a);
			break;
		
		case PERUUTUS: a = palvelupisteet[2].otajonosta();
			a.setPoistumisaika(kello.getAika());
			System.out.println(a);
			break;
		
		case NEUVONTA: a = palvelupisteet[3].otajonosta();
			a.setPoistumisaika(kello.getAika());
			System.out.println(a);
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
	System.out.println("Tulokset" );
	
	a.getId();
	System.out.println("" + a.toString());
	kontrolleri.naytaLoppuaika(kello.getAika());
	kontrolleri.naytaAsiakasmaara(palvelupisteet[0].getAsiakkaidenmaara());
	Trace.out(Trace.Level.INFO, "0: " +(palvelupisteet[0].jononPituus()));
	Trace.out(Trace.Level.INFO, "1: " +(palvelupisteet[1].jononPituus()));
	Trace.out(Trace.Level.INFO, "2: " +(palvelupisteet[2].jononPituus()));
	Trace.out(Trace.Level.INFO, "3: " +(palvelupisteet[3].jononPituus()));
	
	Trace.out(Trace.Level.INFO, "0: " +(palvelupisteet[0].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "1: " +(palvelupisteet[1].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "2: " +(palvelupisteet[2].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "3: " +(palvelupisteet[3].getAsiakkaidenmaara()));
	kontrolleri.naytaJononpituus(palvelupisteet[0].jononPituus());
}














}