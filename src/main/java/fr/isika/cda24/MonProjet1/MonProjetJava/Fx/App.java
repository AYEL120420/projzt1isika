package fr.isika.cda24.MonProjet1.MonProjetJava.Fx;

import java.io.IOException;
import java.util.ArrayList;

import fr.isika.cda24.MonProjet1.MonProjet1.Stagiaire;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {
	

	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Scene scene4;
	private Stage monStage;
	
	public ArrayList<Stagiaire> stagiaires;
	
	
    @Override
	public void init() {
		
		stagiaires = new ArrayList<Stagiaire>();
	}

	@Override
    public void start(Stage monStage) throws IOException {
    	
    	premierSceneBorderPane root1 = new premierSceneBorderPane();
    	scene1 = new Scene(root1,1200,800);
    	
    	deuxiemeScene root2 = new deuxiemeScene(scene1, monStage);
    	scene2 = new Scene(root2, 1200,800);
    	root1.monStage= monStage;
    	root1.scene2 = scene2;
    	
    	troisiemeSceneTable root3 = new troisiemeSceneTable(stagiaires, monStage, scene1, scene2, false);
    	scene3 = new Scene(root3, 1200,800);
    	root2.monStage= monStage;
    	root2.scene3 = scene3;
    	root1.scene3 = scene3;
    	
    	documentation root4 = new documentation(scene1, monStage);
    	scene4 = new Scene(root4, 1200,800);
    	root1.monStage= monStage;
    	root1.scene4 = scene4;
    
    	
        monStage.setTitle("Seek by ISIKA");
		monStage.setScene(scene1);
		
        monStage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }


}
