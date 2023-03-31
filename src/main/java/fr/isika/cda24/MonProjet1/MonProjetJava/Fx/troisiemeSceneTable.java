package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import java.util.List;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.scene.layout.BorderPane;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
;


public class troisiemeSceneTable extends BorderPane {
	
	//public troisiemeSceneTable(List<Stagiaire> stagiaiares) {
	
	
		
		public troisiemeSceneTable(List<Stagiaire> stagiaires) {
			TableView<Stagiaire> table = new TableView<Stagiaire>();
			table.setEditable(true);
			
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
			
			TableColumn<Stagiaire,String> anneCol = new TableColumn<Stagiaire,String>("Annee");
			anneCol.setMinWidth(100);
			anneCol.setCellValueFactory(
					new PropertyValueFactory<Stagiaire,String>("annee"));
			
			table.getColumns().addAll(nomCol,prenomCol,departementCol,cycleCol,anneCol);
			table.setItems(FXCollections.observableList(stagiaires));
	        this.setCenter(table);
		}
	
		
	}
	
	


