package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class documentation extends BorderPane {
	
	private Scene scene1;
	private Stage monStage;
	String blue = "#0077be";
	
	

public documentation(Scene scene1, Stage monStage) {
	this.scene1=scene1;
	
	//--------------------------------------------
    //gridpane du haut pour le bouton accueil qui permet de revenir à la premiere scene 
    
    GridPane TopPane = new GridPane();
	TopPane.setPrefHeight(50);
	// commande pour appliquer le dégradé à la gridpane
	LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
			 new Stop(0, Color.web("#0072C6")),
		        new Stop(0.5, Color.web("#00BFFF")),
		        new Stop(1, Color.web("#0072C6")));
	TopPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));
	
	ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/ressources/LogoIsika.png")));
	logo.setFitWidth(30);
	logo.setFitHeight(50);
	TopPane.add(logo, 6, 0, 2, 1);
	TopPane.setAlignment(Pos.TOP_RIGHT);
	
    Button btnAccueil = new Button("Accueil");
    TopPane.add(btnAccueil, 0, 0, 1, 1);

	btnAccueil.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			monStage.setScene(scene1);
			
	
		}
		
	});
	
	monStage.setScene(this.getScene());
	
	
	this.setTop(TopPane);
    
	
}
}
