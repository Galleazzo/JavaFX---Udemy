package basico;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Contador extends Application{
	
	private Integer contador = 0;
	
	private void atualizarLabelNumero(Label label) {
		label.setText(Integer.toString(contador));
		
		label.getStyleClass().remove("verde");
		label.getStyleClass().remove("vermelho");
		
		if(contador > 0) {
			label.getStyleClass().add("verde");
		}else if(contador < 0 ) {
			label.getStyleClass().add("vermelho");
		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox boxPrincipal = new VBox();
		HBox boxSecundario = new HBox();
		
		Label labelTtitulo = new Label("Contador");
		Label labelNumero = new Label("0");
		labelTtitulo.getStyleClass().add("label");
		labelNumero.getStyleClass().add("label");
		
		Button botaoDecremento = new Button("-");
		Button botaoIncremento = new Button("+");
		botaoDecremento.getStyleClass().add("botao");
		botaoIncremento.getStyleClass().add("botao");
		
		botaoDecremento.setOnAction(e -> {
			contador = contador - 1;
			atualizarLabelNumero(labelNumero);
			
		});
		
		botaoIncremento.setOnAction(e -> {
			contador = contador + 1;
			atualizarLabelNumero(labelNumero);
		});
		
		boxSecundario.getChildren().add(botaoIncremento);
		boxSecundario.getChildren().add(botaoDecremento);
		boxSecundario.setAlignment(Pos.CENTER);
		boxSecundario.setSpacing(10);
		
		boxPrincipal.getStyleClass().add("conteudo");
		boxPrincipal.getChildren().add(labelTtitulo);
		boxPrincipal.getChildren().add(labelNumero);
		boxPrincipal.getChildren().add(boxSecundario);
		boxPrincipal.setAlignment(Pos.CENTER);
		boxPrincipal.setSpacing(10);
		
		
		String caminhoCSS = getClass().getResource("/basico/Contador.css").toExternalForm();
		
		Scene cenaPrincipal = new Scene(boxPrincipal, 100, 250);
		
		cenaPrincipal.getStylesheets().add(caminhoCSS);
		
		primaryStage.setScene(cenaPrincipal);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}

}
