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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;


public class documentation extends BorderPane {
	
	private Scene scene1;
	private Stage monStage;
	
	
	BorderStrokeStyle borderStrokeStyle = BorderStrokeStyle.SOLID;
	CornerRadii cornerRadii = CornerRadii.EMPTY;
	BorderWidths borderWidths = BorderWidths.DEFAULT;
	Color borderColor = Color.web("#ADD8E6");
	BackgroundFill backgroundFill = new BackgroundFill(
	        new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	                new Stop(0, Color.web("#ADD8E6")),
	                new Stop(1, Color.web("#87CEFA"))),
	        null, null);
	

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

FlowPane centerPane = new FlowPane();






}


