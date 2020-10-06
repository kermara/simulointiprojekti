package Controller;

import application.IGUI;
import javafx.application.Platform;
import model.IMoottori;
import model.Moottori;

public class Kontrolleri implements IKontrolleri {
	
	private IMoottori moottori;
	private IGUI igui;
	
	public Kontrolleri(IGUI igui) {
		this.igui = igui;
	}

	@Override
	public void kaynnistaSimulointi() {
		moottori = new Moottori(this);
		moottori.setSimulointiaika(igui.getAika());
		moottori.setViive(igui.getViive());
		igui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();
	}

	@Override
	public void nopeuta() {
		moottori.setViive(moottori.getViive()/50);
	}

	@Override
	public void hidasta() {
		moottori.setViive(moottori.getViive()*25);
	}

	@Override
	public void naytaLoppuaika(long aika) {
		Platform.runLater(()->igui.setLoppuaika(aika));
	}

	@Override
	public void visualisoiAsiakas(int id) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				igui.getVisualisointi().uusiAsiakas(id);
				
			}
		});
	}
	
	
	public void paivitaNaytto(int lkm1, int lkm2, int lkm3, int lkm4) {
	Platform.runLater(()->igui.getVisualisointi().paivitaNaytto(lkm1, lkm2, lkm3, lkm4));
	}
	
	public void naytaAsiakkaat(int lkm1, int lkm2, int lkm3, int lkm4) {
		Platform.runLater(()->igui.getVisualisointi().naytaAsiakkaat(lkm1, lkm2, lkm3, lkm4));
	}
	
	
	public void naytaAsiakasmaara(int i) {
		Platform.runLater(()-> igui.setAsiakasmaara(i));
	}
	
	public void naytaJononpituus(int i) {
		Platform.runLater(()-> igui.setJononpituus(i));
	}

	@Override
	public int syoteNormalVasen() {
		return igui.getSyoteNormalVasen();
	}

	@Override
	public int syoteNormalOikea() {

		return igui.getSyoteNormalOikea();
	}
	
	
}
