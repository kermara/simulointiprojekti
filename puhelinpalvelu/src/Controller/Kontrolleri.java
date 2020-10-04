package Controller;

import application.IGUI;
import model.IMoottori;
import model.Moottori;

public class Kontrolleri implements IKontrolleri {
	
	private IMoottori moottori;
	private IGUI igui;
	
	public Kontrolleri(IGUI igui) {
		this.igui = igui;

	@Override
	public void kaynnistaSimulointi() {
		moottori = new Moottori(this);
		moottori.setSimulointiaika(igui.getAika());
		moottori.setViive(igui.getViive());
		igui.getVisualisointi().tyh

	}

	@Override
	public void nopeuta() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hidasta() {
		// TODO Auto-generated method stub

	}

	@Override
	public void naytaLoppuaika(long aika) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visualisoiAsiakas() {
		// TODO Auto-generated method stub

	}

}
