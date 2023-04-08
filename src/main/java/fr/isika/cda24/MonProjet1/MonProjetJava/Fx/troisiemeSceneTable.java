package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Optional;

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
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fr.isika.cda24.MonProjet1.MonProjet1.ArbreBinaireDeRecherche;
import javafx.util.Callback;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class troisiemeSceneTable extends BorderPane {

	public Stage monStage;
	public Scene scene1;
	public Scene scene2;
	public Scene scene3;

	private TextField txtPrenom;
	private TextField txtNom;
	private TextField txtCycle;
	private TextField txtDepart;
	private TextField txtAnnee;
	public Button actionBtn;

	public boolean isAdmin = false;
	public ArbreBinaireDeRecherche abre;
	private TableView<Stagiaire> table = new TableView<Stagiaire>();
	public RandomAccessFile raf;
	GridPane profilPane = new GridPane();

	public TableView<Stagiaire> getTable() {
		return table;
	}

	public void setTable(TableView<Stagiaire> table) {
		this.table = table;
	}

	public troisiemeSceneTable(List<Stagiaire> stagiaires, Stage monStage, Scene scene1, Scene scene2)
			throws IOException {

		this.abre = new ArbreBinaireDeRecherche();
		raf = new RandomAccessFile("src/main/java/annuaireTxt/fBinaireStagiaire.bin", "rw");
		if (raf.length() == 0) {
			abre.ecrireDansFichierBinaire();
		}
		stagiaires = abre.arbreAffichageInfix(raf);

		profilPane.setVisible(false);
		txtNom = new TextField();
		txtPrenom = new TextField();
		txtCycle = new TextField();
		txtDepart = new TextField();
		txtAnnee = new TextField();

		TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
		nomCol.setMinWidth(100);

		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));

		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prénom");
		prenomCol.setMinWidth(100);
		prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Departement");
		departementCol.setMinWidth(150);
		departementCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));

		TableColumn<Stagiaire, String> cycleCol = new TableColumn<Stagiaire, String>("Cycle");
		cycleCol.setMinWidth(100);
		cycleCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("cycle"));

		TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Annee");
		anneeCol.setMinWidth(100);
		anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		TableColumn<Stagiaire, String> actionCol = new TableColumn<Stagiaire, String>("Profil");
		actionCol.setMinWidth(100);
		actionCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("Profil"));

		table.getColumns().addAll(nomCol, prenomCol, departementCol, cycleCol, anneeCol);

		table.setPrefWidth(700);
		table.setPrefHeight(800);

		// Ajout d'un padding pour la table
		table.setPadding(new Insets(10));

		table.setEditable(true);
		table.setStyle("-fx-background-color: white; -fx-border-color: blue; -fx-border-width: 1px; -fx-border-style: solid;");

		// Méthode pour effectuer la recherche avancée
		FilteredList<Stagiaire> donneeFiltre = new FilteredList<>(FXCollections.observableList(stagiaires), p -> true);

		// Ajout d'un listener pour mettre à jour la liste filtrée lors de la saisie
		// d'un filtre
		TextField champsFiltre = new TextField();
		champsFiltre.setPromptText("Recherche avancée...");
		champsFiltre.textProperty().addListener((observable, oldValue, newValue) -> {
			donneeFiltre.setPredicate(stagiaire -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				return stagiaire.getNom().toLowerCase().contains(lowerCaseFilter)
						|| stagiaire.getPrenom().toLowerCase().contains(lowerCaseFilter)
						|| stagiaire.getDepartement().toLowerCase().contains(lowerCaseFilter)
						|| stagiaire.getCycle().toLowerCase().contains(lowerCaseFilter)
						|| stagiaire.getAnnee().toLowerCase().contains(lowerCaseFilter);

			});
		});

		// Ajout de la liste filtrée à la table
		table.setItems(donneeFiltre);

		// Création d'un VBox pour afficher la table et le champ de filtre
		VBox vbox = new VBox();
		vbox.getChildren().addAll(champsFiltre, table);

		this.setLeft(vbox);
		addButtonToTable();

//----------------------------------------------------------------------------------------------------------------------------------------------	
		StackPane TopPane = new StackPane();
		TopPane.setPrefHeight(50);
		LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#0072C6")), new Stop(0.5, Color.web("#00BFFF")),
				new Stop(1, Color.web("#0072C6")));
		TopPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

		Button btnAcc = new Button("Accueil");
		Button btnRetour = new Button("Retour");

		TopPane.getChildren().addAll(btnAcc, btnRetour);

		this.setTop(TopPane);

		btnAcc.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				monStage.setScene(scene1);
				;
			}
		});

		btnRetour.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				monStage.setScene(scene2);
			}
		});

	}

	// ----------------------------------------------------------------------------------------------------------------------------------------------
	
	private void addButtonToTable() {
		TableColumn<Stagiaire, String> actionCol = new TableColumn("Profil");

		Callback<TableColumn<Stagiaire, String>, TableCell<Stagiaire, String>> cellFactory = new Callback<TableColumn<Stagiaire, String>, TableCell<Stagiaire, String>>() {
			@Override
			public TableCell<Stagiaire, String> call(final TableColumn<Stagiaire, String> param) {
				final TableCell<Stagiaire, String> cell = new TableCell<Stagiaire, String>() {

					public Button actionBtn = new Button("Afficher le profil");
					{
						actionBtn.setVisible(isAdmin);
						actionBtn.setOnAction((ActionEvent event) -> {
							Stagiaire stagiaire = getTableView().getItems().get(getIndex());
							System.out.println(stagiaire);

							profilPane.setVisible(true);

							txtNom.setText(stagiaire.getNom());
							txtPrenom.setText(stagiaire.getPrenom());
							txtCycle.setText(stagiaire.getCycle());
							txtDepart.setText(stagiaire.getDepartement());
							txtAnnee.setText(stagiaire.getAnnee());

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



//------------------------------------------------------------------------------------------------------------------------------	
		profilPane.setPrefSize(500, 600);
		profilPane.setStyle("-fx-background-color: #F0F8FF");

		ImageView profil = new ImageView(new Image(getClass().getResourceAsStream("/ressources/PhotoProfil.png")));
		profil.setFitWidth(120);
		profil.setFitHeight(120);
		profilPane.add(profil, 1, 0, 2, 1);

		Label formLabel = new Label("Informations du Stagiaire");
		formLabel.setStyle("-fx-font-size: 20; -fx-text-fill: #000080;");
		profilPane.add(formLabel, 0, 1, 3, 1);

		Label lblNom = new Label("Nom");
		profilPane.add(lblNom, 0, 2);
		profilPane.add(txtNom, 1, 2);

		Label lblPrenom = new Label("Prénom");
		profilPane.add(lblPrenom, 0, 3);
		profilPane.add(txtPrenom, 1, 3);

		Label lblCycle = new Label("Cycle");
		profilPane.add(lblCycle, 0, 4);
		profilPane.add(txtCycle, 1, 4);

		Label lblDepart = new Label("Département");
		profilPane.add(lblDepart, 0, 5);
		profilPane.add(txtDepart, 1, 5);

		Label lblAnnee = new Label("Année");
		profilPane.add(lblAnnee, 0, 6);
		profilPane.add(txtAnnee, 1, 6);

		VBox buttonsBox = new VBox(10);
		buttonsBox.setAlignment(Pos.CENTER_RIGHT);

		Button btnEditer = new Button("Mettre à jour");
		btnEditer.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
		btnEditer.setPrefWidth(150);

		Button btnSupprimer = new Button("Supprimer");
		btnSupprimer.setStyle("-fx-background-color: red; -fx-text-fill: black;");
		btnSupprimer.setPrefWidth(150);

		Button btnAnnuler = new Button("Annuler");
		btnAnnuler.setStyle("-fx-background-color: cyan; -fx-text-fill: black;");
		btnAnnuler.setPrefWidth(150);

		buttonsBox.getChildren().addAll(btnEditer, btnSupprimer, btnAnnuler);
		profilPane.add(buttonsBox, 0, 7, 2, 1);

		btnAnnuler.setOnAction((ActionEvent event1) -> {
			profilPane.setVisible(false);
		});

		btnSupprimer.setOnAction((ActionEvent event1) -> {

			Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
			confirmAlert.setTitle("Confirmation de la suppression");
			confirmAlert.setHeaderText(null);
			confirmAlert.setContentText("Êtes-vous sûr de vouloir supprimer ce stagiaire ?");

			Optional<ButtonType> result = confirmAlert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				// Suppression du stagiaire sélectionné
				// insérer ici la méthode supprimer : maListe.remove(stagiaire);
			}

		});

		profilPane.setVgap(15);
		profilPane.setHgap(15);
		profilPane.setAlignment(Pos.CENTER);

		profilPane.setStyle("-fx-border-color: blue; -fx-border-width: 1px; -fx-border-style: solid");
		this.setRight(profilPane);

		this.setRight(profilPane);
		this.getRight().setVisible(false);
	
	//----------------------------------------------------------------------------------------------------------------------------
			StackPane bottomPane = new StackPane();
			bottomPane.setPrefHeight(50);
			LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
					new Stop(0, Color.web("#0072C6")), new Stop(0.5, Color.web("#00BFFF")),
					new Stop(1, Color.web("#0072C6")));
			bottomPane.setBackground(new Background(new BackgroundFill(gradient1, null, null)));

			Button btnImprime = new Button("Imprimer");
			bottomPane.getChildren().add(btnImprime);

			btnImprime.setOnAction(e -> {
				PrinterJob job = PrinterJob.createPrinterJob();
				if (job != null) {
					job.showPrintDialog(monStage);
					job.printPage(table);
					job.endJob();
				}

			});
			this.setBottom(bottomPane);
}
	}
