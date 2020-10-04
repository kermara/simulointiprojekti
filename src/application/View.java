package application;

import javafx.scene.canvas.Canvas;

//kuvat https://www.iconfinder.com/iconsets/mobile-smart-phone

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class View extends Canvas{
	
	private GraphicsContext gc;
	
	double i = 20;
	double j = 20;
	public View(int w, int h) {
		super(w,h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}
	
	public void tyhjennaNaytto() {
		gc.setFill(Color.valueOf("#06D6A0"));
		gc.fillRect(0,10, this.getWidth(), this.getHeight());
		gc.strokeText("Simuloinnin visualisointi",220, 30);
		gc.strokeText("1. Valikko",25, 45);
		gc.drawImage(new Image("file:resources/images/puhelinpalvelu.png"), 25, 50);
		gc.strokeText("Saapunet asiakkaat yhteensä",100, 100);
		gc.strokeText("2. Varaus",25, 145);
		gc.drawImage(new Image("file:resources/images/ajanvaraaminen.png"), 25, 150);
		gc.strokeText("3. Peruutus",25, 245);
		gc.drawImage(new Image("file:resources/images/ajanperuuttaminen.png"),25, 250);
		gc.strokeText("4. Neuvonta",25, 345);
		gc.drawImage(new Image("file:resources/images/neuvonta.png"), 25, 350);
	}
	
	public void paivitaNaytto(int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 100;
		j = 110;
		
		//gc.fillRect(i,j,50,50);
		gc.setFill(Color.BLACK);
		
		gc.fillText("" + lkm1, i, j);
		gc.fillText("" + lkm2, i, j +100);
		gc.fillText("" + lkm3, i, j + 200);
		gc.fillText("" + lkm4, i, j +300);
		
	}
	
	/*
	public void uusiAsiakas(int id) {
		double i = 300;
		double j = 100;
		//gc.setFill(Color.AZURE);
		//tyhjennaNaytto();
		gc.fillRect(i,j,10,10);
		gc.setFill(Color.RED);
		
		//i = (i + 12) % this.getWidth();
		//j = (j + 12) % this.getHeight();
	}
	*/
	
	/*
	public void naytaTeksti() {
		gc.setFill(Color.BLUE);
		gc.setLineWidth(1);
		gc.strokeText("Tapahtuma",50, 50);
	}
	*/
}
