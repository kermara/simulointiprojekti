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
		
		
		
		gc.strokeText("Jonossa olevat asiakkaat",50, 50);
		gc.strokeText("Valikko",130, 150);
		gc.drawImage(new Image("file:resources/images/puhelinpalvelu.png"), 130, 155);
		gc.strokeText("Palvellut asiakkaat",450, 30);
		gc.strokeText("1. Varaus",370, 50);
		gc.drawImage(new Image("file:resources/images/ajanvaraaminen.png"), 370, 55);
		gc.strokeText("2. Peruutus",370, 150);
		gc.drawImage(new Image("file:resources/images/ajanperuuttaminen.png"),370, 155);
		gc.strokeText("3. Neuvonta",370, 250);
		gc.drawImage(new Image("file:resources/images/neuvonta.png"), 370, 255);
		gc.strokeText("Yhteensä",370, 355);
	}
	
	
	public void jono1(int jononPituus) {
		i =25;
		j = 150;
		
		//tyhjennaNaytto();
		gc.setFill(Color.valueOf("#A5D605"));	
	
			for(int x = 0; x <jononPituus; x++) {
		
			gc.fillOval(i,j,15,15);
				i = (i+ 30)% this.getWidth(); 
		}
	}
	
	public void jono2(int jononPituus) {
		i =200;
		j = 100;
		
		//tyhjennaNaytto();
		gc.setFill(Color.valueOf("#D69E05"));	
	
			for(int x = 0; x <jononPituus; x++) {
		
			gc.fillOval(i,j,15,15);
		
				i = (i+ 30); 
				j= (j - 15); 
				
		}

	}

		public void jono3(int jononPituus) {
			i =200;
		j = 170;
		
		//tyhjennaNaytto();
		gc.setFill(Color.valueOf("#D6053D"));	
	
			for(int x = 0; x <jononPituus; x++) {
		
			gc.fillOval(i,j,15,15);
		
				i = (i+ 30); 
				
		}
	}

	public void jono4(int jononPituus) {
		i =200;
	j = 250;
	
	//tyhjennaNaytto();
	gc.setFill(Color.valueOf("053DD6"));	

		for(int x = 0; x <jononPituus; x++) {
	
		gc.fillOval(i,j,15,15);
	
			i = (i+ 30); 
			j= (j + 15); 
			
	}
	}


	public void naytaAsiakkaat(int lkm, int lkm1, int lkm2, int lkm3, int lkm4) {
		i = 500;
		j = 100;
		
		tyhjennaNaytto();
		gc.setFill(Color.BLACK);
		
		gc.fillText("" + lkm1, i, j);
		gc.fillText("" + lkm2, i, j + 100);
		gc.fillText("" + lkm3, i, j +200);
		gc.fillText("" + lkm4, i, 355);
		gc.fillText("" + lkm, 50, 200);
		

	}
}






	
	

