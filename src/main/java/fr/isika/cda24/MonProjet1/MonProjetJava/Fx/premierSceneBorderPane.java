package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

import java.util.List;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import fr.isika.cda24.MonProjet1.MonProjetJava.Fx.troisiemeSceneTable;

public class premierSceneBorderPane extends BorderPane {
	
	

public Stage monStage;
public Scene scene2;
public Scene scene3;
public Scene scene4;
String blue = "#0077be";
public Scene scene5;
private Button actionBtn;
private List<Stagiaire> stagiaires;

public premierSceneBorderPane() {
	
	
	troisiemeSceneTable table = new troisiemeSceneTable(stagiaires, monStage, scene2, scene2);
	
	
	

	BorderStrokeStyle borderStrokeStyle = BorderStrokeStyle.SOLID;
	CornerRadii cornerRadii = CornerRadii.EMPTY;
	BorderWidths borderWidths = BorderWidths.DEFAULT;
	Color borderColor = Color.web("#ADD8E6");
	BackgroundFill backgroundFill = new BackgroundFill(
	        new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	                new Stop(0, Color.web("#ADD8E6")),
	                new Stop(1, Color.web("#87CEFA"))),
	        null, null);

//----------------------------------------------------------------------------------
	// Accueil pour tout les utilisateurs
	GridPane centerPane = new GridPane();
	centerPane.setPrefSize(300, 400);
	centerPane.setStyle("-fx-background-color: #F0F8FF");

	// Commande pour ajouter le logo
	ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/ressources/LogoIsika1.png")));
	logo.setFitWidth(300);
	logo.setFitHeight(200);
	centerPane.add(logo, 2, 2, 1, 1);

	// Bouton entrer pour un accès restreint sans identifiants
	Button entrer = new Button("Entrer");
	entrer.setPrefSize(100, 40);
	centerPane.add(entrer, 2, 3, 1, 1);

	entrer.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
	        System.out.println(monStage);
	        System.out.println(scene2);
	        // TODO Auto-generated method stub
	        monStage.setScene(scene2);
	    }
	});
	
	centerPane.setVgap(15);
	centerPane.setHgap(15);
	centerPane.setAlignment(Pos.CENTER);

	centerPane.setBorder(new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths)));
	this.setCenter(centerPane);

//------------------------------------------------------------------------------------------
	// En-tête
	HBox topPane = new HBox();
	topPane.setPrefHeight(50);
	LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
			 new Stop(0, Color.web("#0072C6")),
		        new Stop(0.5, Color.web("#00BFFF")),
		        new Stop(1, Color.web("#0072C6")));
	topPane.setBackground(new Background(new BackgroundFill(gradient, null, null)));

	Label titleLabel = new Label("Accueil");
	titleLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
	topPane.getChildren().add(titleLabel);
	HBox.setMargin(titleLabel, new Insets(10, 0, 0, 10));

	topPane.setBorder(new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths)));
	this.setTop(topPane);

//-----------------------------------------------------------------------------------------------

	// Accès Admin
	GridPane rightPane = new GridPane();
	rightPane.setPrefSize(400, 600);
	rightPane.setStyle("-fx-background-color: #F0F8FF");

	Label adminLabel = new Label("Accès Admin");
	adminLabel.setStyle("-fx-font-size: 24; -fx-text-fill: " + blue + ";");
	rightPane.add(adminLabel, 1, 0, 3, 1);

	Label numUtilisatLabel = new Label ("Identifiant");
	numUtilisatLabel.setStyle("-fx-text-fill: " + blue + ";");
	rightPane.add(numUtilisatLabel, 0, 1, 1, 1);
	TextField numTxt = new TextField();
	numTxt.setPromptText("Entrez votre identifiant");
	rightPane.add(numTxt, 1, 1, 3, 1);

	Label mdpLabel = new Label ("Mot de Passe");
	mdpLabel.setStyle("-fx-text-fill: " + blue + ";");
	rightPane.add(mdpLabel, 0, 2, 1, 1);	
	PasswordField mdpTxt = new PasswordField();
	mdpTxt.setPromptText("Entrez votre mot de passe");
	rightPane.add(mdpTxt, 1, 2, 3, 1);

	Button valider = new Button ("Valider");
	valider.setPrefSize(100, 40);
	rightPane.add(valider, 2, 4, 1, 1);
	
	String adminNum = "FantaS";
	String adminMdp = "jeSuisAdmin"; // à remplacer par le mot de passe souhaité

	valider.setOnAction(e -> {
	    String numUtilisateur = numTxt.getText();
	    String mdp = mdpTxt.getText();
	    if (mdp.equals(adminMdp) && numUtilisateur.equals(adminNum)) {
	        // Si le mot de passe est correct, afficher les boutons cachés dans la scene 3
		
		monStage.setScene(scene3);
		table.actionBtn.setVisible(true);
		
		
	    	
	    	System.out.println("mot de passe correct");
	    } else {
	        // Si le mot de passe est incorrect, afficher un message d'erreur
	        Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur d'authentification");
	        alert.setHeaderText("Identifiant ou mot de passe incorrect");
	        alert.setContentText("Veuillez vérifier vos identifiants");
	        alert.showAndWait();
	        System.out.println("mot de passe incorrect");
	    }
	});
	
    rightPane.setBorder((new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths))));
    
	rightPane.setVgap(15);
	rightPane.setHgap(15);
	rightPane.setAlignment(Pos.CENTER);
	this.setRight(rightPane);
//------------------------------------------------------------------------------------
	
	
	GridPane bottomPane = new GridPane ();
	bottomPane.setPrefHeight(50);
	LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
			 new Stop(0, Color.web("#0072C6")),
		        new Stop(0.5, Color.web("#00BFFF")),
		        new Stop(1, Color.web("#0072C6")));
	bottomPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

	
//label et bouton pour la documentation 'Besoin d'aide'
	Label lblBesoinAide = new Label ("Besoin d'aide?");
	lblBesoinAide.setStyle("-fx-text-fill: White");
	bottomPane.add(lblBesoinAide, 2, 0, 3, 2);
	Button btnBesoinAide = new Button ("cliquez ici !");
	btnBesoinAide.setPrefHeight(50);
	bottomPane.add(btnBesoinAide, 5, 0, 3, 2);
	
	btnBesoinAide.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println(monStage);
			System.out.println(scene4);
			// TODO Auto-generated method stub
			//monStage = btnBesoinAide.getScene().get;
			monStage.setScene(scene4);
			
		}
	});
	
	bottomPane.setVgap(15);
	bottomPane.setHgap(15);
	bottomPane.setAlignment(Pos.BOTTOM_RIGHT);
	this.setBottom(bottomPane);
	bottomPane.setBorder(new Border(new BorderStroke(borderColor, borderStrokeStyle, cornerRadii, borderWidths)));
	
	
}}

