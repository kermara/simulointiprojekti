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
	
	private long poistumisaikaValikko = 0;
	private int asiakaslkmValikko = 0;
	
	private long poistumisaikaVaraus = 0;
	private int asiakaslkmVaraus= 0;
	
	private long poistumisaikaPeruutus = 0;
	private int asiakaslkmPeruutus= 0;
	
	private long poistumisaikaNeuvonta = 0;
	private int asiakaslkmNeuvonta= 0;
	
	
	public Moottori(IKontrolleri kontrolleri) {
		this.kontrolleri = kontrolleri;
		int vasen = kontrolleri.syoteNormalVasen();
		int oikea = kontrolleri.syoteNormalOikea();
		
		palvelupisteet[0] = new Palvelupiste(new Normal(2,1), this, TapahtumanTyyppi.VALIKKO);
		palvelupisteet[1] = new Palvelupiste(new Normal(10,3), this, TapahtumanTyyppi.VARAUS);
		palvelupisteet[2] = new Palvelupiste(new Normal(10,2), this, TapahtumanTyyppi.PERUUTUS);
		palvelupisteet[3] = new Palvelupiste(new Normal(6,3), this, TapahtumanTyyppi.NEUVONTA);
		
		kello = Kello.getInstance();
		kello.setAika(0);
		
		saapumisprosessi = new Saapumisprosessi(this, new Normal(3,2), TapahtumanTyyppi.SAAPUMINENJONOON);
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
		
		kontrolleri.paivitaNaytto(palvelupisteet[0].jononPituus(), palvelupisteet[1].jononPituus(), palvelupisteet[2].jononPituus(), palvelupisteet[3].jononPituus());
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
			kontrolleri.visualisoiAsiakas(a.getId());
			
			break;
			
		case VALIKKO: a = palvelupisteet[0].otajonosta();
		
			poistumisaikaValikko += a.setMenoaika(Kello.getInstance().getAika());
			asiakaslkmValikko++;
 			a.palveluraportti();
		
			int luku = (int) (Math.random() * 100) + 1;
			
			a.setTuloaika(Kello.getInstance().getAika());
			
			if(luku > 0 && luku <41) {
				palvelupisteet[1].lisaaJonoon(a);
				System.out.println(a);
			}
			
			if(luku > 40 && luku <81) {
				palvelupisteet[2].lisaaJonoon(a);
				System.out.println(a);
			}
			
			if(luku > 80 && luku <101) {
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
			poistumisaikaVaraus += a.setMenoaika(Kello.getInstance().getAika());
			asiakaslkmVaraus++;
			a.palveluraportti();
			System.out.println(a);
			break;
		
		case PERUUTUS: a = palvelupisteet[2].otajonosta();
			poistumisaikaPeruutus += a.setMenoaika(Kello.getInstance().getAika());
			asiakaslkmPeruutus++;
			a.palveluraportti();
			System.out.println(a);
			break;
		
		case NEUVONTA: a = palvelupisteet[3].otajonosta();
			poistumisaikaNeuvonta += a.setMenoaika(Kello.getInstance().getAika());
			asiakaslkmNeuvonta++;
			a.palveluraportti();
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
	System.out.println("Tulokset" );
	System.out.println("Saapuneiden asiakkaiden kokonaismäärä (A): " +(palvelupisteet[0].getAsiakkaidenmaara()));
	
	long paattymisAika = kello.getAika();
	System.out.println("Simulointi päättyi kello eli simuloinnin kokonaisaika (T) " + paattymisAika);
	
	int pA = asiakaslkmVaraus + asiakaslkmPeruutus + asiakaslkmNeuvonta;
	System.out.println("Palveltujen asiakkaiden kokonaismäärä (C): " + pA);
	
	System.out.println("Palvelupisteen Varaus aktiiviaika (B): " + palvelupisteet[1].getB());
	System.out.println("Palvelupisteen Peruutus aktiiviaika (B): " + palvelupisteet[2].getB());
	System.out.println("Palvelupisteen Neuvonta aktiiviaika (B): " + palvelupisteet[3].getB());
	
	System.out.println("Palvelupisteen Varaus käyttöaste (U): " + (palvelupisteet[1].getB()/(double)paattymisAika)*100);
	System.out.println("Palvelupisteen Peruutus käyttöaste (U): " + (palvelupisteet[2].getB()/(double)paattymisAika)*100);
	System.out.println("Palvelupisteen Neuvonta käyttöaste (U): " + (palvelupisteet[3].getB()/(double)paattymisAika)*100);
	
	System.out.println("Palvelupisteen suoritusteho (X): " + (asiakaslkmVaraus/(double)paattymisAika)*100);
	
	
	a.getId();
	System.out.println("" + a.toString());
	kontrolleri.naytaLoppuaika(kello.getAika());
	kontrolleri.naytaAsiakasmaara(palvelupisteet[0].getAsiakkaidenmaara());
	kontrolleri.naytaAsiakkaat(palvelupisteet[0].getAsiakkaidenmaara(), palvelupisteet[1].getAsiakkaidenmaara(), palvelupisteet[2].getAsiakkaidenmaara(), palvelupisteet[3].getAsiakkaidenmaara());
	Trace.out(Trace.Level.INFO, "0: " +(palvelupisteet[0].jononPituus()));
	Trace.out(Trace.Level.INFO, "1: " +(palvelupisteet[1].jononPituus()));
	Trace.out(Trace.Level.INFO, "2: " +(palvelupisteet[2].jononPituus()));
	Trace.out(Trace.Level.INFO, "3: " +(palvelupisteet[3].jononPituus()));
	
	Trace.out(Trace.Level.INFO, "0: " +(palvelupisteet[0].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "1: " +(palvelupisteet[1].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "2: " +(palvelupisteet[2].getAsiakkaidenmaara()));
	Trace.out(Trace.Level.INFO, "3: " +(palvelupisteet[3].getAsiakkaidenmaara()));
	kontrolleri.naytaJononpituus(palvelupisteet[0].jononPituus());
	System.out.println("Asiakaslukumäärät " + asiakaslkmValikko);
	System.out.println("Palveluajat " + poistumisaikaValikko);
}
















}