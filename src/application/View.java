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
		
		gc.strokeText("1. Valikko",25, 50);
		gc.strokeText("Jonossa",125, 50);
		gc.drawImage(new Image("file:resources/images/puhelinpalvelu.png"), 25, 55);
		gc.strokeText("2. Varaus",25, 150);
		gc.drawImage(new Image("file:resources/images/ajanvaraaminen.png"), 25, 155);
		gc.strokeText("3. Peruutus",25, 250);
		gc.drawImage(new Image("file:resources/images/ajanperuuttaminen.png"),25, 255);
		gc.strokeText("4. Neuvonta",25, 350);
		gc.drawImage(new Image("file:resources/images/neuvonta.png"), 25, 355);
		gc.strokeText("Saapunet asiakkaat yhteensä", 25, 450);
	}
	
	public void paivitaNaytto(int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 125;
		j = 110;
		gc.setFill(Color.BLACK);
	
		gc.fillText("" + lkm1, i, j);
		gc.fillText("" + lkm2, i, j +100);
		gc.fillText("" + lkm3, i, j + 200);
		gc.fillText("" + lkm4, i, j +300);
	}
	
	public void naytaAsiakkaat(int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 300;
		j = 110;
		gc.setFill(Color.BLACK);
	
		gc.fillText("" + lkm1, i, j);
		gc.fillText("" + lkm2, i, j +100);
		gc.fillText("" + lkm3, i, j + 200);
		gc.fillText("" + lkm4, i, j +300);
		
	}
	
	public void uusiAsiakas(int id) {
		double i = 300;
		double j = 450;
		gc.setFill(Color.AZURE);
		tyhjennaNaytto();
		gc.fillRect(i,j,10,10);
		gc.setFill(Color.RED);
		//gc.fillText("" + id, i, j);
		i = (i + 12) % this.getWidth();
		j = (j + 12) % this.getHeight();
	}
	
	
}
