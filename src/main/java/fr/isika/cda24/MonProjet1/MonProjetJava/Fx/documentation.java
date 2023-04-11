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


TextArea texteLong = new TextArea("Introduction \r\n"
		+ " \r\n"
		+ " L'application de gestion d'annuaire permet de gérer facilement les informations des stagiaires, \r\n"
		+ "de les rechercher rapidement, de les ajouter à la liste et d'imprimer la liste. L'application peut \r\n"
		+ "être utilisée sur  ordinateur  ou sur un smartphone répondant aux exigences minimales \r\n"
		+ " \r\n"
		+ "Objectif de l'application est de centraliser toutes les informations relatives aux stagiaires et \r\n"
		+ "facilitant ainsi leur suivi tout au long de leur parcours de formation. \r\n"
		+ " \r\n"
		+ "1.  Fonctionnalités \r\n"
		+ " Fonctionnalités principales \r\n"
		+ " \r\n"
		+ "- Affichage de la liste des stagiaires \r\n"
		+ "Cette fonctionnalité permet l’affichage des informations relatives au stagiaires \r\n"
		+ "à savoir : nom ,prénom ,cycle ,département, année de formation. \r\n"
		+ "- Ajout de la liste des stagiaires \r\n"
		+ "Cette fonctionnalité permet l’ajout d’un nouveau stagiaires sur la liste \r\n"
		+ "- Recherche d’un stagiaire \r\n"
		+ "Cette fonctionnalité permet de faire la recherche d’un stagiaire. Cette recherche \r\n"
		+ "peut se faire soit par nom, soit par prénom , soit par département, soit par cycle \r\n"
		+ "ou soit par année de formation  \r\n"
		+ "- Impression de la liste des stagiaires  \r\n"
		+ " \r\n"
		+ "Fonctionnalités spécifiques  \r\n"
		+ " \r\n"
		+ "        Cette fonctionnalité est spécifiques qu’a administrateur  \r\n"
		+ "- Suppression d'un stagiaire \r\n"
		+ "La fonctionnalité suppression permet de supprimer un ancien stagiaire qui à \r\n"
		+ "fini son parcours de formation ou un stagiaire qui à arrêter son parcours en \r\n"
		+ "cours d’année. \r\n"
		+ " \r\n"
		+ "     \r\n"
		+ "                      - Mise à jour d'un stagiaire \r\n"
		+ ".La fonctionnalité mise à jour permet d’actualiser les informations sur le site  \r\n"
		+ "Configuration requise \r\n"
		+ ". l’administrateur peut également gérer les comptes utilisateurs et accorder des \r\n"
		+ "autorisations spéciales à certains utilisateurs \r\n"
		+ " \r\n"
		+ "2.  Installation et configuration  \r\n"
		+ " \r\n"
		+ " L'installation de l'application est simple et ne nécessite pas de connaissances \r\n"
		+ "informatiques avancées. Les paramètres peuvent être configurés pour s'adapter à vos \r\n"
		+ "besoins spécifiques, comme la langue de l'application ou la taille de la police. \r\n"
		+ "L'application nécessite également l'ajout d'une base de données pour stocker les \r\n"
		+ "informations des stagiaires. \r\n"
		+ " \r\n"
		+ "3. Utilisation de l'application : \r\n"
		+ " L'application permet d'afficher une liste complète de tous les stagiaires enregistrés \r\n"
		+ "dans l'annuaire. Vous pouvez également ajouter de nouveaux stagiaires, rechercher un \r\n"
		+ "stagiaire spécifique en utilisant des critères de recherche, et imprimer la liste des \r\n"
		+ "stagiaires. \r\n"
		+ " \r\n"
		+ "4.Gestion des comptes utilisateurs \r\n"
		+ " \r\n"
		+ "L’application permet l’accès à tout les utilisateurs en vertu des conditions \r\n"
		+ "d’utilisations et de confidentialité. \r\n"
		+ "L’application permet configure les paramètres de confidentialités de telle sorte qu’un \r\n"
		+ "utilisateur peut voir les informations sur un autres utilisateurs : a savoir : \r\n"
		+ "La photo de profil, le nom, prénom ,département, cycle et année de formation \r\n"
		+ " \r\n"
		+ "5. Notifications \r\n"
		+ " \r\n"
		+ " une alerte de sécurité est activé lorsque , l’utilisateur change d’un appareil à un autre .  \r\n"
		+ " \r\n"
		+ " \r\n"
		+ "6. Dépannage et support  \r\n"
		+ " \r\n"
		+ " En cas de problèmes avec l'application, nous recommandons de vérifier les FAQ et la \r\n"
		+ "documentation d'aide pour résoudre les problèmes courants. Si vous avez besoin d'une \r\n"
		+ "assistance supplémentaire, notre équipe de support est disponible pour vous aider. \r\n"
		+ " \r\n"
		+ "7. Contacter-nous  \r\n"
		+ " \r\n"
		+ " Téléphone : 0033478524031 \r\n"
		+ "                              \r\n"
		+ "  e-mail :seekbyissika.formation@isika.fr \r\n"
		+ " \r\n"
		+ "8. Langue d’application \r\n"
		+ " \r\n"
		+ "• Français \r\n"
		+ "• Anglais \r\n"
		+ " \r\n"
		+ "9. Information sur l’application \r\n"
		+ " \r\n"
		+ "• Seek by isika Version 1.00.0.00 \r\n"
		+ "• Licences 2022-2023 \r\n"
		+ " \r\n"
		+ " \r\n"
		+ "10. resumé  \r\n"
		+ " \r\n"
		+ "L'application de bureau pour la gestion de l'annuaire des stagiaires d'un centre de \r\n"
		+ "formation est une solution simple et efficace pour gérer les informations des stagiaires. \r\n"
		+ "Nous espérons que cette application répondra à vos besoins et vous aidera dans votre \r\n"
		+ "travail quotidien. Si vous avez des commentaires ou des suggestions, n'hésitez pas à \r\n"
		+ "nous contacter. ");

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

