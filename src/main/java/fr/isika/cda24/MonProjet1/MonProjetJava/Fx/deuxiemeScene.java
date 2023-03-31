package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import java.util.Collection;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
//		
	private Scene scene1;
	private Stage monStage;
	public Scene scene2;
	

public deuxiemeScene(Scene scene1, Stage stage) {
	this.scene1=scene1;
		
	//gridpane pour le formulaire de recherche
	
		GridPane centerPane = new GridPane();
		centerPane.setPrefSize(300, 400);
		centerPane.setStyle("-fx-background-color: #CDB599");
		
		Label lblNom = new Label("Entrez le nom : ");
		txtNom = new TextField();
		txtNom.setText("Nom");
		centerPane.add(lblNom, 0, 1, 2, 1);
		centerPane.add(txtNom, 2,1,2, 1);
		
		// Ligne de saisie du prénom
		Label lblPrenom = new Label("Entrez le prénom : ");
		txtPrenom = new TextField();
		txtPrenom.setText("Prénom");
		centerPane.add(lblPrenom, 0, 2, 2, 1);
		centerPane.add(txtPrenom,2, 2, 2,1);

		// Ligne de saisie du cycle
		Label lblCycle = new Label("Entrez le cycle : ");
		txtCycle = new TextField();
		txtCycle.setText("Cycle");
		centerPane.add(lblCycle, 0, 3, 2, 1);
		centerPane.add(txtCycle, 2, 3,2,1);
        
        Label lblDepart= new Label("Depatrement : ");
        txtDepart = new TextField("Departement");
        centerPane.add(lblDepart, 2,1,2, 4);
        centerPane.add(txtDepart, 2, 4,2,1);
        
        // Ligne de saisie Année de formation
        Label lblAnnee= new Label("entrez l'année :");
        txtAnnee= new TextField();
        txtAnnee.setText("AnneeDeFormation");
        centerPane.add(lblAnnee,2,1, 2, 5);
        centerPane.add(txtAnnee, 2, 5,2,1);
        
        this.setCenter(centerPane);
        
 //--------------------------------------------
        //gridpane du haut pour le bouton accueil qui permet de revenir à la premiere scene 
        
        GridPane TopPane = new GridPane();
    	TopPane.setPrefHeight(50);
    	// commande pour appliquer le dégradé à la gridpane
    	LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
    	        new Stop(0, Color.web("#4B0082")),
    	        new Stop(1, Color.web("#8B008B")));
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
		
		this.setTop(TopPane);
        
        //bouton pour la méthode ajouter 
//        btnValider.setOnAction(new EventHandler<ActionEvent>(){
//     	   @Override 
//     	   public void handle (Action event) {
//     		   Stagiaire s = new Stagiaire(txtNom.getText(),
//     				  txtPrenom.getText(), txtCycle.getText(),
//     				  txtAnne.getText(),txtDepart.getText());
   //ajouteer sysout
	}
        }
	
	
