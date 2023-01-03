package basico;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PrimeiroFX extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		HBox box = new HBox();
		Button botaoA = new Button("A");
		Button botaoB = new Button("B");
		Button botaoC = new Button("Sair");
		
		botaoA.setOnAction(e -> {
			System.out.println("Daniel");
		});
		
		botaoB.setOnAction(e -> {
			System.out.println("Cara de Pastel!!");
		});
		
		botaoC.setOnAction(e ->{
			System.exit(0);
		});
		
		
		box.getChildren().add(botaoA);
		box.getChildren().add(botaoB);
		box.getChildren().add(botaoC);
		box.setSpacing(10);
		box.setAlignment(Pos.CENTER);
		
		Scene cenaPrincipal = new Scene(box, 100, 150);
		
		primaryStage.setScene(cenaPrincipal);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
