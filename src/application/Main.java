package application;

import Controller.IKontrolleri;
import Controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.Trace;
import model.Trace.Level;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application implements IGUI{
	
	private IKontrolleri kontrolleri;
	
	//Käyttöliittymä
	
	HBox hbox;
	
	private TextField aika;
	private TextField viive;
	private TextField tulos;
	private TextField asiakasmaara;
	private TextField jononp;
	private TextField vasenluku;
	private TextField oikealuku;
	private ToggleGroup tg; 
	private RadioButton kaynnistaButton;
	private RadioButton hidastaButton;
	private RadioButton nopeutaButton;
	
	Duration duration;
	
	private View view;
		
	@Override
	public void init() {
		Trace.setTraceLevel(Level.INFO);
		kontrolleri = new Kontrolleri(this);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);
				}
			});
			
			primaryStage.setTitle("Puhelinpalvelusimulaattori - Normaalijakauma");
			primaryStage.getIcons().add(new Image("file:resources/images/icon.png"));
			kaynnistaButton = new RadioButton();
			kaynnistaButton.setText("Käynnistä");
			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					kontrolleri.kaynnistaSimulointi();
				}
			});
			createHbox();
			Scene scene = new Scene(hbox);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private HBox createHbox() {
		TilePane toiminto = new TilePane();
		toiminto.setMaxWidth(7);
		toiminto.setAlignment(Pos.CENTER);
		
		Label l = new Label("Valitse toiminto:      "); 
		l.setFont(Font.font("Helvetica", FontWeight.BLACK, 20));
		
		tg = new ToggleGroup(); 
		
		nopeutaButton = new RadioButton();
		nopeutaButton.setText("Nopeuta ");
		nopeutaButton.setOnAction(e-> kontrolleri.nopeuta());
		
		hidastaButton = new RadioButton();
		hidastaButton.setText("Hidasta  ");
		hidastaButton.setOnAction(e -> kontrolleri.hidasta());
		
			
		// add radiobuttons to toggle group 
        kaynnistaButton.setToggleGroup(tg); 
        nopeutaButton.setToggleGroup(tg); 
        hidastaButton.setToggleGroup(tg); 
  
        // add label 
        
        toiminto.getChildren().add(l); 
        toiminto.getChildren().add(kaynnistaButton); 
        toiminto.getChildren().add(nopeutaButton); 
        toiminto.getChildren().add(hidastaButton); 
        //r.getChildren().add(l2); 
        
        Text aikaText = new Text("Aseta simulointiaika (ms): ");
       
		
		aika = new TextField("Simulointiaika ");
		aika.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
		aika.setPrefWidth(150);
		
		Text viiveText = new Text("Aseta viive (ms): ");
		
		viive = new TextField("Viive");
		viive.setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		viive.setPrefWidth(150);
		
		Text vasenlukuText = new Text("Aseta jakauman korkeus: ");
		
		vasenluku = new TextField("Jakauman korkeus");
		vasenluku .setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		vasenluku .setPrefWidth(150);
		
		Text oikealukuText = new Text("Aseta jakauman leveys: ");
		
		oikealuku = new TextField("Jakauman leveys");
		oikealuku .setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		oikealuku.setPrefWidth(150);
		
		Text tulosText = new Text("Simuloinnin kokonaisaika (s): ");
		
		tulos = new TextField();
		tulos.setFont(Font.font("Helvetica", FontWeight.NORMAL,10));
		tulos.setPrefWidth(150);
		
		/*
		
		Text asiakasmaaraText = new Text("Palveltujen asiakkaiden määrä: ");	
		
		asiakasmaara = new TextField();
		asiakasmaara.setFont(Font.font("Helvetica", FontWeight.NORMAL,10));
		
		Text jononpText = new Text("Jonon pituus: ");	
		
		jononp = new TextField();
		jononp.setFont(Font.font("Helvetica", FontWeight.NORMAL,10));
		jononp.setPrefWidth(150);
			
		asiakasmaara.setPrefWidth(150);
		*/
		
		
		hbox = new HBox();
		hbox.setPadding(new Insets(15,12,15,12));
		hbox.setBackground(new Background(new BackgroundFill(Color.valueOf("#54C6EB"), null,null)));
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setVgap(20);
		grid.setHgap(10);
		
		
		grid.add(aikaText, 0, 0);
		grid.add(aika,0,1);
		grid.add(viiveText,0, 2);
		grid.add(viive,0, 3);
		grid.add(vasenlukuText, 0, 4);
		grid.add(vasenluku,0,5);
		grid.add(oikealukuText,0, 6);
		grid.add(oikealuku,0,7);
		grid.add(toiminto, 0, 8);
		grid.add(tulosText, 0, 9);
		grid.add(tulos,0, 10);
		
		/*
		grid.add(asiakasmaaraText, 0, 7);
		grid.add(asiakasmaara, 0, 8);
		grid.add(jononpText, 0, 9);
		grid.add(jononp, 0, 10);
		*/
		
			
		view = new View(400, 400);
		hbox.getChildren().addAll(grid, view);
		return hbox;
	}
	

	@Override
	public long getAika() {
		
		return Long.parseLong(aika.getText());
	}

	@Override
	public long getViive() {
			return Long.parseLong(viive.getText());
	}

	@Override
	public void setLoppuaika(long aika) {
		
		duration = new Duration(aika);
				this.tulos.setText("" + duration.toSeconds());
	}
	
	@Override
	public View getVisualisointi() {
			return view;
	}
	
	@Override
	public int getSyoteNormalVasen() {
		return Integer.parseInt(vasenluku.getText());
	}

	@Override
	public int getSyoteNormalOikea() {
		return Integer.parseInt(oikealuku.getText());
	}
}
	/*
	public void setAsiakasmaara(int i) {
		this.asiakasmaara.setText("" +i);
	}

	

	@Override
	public void setJononpituus(int i) {
		this.jononp.setText(""+1);
		
	}
	*/

