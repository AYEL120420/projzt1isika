package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;
import java.util.ArrayList;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class App extends Application {
	//public premierSceneBorderPane  scene1;
	//public ArrayList<Stagiaire> stagiaires;

	private Scene scene1 = null;
	private Scene scene2 = null;
	private Stage monStage;
	
    @Override
    public void start(Stage stage) {
    	
    	premierSceneBorderPane root1 = new premierSceneBorderPane();
    	scene1 = new Scene(root1,600,400);
    	
    	deuxiemeScene root2 = new deuxiemeScene(scene1, stage);
    	scene2 = new Scene(root2, 600,400);
    	root1.monStage= stage;
    	root1.scene2 = scene2;
    	
        stage.setTitle("Gestionnaire des stagiaires");
		stage.setScene(scene1);
		
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

	public static Object getPrimaryStage() {
		// TODO Auto-generated method stub
		return null;
	}
}