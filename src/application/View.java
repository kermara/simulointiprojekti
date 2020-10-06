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
		
		gc.strokeText("Valikko",25, 150);
		gc.strokeText("Jonossa",125, 50);
		gc.drawImage(new Image("file:resources/images/puhelinpalvelu.png"), 30, 155);
		gc.strokeText("1. Varaus",220, 50);
		gc.drawImage(new Image("file:resources/images/ajanvaraaminen.png"), 220, 55);
		gc.strokeText("2. Peruutus",220, 150);
		gc.drawImage(new Image("file:resources/images/ajanperuuttaminen.png"),220, 155);
		gc.strokeText("3. Neuvonta",220, 250);
		gc.drawImage(new Image("file:resources/images/neuvonta.png"), 220, 255);
		gc.strokeText("Jonoon tulleet asiakkaat yhteensä", 25, 450);
	}
	
	public void paivitaNaytto(int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 100;
		j = 110;
		
		gc.setFill(Color.BLACK);
		
		/*
		for(int x = 0; x <lkm1; x++) {
			gc.fillOval(i,j,10,10);
				i = (10 + 12); //% this.getWidth();
				j = (170); //this.getHeight();
		}

		*/
	
	
		//gc.fillText("" + lkm1, 10, 170);
		
		for(int x = 0; x <lkm2; x++) {
			gc.fillOval(i,j,10,10);
			if (i >= 250) {
				i = 130;
				gc.setFill(Color.RED);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				i = (130 + 10); //% this.getWidth();
				j = (110 - 10); //% this.getHeight();
		}
		//gc.fillText("" + lkm2, 130, 110 );
		
	/*
		
		i = (i - 12) % this.getWidth();
		j = (j - 12) % this.getHeight();
		gc.fillText("" + lkm3, 130, 190);
		gc.fillOval(i,j,10,10);
		i = (i + 12) % this.getWidth();
		j = (j + 12) % this.getHeight();
		gc.fillText("" + lkm4, 130, 270);
		gc.fillOval(i,j,10,10);
		i = (i + 12) % this.getWidth();
		j = (j + 12) % this.getHeight();
		
		*/
	}
	
	public void naytaAsiakkaat(int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 300;
		j = 100;
		gc.setFill(Color.BLACK);
	
		gc.fillText("" + lkm1, 300,450);
		gc.fillText("" + lkm2, i, j);
		gc.fillText("" + lkm3, i, j + 100);
		gc.fillText("" + lkm4, i, j +200);
		
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
