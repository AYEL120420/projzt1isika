package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import java.util.ArrayList;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.Node;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;


public class ProfilStagiaire extends BorderPane {
String blue = "#0077be";
	
	
	public Stage monStage;
	public Scene scene1;
	public Scene scene2;
	public Scene scene3;
	public Scene scene4;
	Node profil;
	

	public ProfilStagiaire(Stagiaire stagiaires) {
		// TODO Auto-generated constructor stub
	}

	public ProfilStagiaire(ArrayList<Stagiaire> stagiaires, Stage monStage, Scene scene1, Scene scene3) {
	
		this.scene1=scene1;
		
		AnchorPane centerPane = new AnchorPane();
		
		ImageView profil = new ImageView(new Image(getClass().getResourceAsStream("/ressources/White.png")));
		profil.setFitWidth(300);
		profil.setFitHeight(300);
		AnchorPane.setTopAnchor(profil, 10.0);
		AnchorPane.setRightAnchor(profil, 10.0);

		centerPane.getChildren().add(profil);
		centerPane.setStyle("-fx-background-color: #F0F8FF");
		
		Button btnSupprimer = new Button("Supprimer");
		btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: black;");
		btnSupprimer.setPrefWidth(150);
		AnchorPane.setLeftAnchor(btnSupprimer, 30.0);
		AnchorPane.setBottomAnchor(btnSupprimer, 30.0);

		Button btnEditer = new Button("Editer");
		btnEditer.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
		btnEditer.setPrefWidth(150);
		AnchorPane.setRightAnchor(btnEditer, 30.0);
		AnchorPane.setBottomAnchor(btnEditer, 30.0);

		centerPane.getChildren().addAll(btnSupprimer, btnEditer);
		
		
		
		

//		Button btnSupprimer = new Button("Supprimer");
//		btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: white;");
//		btnSupprimer.setPrefWidth(200);
//
//		AnchorPane.setBottomAnchor(btnSupprimer, 20.0);
//		
//		Button btnEditer = new Button("Editer");
//		btnSupprimer.setStyle("-fx-background-color: yellow; -fx-text-fill: white;");
//		btnSupprimer.setPrefWidth(200);
//
//		AnchorPane.setBottomAnchor(btnEditer, 20.0);
//
//		centerPane.getChildren().addAll(btnSupprimer, btnEditer);
//		
		
		
		this.setCenter(centerPane);

		//-----------------------------------------------------------------
		
		StackPane TopPane = new StackPane();
		TopPane.setPrefHeight(50);
		LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
				 new Stop(0, Color.web("#0072C6")),
			        new Stop(0.5, Color.web("#00BFFF")),
			        new Stop(1, Color.web("#0072C6")));
		TopPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

		Button btnAcc = new Button("Accueil");				
		Button btnRetour = new Button("Retour");	
		
		
		 TopPane.getChildren().addAll(btnAcc, btnRetour);
		
		this.setTop(TopPane);

	
	btnAcc.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
	  
	        monStage.setScene(scene1);;
	    }
	});

	btnRetour.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			monStage.setScene(scene3);
		}
		});
	

	
//---------------------------------
	 StackPane bottomPane = new StackPane ();
		bottomPane.setPrefHeight(50);
		LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
				 new Stop(0, Color.web("#0072C6")),
			        new Stop(0.5, Color.web("#00BFFF")),
			        new Stop(1, Color.web("#0072C6")));
		bottomPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));
		
		
		Button btnImprime = new Button("Imprimer");
		bottomPane.getChildren().add(btnImprime);

		btnImprime.setOnAction(e -> {
		PrinterJob job = PrinterJob.createPrinterJob();
		if(job != null) {
			job.showPrintDialog(monStage);
			job.printPage(profil);
			job.endJob();		
		}
		
		
		});
		this.setBottom(bottomPane);

}
}
