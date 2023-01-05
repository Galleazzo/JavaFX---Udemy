package layout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLayout4 extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Scene principal = new Scene(new TesteFlowPane(), 800, 600);
		
		
		primaryStage.setScene(principal);
		primaryStage.setTitle("Gerenciador de Layout");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
