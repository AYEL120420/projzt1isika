package fr.isika.cda24.MonProjet1.MonProjet1;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//public class App extends Application {

	
//	public static void main (String[] args) {
//		launch(args);
//		
//	}
//	
//
//	@Override
//	public  void start(Stage Stage) throws Exception {
//		
//		
//		// On instancie un BorderPane:
//		BorderPane root = new BorderPane();
//		
//		// On instancie et on stylise des labels 
//		//et des panes qui seront contenus par notre BorderPane:
//		
//		Label lblBottom = new Label("footer");
//		TextField footer= new TextField();
//		HBox hbBottom= new HBox ();
//		
//		Label lblRight = new Label("Slogan");
//		TextField Slogan = new TextField();
//		VBox vbSlogan = new VBox();
//		Label lblleft = new Label(" gridpane");
//		TextField gridpane = new TextField();
//		VBox vbgridpane = new VBox();
//		
//		 hbBottom.setMaxWidth(GridPane.REMAINING);
//		 vbRight.setMaxWidth(GridPane.REMAINING );
//		 vbLeft.setMaxwidth(GridPane.REMAINING);
//		 hbBottom.addAll(lblBottom,footer);
//		 
//		
//		
//		 topPannel.setPrefSize(800, 100);
//		 topPannel.setStyle("-fx-background-color:orange");
//		Pane leftPannel = new Pane();
//		leftPannel.setPrefSize(200, 700);
//		leftPannel.setStyle("-fx-background-color: vert");
//		
//		// on positionne nos différents composants dans le BoderPane:
//		
//		 root.setTop(topPannel)
//	//	root.setBottom(lblBottom);
//		 root.setRight(lblRight);
//		 root.setLeft(leftPannel);
//		 root.setleft(lblleft);
//		
//		// On instancie notre scène en lui donnant notre pannel root et des dimmensions:
//		
//		Scene scene= new Scene(root,800,800);
//		
//		//On donne un titre à notre stage:
//		
//		Stage.setScene(scene);
//		// On affiche notre stage:
//		
//		Stage.show();
//	}
//
//		
//		
//	}