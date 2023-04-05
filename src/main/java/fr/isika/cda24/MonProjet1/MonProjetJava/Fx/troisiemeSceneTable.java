package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import java.util.List;
import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fr.isika.cda24.MonProjet1.MonProjet1.Annuaire;
import javafx.util.Callback;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.ScrollPane;

public class troisiemeSceneTable extends BorderPane {
	String blue = "#0077be";
	
	
	public Stage monStage;
	public Scene scene1;
	public Scene scene2;
	public Scene scene3;
	public Scene scene4;
	public Scene scene5;
	public Button actionBtn;
	
	private TableView<Stagiaire> table = new TableView<Stagiaire>();
	
	public troisiemeSceneTable(List<Stagiaire> stagiaires, Stage monStage, Scene scene1, Scene scene2) {
		
		Annuaire annuaire = new Annuaire();
		stagiaires = annuaire.lireFichierTxt();
		
		StackPane centerPane = new StackPane();
		

		
		TableColumn<Stagiaire,String> nomCol = new TableColumn<Stagiaire,String>("Nom");
		nomCol.setMinWidth(100);
		
		nomCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("nom"));
		
		TableColumn<Stagiaire,String> prenomCol = new TableColumn<Stagiaire,String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("prenom"));
		
		TableColumn<Stagiaire,String> departementCol = new TableColumn<Stagiaire,String>("Departement");
		departementCol.setMinWidth(150);
		departementCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("departement"));
		
		TableColumn<Stagiaire,String> cycleCol = new TableColumn<Stagiaire,String>("Cycle");
		cycleCol.setMinWidth(100);
		cycleCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("cycle"));
		
		TableColumn<Stagiaire,String> anneeCol = new TableColumn<Stagiaire,String>("Annee");
		anneeCol.setMinWidth(100);
		anneeCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("annee"));
		
		TableColumn<Stagiaire,String> actionCol = new TableColumn<Stagiaire,String>("Profil");
		actionCol.setMinWidth(100);
		actionCol.setCellValueFactory(
				new PropertyValueFactory<Stagiaire,String>("Profil"));
		
		table.getColumns().addAll(nomCol,prenomCol,departementCol,cycleCol,anneeCol);

//		// Redimensionnement automatique de la TableView lorsque la fenêtre est redimensionnée
//		table.prefWidthProperty().bind(centerPane.widthProperty());
//		table.prefHeightProperty().bind(centerPane.heightProperty());

		table.setPrefWidth(900);
		table.setPrefHeight(600);
		
		// Ajout d'un padding pour la table
		table.setPadding(new Insets(10));


		table.setEditable(true);
		table.setStyle("-fx-background-color: white; -fx-border-color: blue; -fx-border-width: 1px; -fx-border-style: solid;");
		
		//Méthode pour effectuer la recherche avancée 
		FilteredList<Stagiaire> donneeFiltre = new FilteredList<>(FXCollections.observableList(stagiaires), p -> true);

	    // Ajout d'un listener pour mettre à jour la liste filtrée lors de la saisie d'un filtre
	    TextField champsFiltre = new TextField();
	    champsFiltre.setPromptText("Recherche avancée...");
	    champsFiltre.textProperty().addListener((observable, oldValue, newValue) -> {
	        donneeFiltre.setPredicate(stagiaire -> {
	            if (newValue == null || newValue.isEmpty()) {
	                return true;
	            }
	            String lowerCaseFilter = newValue.toLowerCase();
	            return stagiaire.getNom().toLowerCase().contains(lowerCaseFilter) || 
	            		stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter) ||
	            	 stagiaire.getDepartement().toLowerCase().contains(lowerCaseFilter) ||
	                 stagiaire.getCycle().toLowerCase().contains(lowerCaseFilter) ||
	                 stagiaire.getAnnee().toLowerCase().contains(lowerCaseFilter);
	            	
	            
	        
	    });
	    });
	    
	    

	    // Ajout de la liste filtrée à la table
	    table.setItems(donneeFiltre);

	    // Création d'un VBox pour afficher la table et le champ de filtre
	    VBox vbox = new VBox();
	    vbox.getChildren().addAll(champsFiltre, table);

	    this.setCenter(vbox);
	    addButtonToTable();
	

//------------------------------------------------------------------------------------------	
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
			monStage.setScene(scene2);
		}
		});
	
}

//---------------------------------------------------------------------------------------
	 private void addButtonToTable() {
         TableColumn<Stagiaire, String> actionCol = new TableColumn("Profil");

         Callback<TableColumn<Stagiaire, String>, TableCell<Stagiaire, String>> cellFactory = new Callback<TableColumn<Stagiaire, String>, TableCell<Stagiaire, String>>() {
             @Override
             public TableCell<Stagiaire, String> call(final TableColumn<Stagiaire, String> param) {
                 final TableCell<Stagiaire, String> cell = new TableCell<Stagiaire, String>() {

                     private final Button actionBtn = new Button("Afficher le profil");

                     {
                         actionBtn.setOnAction((ActionEvent event) -> {
                             Stagiaire stagiaire = getTableView().getItems().get(getIndex());
                             System.out.println("selectedData: " + stagiaire);
                             
                             actionBtn.setVisible(false);

                            	    monStage.setScene(scene5);
                            	});
                        
    
                     }

                     @Override
                     public void updateItem(String item, boolean empty) {
                         super.updateItem(item, empty);
                         if (empty) {
                             setGraphic(null);
                         } else {
                             setGraphic(actionBtn);
                         }
                     }
                 };
                 return cell;
             }
         };

         actionCol.setCellFactory(cellFactory);
         table.getColumns().add(actionCol);

     
        
//-----------------------------------------------------------------
	 StackPane bottomPane = new StackPane ();
		bottomPane.setPrefHeight(50);
		LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
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
			job.printPage(table);
			job.endJob();		
		}
		
		
		});
		this.setBottom(bottomPane);

	 }
}




