package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

public class deuxiemeScene extends BorderPane {

	private TextField txtPrenom;
	private TextField txtNom;
	private TextField txtCycle;
	private TextField txtDepart; 
	private TextField txtAnnee;
	
//public ArrayList<Stagiaire> stagiaires;
//	
//	public deuxiemeScene(ArrayList<Stagiaire> stagiaires) {
//		
//		this.stagiaires = stagiaires;
		
	private Scene scene1;
	public Stage monStage;
	public Scene scene2;
	public Scene scene4;
	public Scene scene3;
	
	
	String blue = "#0077be";
	
	

public deuxiemeScene(Scene scene1, Stage monStage) {
	this.scene1=scene1;
		

	BorderStrokeStyle borderStrokeStyle = BorderStrokeStyle.SOLID;
	CornerRadii cornerRadii = CornerRadii.EMPTY;
	BorderWidths borderWidths = BorderWidths.DEFAULT;
	Color borderColor = Color.web("#ADD8E6");
	BackgroundFill backgroundFill = new BackgroundFill(
	        new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	                new Stop(0, Color.web("#ADD8E6")),
	                new Stop(1, Color.web("#87CEFA"))),
	        null, null);

	//gridpane pour le formulaire de recherche
	
		GridPane centerPane = new GridPane();
		centerPane.setPrefSize(300, 400);
		centerPane.setStyle("-fx-background-color: #F0F8FF");
		
		ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/ressources/LogoIsika1.png")));
		logo.setFitWidth(300);
		logo.setFitHeight(200);
		centerPane.add(logo, 2, 0, 1, 1);
		
		Label formLabel = new Label("Formulaire");
		formLabel.setStyle("-fx-font-size: 32; -fx-text-fill: #000080;");
		centerPane.add(formLabel, 2, 1, 4, 1);
	
			
		Label lblNom = new Label("Entrez le nom : ");
		txtNom = new TextField();
		txtNom.setText("Nom");
		centerPane.add(lblNom, 0, 2, 2, 1);
		centerPane.add(txtNom, 2,2,2, 1);
		
		// Ligne de saisie du prénom
		Label lblPrenom = new Label("Entrez le prénom : ");
		txtPrenom = new TextField();
		txtPrenom.setText("Prénom");
		centerPane.add(lblPrenom, 0, 3, 2, 1);
		centerPane.add(txtPrenom,2, 3, 2,1);

		// Ligne de saisie du cycle
		Label lblCycle = new Label("Entrez le cycle : ");
		txtCycle = new TextField();
		txtCycle.setText("Cycle");
		centerPane.add(lblCycle, 0, 4, 2, 1);
		centerPane.add(txtCycle, 2, 4,2,1);
        
        Label lblDepart= new Label("Depatrement : ");
        txtDepart = new TextField("Departement");
        centerPane.add(lblDepart, 0,5,2, 1);
        centerPane.add(txtDepart, 2, 5,2,1);
        
        // Ligne de saisie Année de formation
        Label lblAnnee= new Label("Entrez l'année :");
        txtAnnee= new TextField();
        txtAnnee.setText("Annee de Formation");
        centerPane.add(lblAnnee,0,6, 2, 1);
        centerPane.add(txtAnnee, 2, 6,2,1);
//        
//        Button btnAfficher = new Button("Afficher");
//        centerPane.add(btnAfficher, 2, 7);
//        btnAfficher.setMaxSize(100, 300);
//        
//        Button btnAjouter = new Button("Ajouter");
//        centerPane.add(btnAjouter, 3, 7);
//        btnAjouter.setMaxSize(100, 300);
        
        GridPane buttonPane = new GridPane();
        buttonPane.setHgap(10);
        Button btnAfficher = new Button("Afficher");
        btnAfficher.setMaxSize(100, 300);
        btnAfficher.setStyle("-fx-background-color: #007FFF; -fx-text-fill: white;");

        Button btnAjouter = new Button("Ajouter");
        btnAjouter.setMaxSize(100, 300);
        btnAjouter.setStyle("-fx-background-color: green; -fx-text-fill: white;");

        buttonPane.add(btnAfficher, 0, 0);
        buttonPane.add(btnAjouter, 1, 0);

        centerPane.add(buttonPane, 2, 8, 2, 1);

		btnAfficher.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.out.println(monStage);
				System.out.println(scene3);
				// TODO Auto-generated method stub
				//monStage = btnBesoinAide.getScene().get;
				monStage.setScene(scene3);
		
			}
			
		});
		
		btnAjouter.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
			     		   Stagiaire s = new Stagiaire(txtNom.getText(),
			     				  txtPrenom.getText(), txtCycle.getText(),
			     				  txtAnnee.getText(),txtDepart.getText());

		
			}
			
		});
		
		
		
		monStage.setScene(this.getScene());
		
        
        
		centerPane.setVgap(15);
		centerPane.setHgap(15);
		centerPane.setAlignment(Pos.CENTER);

		centerPane.setBorder(new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths)));
		this.setCenter(centerPane);

        
        
        
        
 //--------------------------------------------
        //gridpane du haut pour le bouton accueil qui permet de revenir à la premiere scene 
        
        StackPane topPane = new StackPane();
    	topPane.setPrefHeight(50);
    	
		// commande pour appliquer le dégradé à la gridpane
    	LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
    			 new Stop(0, Color.web("#0072C6")),
    		        new Stop(0.5, Color.web("#00BFFF")),
    		        new Stop(1, Color.web("#0072C6")));
    	topPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

 
    	
    	
        Button btnAccueil = new Button("Accueil");
        topPane.getChildren().add(btnAccueil);

		btnAccueil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				monStage.setScene(scene1);
				
		
			}
			
		});
		
		GridPane bottomPane = new GridPane ();
		bottomPane.setPrefHeight(50);
		LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
				 new Stop(0, Color.web("#0072C6")),
			        new Stop(0.5, Color.web("#00BFFF")),
			        new Stop(1, Color.web("#0072C6")));
		bottomPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

		
		monStage.setScene(this.getScene());
		
		topPane.setBorder(new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths)));
		this.setTop(topPane);

}
		


		




}
        
	
	
