package application;



import Controller.IKontrolleri;
import Controller.Kontrolleri;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application implements IGUI{
	
	private IKontrolleri kontrolleri;
	

	//Käyttöliittymä
	
	HBox hbox;
	
	private Stage dialogStage;
	
	private TextField aika;
	private TextField viive;
	private TextField tulos;
	private TextField vasenluku;
	private TextField oikealuku;
	private Button kaynnistaButton;
	private Button hidastaButton;
	private Button nopeutaButton;
	
	Duration duration;
	
	private View view;
	private ListView<String> tiedotList;
		
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
			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä");
			kaynnistaButton.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
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
		
		        Text aikaText = new Text("Aseta simulointiaika (ms): ");
       
		
		aika = new TextField("1000");
		aika.setFont(Font.font("Helvetica", FontWeight.NORMAL, 15));
		aika.setPrefWidth(150);
		
		Text viiveText = new Text("Aseta viive (ms): ");
		
		viive = new TextField("500");
		viive.setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		viive.setPrefWidth(150);
		
		Text vasenlukuText = new Text("Aseta jakauman korkeus: ");
		
		vasenluku = new TextField("3");
		vasenluku .setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		vasenluku .setPrefWidth(150);
		
		Text oikealukuText = new Text("Aseta jakauman leveys: ");
		
		oikealuku = new TextField("2");
		oikealuku .setFont(Font.font("Helvetica", FontWeight.NORMAL,15));
		oikealuku.setPrefWidth(150);
		
		Label l = new Label("Valitse toiminto:      "); 
		l.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
		
		nopeutaButton = new Button();
		nopeutaButton.setText("Nopeuta ");
		nopeutaButton.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
		nopeutaButton.setOnAction(e-> kontrolleri.nopeuta());
		
		hidastaButton = new Button();
		hidastaButton.setText("Hidasta  ");
		hidastaButton.setFont(Font.font("Helvetica", FontWeight.EXTRA_BOLD, 20));
		hidastaButton.setOnAction(e -> kontrolleri.hidasta());
		
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
		grid.add(l,0, 8);
		grid.add(kaynnistaButton, 0, 9);
		grid.add(nopeutaButton, 0, 10);
		grid.add(hidastaButton,0, 11);
		grid.add(tulosText, 0, 12);
		grid.add(tulos,0, 13);
		
		view = new View(600, 400);
		
		Label tiedotLabel = new Label("Tulokset: ");
		tiedotLabel.setLabelFor(tiedotList);
		tiedotList = new ListView<>();
		tiedotList.setPrefSize(600, 400);
		tiedotList.setBackground(new Background(new BackgroundFill(Color.valueOf("#54C6EB"), null,null)));

				
		hbox.getChildren().addAll(grid, view, tiedotLabel, tiedotList);
		return hbox;
	}
	

	@Override
	public long getAika() {
		String errorMessage = "";

            try {
                Integer.parseInt(aika.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Syötä simulointiaika numeroina\n"; 
            }  
            
            if (errorMessage.length() != 0) {
                 // Show the error message.
                Alert alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Virheitä syötteissä");
                alert.setHeaderText("Korjaa syöte");
                alert.setContentText(errorMessage);
                
                alert.showAndWait();
        }

		return Long.parseLong(aika.getText());
	}

	@Override
	public long getViive() {
		String errorMessage = "";

        try {
            Integer.parseInt(viive.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Syötä viive numeroina\n"; 
        }  
        
        if (errorMessage.length() != 0) {
             // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Virheitä syötteissä");
            alert.setHeaderText("Korjaa syöte");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
    }
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
		String errorMessage = "";

        try {
            Integer.parseInt(vasenluku.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Syötä jakaumankorkeus numeroina\n"; 
        }  
        
        if (errorMessage.length() != 0) {
             // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Virheitä syötteissä");
            alert.setHeaderText("Korjaa syöte");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
    }
		return Integer.parseInt(vasenluku.getText());
	}

	@Override
	public int getSyoteNormalOikea() {
		
		String errorMessage = "";

        try {
            Integer.parseInt(oikealuku.getText());
        } catch (NumberFormatException e) {
            errorMessage += "Syötä jakauman korkeus numeroina\n"; 
        }  
        
        if (errorMessage.length() != 0) {
             // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Virheitä syötteissä");
            alert.setHeaderText("Korjaa syöte");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
    }
		return Integer.parseInt(oikealuku.getText());
	}
	
	public void naytaTulokset(ObservableList<String> tiedot) {
		 tiedotList.setItems(tiedot);
	}
}
	




