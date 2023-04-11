package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TextArea;



public class documentation extends BorderPane {
	
	private Scene scene1;
	private Stage monStage;
	
	
	BorderStrokeStyle borderStrokeStyle = BorderStrokeStyle.SOLID;
	CornerRadii cornerRadii = CornerRadii.EMPTY;
	BorderWidths borderWidths = BorderWidths.DEFAULT;
	Color borderColor = Color.web("#ADD8E6");
	
	

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
    
	

//-------------------------------------------------------------------

FlowPane centerPane = new FlowPane();


TextArea texteLong = new TextArea("1. Introduction\r\n"
		+ "Objectif de l'application\r\n"
		+ "Fonctionnalités principales\r\n"
		+ "Configuration requise\r\n"
		+ "2. Installation et configuration\r\n"
		+ "Installation de l'application\r\n"
		+ "Configuration des paramètres\r\n"
		+ "Ajout de la base de données\r\n"
		+ "3. Utilisation de l'application\r\n"
		+ "Affichage de la liste des stagiaires\r\n"
		+ "Ajout d'un stagiaire\r\n"
		+ "Recherche d'un stagiaire\r\n"
		+ "Impression de la liste des stagiaires\r\n"
		+ "4. Fonctionnalités spécifiques de l'administrateur\r\n"
		+ "Suppression d'un stagiaire\r\n"
		+ "Mise à jour d'un stagiaire\r\n"
		+ "Gestion des comptes utilisateurs\r\n"
		+ "5. Dépannage et support\r\n"
		+ "Problèmes courants\r\n"
		+ "Ressources de support\r\n"
		+ "6. Conclusion\r\n"
		+ "Résumé des fonctionnalités\r\n"
		+ "Remerciements " +
	    "\r\n"
	    + "1. Introduction : L'application de gestion d'annuaire permet de gérer facilement les informations des stagiaires, de les rechercher rapidement, de les ajouter à la liste et d'imprimer la liste."
	    + " L'application peut être utilisée sur n'importe quel ordinateur répondant aux exigences minimales.\r\n"
	    + "2. Installation et configuration : L'installation de l'application est simple et ne nécessite pas de connaissances informatiques avancées. "
	    + "Les paramètres peuvent être configurés pour s'adapter à vos besoins spécifiques, comme la langue de l'application ou la taille de la police. "
	    + "L'application nécessite également l'ajout d'une base de données pour stocker les informations des stagiaires.\r\n"
	    + "3. Utilisation de l'application : L'application permet d'afficher une liste complète de tous les stagiaires enregistrés dans l'annuaire."
	    + " Vous pouvez également ajouter de nouveaux stagiaires, rechercher un stagiaire spécifique en utilisant des critères de recherche, et imprimer la liste des stagiaires.\r\n"
	    + "4. Fonctionnalités spécifiques de l'administrateur : Les administrateurs ont la possibilité de supprimer un stagiaire de la liste ou de mettre à jour les informations de celui-ci. "
	    + "Ils peuvent également gérer les comptes utilisateurs et accorder des autorisations spéciales à certains utilisateurs.\r\n"
	    + "5. Dépannage et support : En cas de problèmes avec l'application, nous recommandons de vérifier les FAQ et la documentation d'aide pour résoudre les problèmes courants. "
	    + "Si vous avez besoin d'une assistance supplémentaire, notre équipe de support est disponible pour vous aider.\r\n"
	    + "6. Conclusion : L'application de bureau pour la gestion de l'annuaire des stagiaires d'un centre de formation est une solution simple et efficace pour gérer les informations des stagiaires. "
	    + "Nous espérons que cette application répondra à vos besoins et vous aidera dans votre travail quotidien. Si vous avez des commentaires ou des suggestions, n'hésitez pas à nous contacter.");

	// Définir la taille du TextArea
	texteLong.setPrefSize(1200, 700);

	// Ajouter le TextArea au FlowPane
	centerPane.getChildren().add(texteLong);
	
	this.setCenter(centerPane);


//-------------------------------------------------------------------------

StackPane bottomPane = new StackPane();
bottomPane.setPrefHeight(50);
LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
		new Stop(0, Color.web("#0072C6")), new Stop(0.5, Color.web("#00BFFF")),
		new Stop(1, Color.web("#0072C6")));
bottomPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

Button btnImprime = new Button("Imprimer");
bottomPane.getChildren().add(btnImprime);

btnImprime.setOnAction(e -> {
	PrinterJob job = PrinterJob.createPrinterJob();
	if (job != null) {
		job.showPrintDialog(monStage);
		job.printPage(centerPane);
		job.endJob();
	}

});
this.setBottom(bottomPane);
}


}

