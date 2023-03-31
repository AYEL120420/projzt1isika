package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
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

public class premierSceneBorderPane extends BorderPane {
	
	

public Stage monStage;
public Scene scene2;

public premierSceneBorderPane() {
	

	
	//Acces pour tout les utilisateurs
	GridPane centerPane = new GridPane();
	centerPane.setPrefSize(300, 400);
	centerPane.setStyle("-fx-background-color: #CDB599");

	// commande pour ajouter le logo
	ImageView logo = new ImageView(new Image("file:///C:/Users/mouni/Projet1.isika/MonProjet1/src/main/java/annuaireTxt/Logo.png"));
	centerPane.add(logo, 5, 4, 2, 1);
	logo.setFitWidth(100);
	logo.setFitHeight(100);
	
	//bouton entrer pour un accès restreint sans identifiants
	Button entrer = new Button("Entrer");
	entrer.setStyle("-fx-background-color:White ; -fx-text-fill: #4B0082; -fx-font-size: 16;");
	entrer.setPrefSize(100, 40);
	centerPane.add(entrer, 5, 5, 2, 1);
	
	entrer.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			System.out.println(monStage);
			System.out.println(scene2);
			// TODO Auto-generated method stub
			monStage.setScene(scene2);
			
		}
	});

	this.setCenter(centerPane);
	centerPane.setBorder(new Border(new BorderStroke(Color.BEIGE,
	        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//--------------------------------------------------------
	
	
	HBox topPane = new HBox();
	topPane.setPrefHeight(50);
	// commande pour appliquer le dégradé à la HBox
	LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	        new Stop(0, Color.web("#4B0082")),
	        new Stop(1, Color.web("#8B008B")));
	topPane.setBackground(new Background(new BackgroundFill(gradient, null, null)));
	
	Label titleLabel = new Label("Accueil");
	titleLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
	topPane.getChildren().add(titleLabel);
	HBox.setMargin(titleLabel, new Insets(10, 0, 0, 10));
	this.setTop(topPane);
	topPane.setBorder(new Border(new BorderStroke(Color.BEIGE,
	        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
//-------------------------------------------------
	
	
	
	GridPane rightPane = new GridPane();
	rightPane.setPrefSize(300, 400);
	rightPane.setStyle("-fx-background-Color: #F5F5F5");
	Label adminLabel = new Label("Accès Admin");
	adminLabel.setStyle("-fx-font-size: 24; -fx-text-fill: #4B0082;");
	rightPane.add(adminLabel, 1, 0, 3, 1);
	rightPane.setBorder(new Border(new BorderStroke(Color.BEIGE,
	        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

	Label numUtilisatLabel = new Label ("Numéro Utilisateur");
	numUtilisatLabel.setStyle("-fx-text-fill: #4B0082;");
	rightPane.add(numUtilisatLabel, 0, 1, 1, 1);
	TextField numTxt = new TextField();
	numTxt.setPromptText("Entrez votre numéro personnel");
	rightPane.add(numTxt, 1, 1, 3, 1);

	Label mdpLabel = new Label ("Mot de Passe");
	mdpLabel.setStyle("-fx-text-fill: #4B0082;");
	rightPane.add(mdpLabel, 0, 2, 1, 1);
	PasswordField mdpTxt = new PasswordField();
	mdpTxt.setPromptText("Entrez votre mot de passe");
	rightPane.add(mdpTxt, 1, 2, 3, 1);

	Button valider = new Button ("Valider");
	valider.setStyle("-fx-background-color: #4B0082; -fx-text-fill: white; -fx-font-size: 16;");
	valider.setPrefSize(100, 40);
	rightPane.add(valider, 2, 4, 1, 1);
//	valider.setOnAction(e -> {
//	    ((deuxiemeScene) App.getPrimaryStage()).setScene(scene2.getScene());
//	});
//	
	this.setCenter(centerPane);
	centerPane.setBorder(new Border(new BorderStroke(Color.BEIGE,
	        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	rightPane.setVgap(15);
	rightPane.setHgap(15);
	rightPane.setAlignment(Pos.CENTER);
	this.setRight(rightPane);
//-------------------------------------------------
	
	
	GridPane bottomPane = new GridPane ();
	bottomPane.setPrefHeight(50);
	LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
	        new Stop(0, Color.web("#4B0082")),
	        new Stop(1, Color.web("#8B008B")));
	bottomPane.setBackground(new Background(new BackgroundFill(gradient, null, null)));
	
//label et bouton pour la documentation 'Besoin d'aide'
	Label lblBesoinAide = new Label ("Besoin d'aide?");
	lblBesoinAide.setStyle("-fx-text-fill: #4B0082;");
	bottomPane.add(lblBesoinAide, 4, 0, 2, 1);
	Button btnBesoinAide = new Button ("clickez ici !");
	btnBesoinAide.setStyle("-fx-background-color: #4B0082; -fx-text-fill: white; -fx-font-size: 16;");
	btnBesoinAide.setPrefSize(60, 40);
	bottomPane.add(btnBesoinAide, 6, 0, 1, 1);
	
//	btnBesoinAide.setOnAction(new EventHandler<ActionEvent>() {
//
//		@Override
//		public void handle(ActionEvent event) {
//			System.out.println(monStage);
//			System.out.println(scene2);
//			// TODO Auto-generated method stub
//			//monStage = btnBesoinAide.getScene().get;
//			monStage.setScene(scene2);
//			
//		}
//	});

	this.setBottom(bottomPane);
	bottomPane.setBorder(new Border(new BorderStroke(Color.BEIGE,
	        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	
	
}}

