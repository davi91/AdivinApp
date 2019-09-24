package dad.javafx.adivin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Adivin extends Application {

	// Components
	private Label descLabel;
	private TextField numTxt;
	private Button comprobarBt;
	
	// Variables
	private int intentos = 1; // Primer intento
	private int randomNumber;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Generamos nuestro número aleatorio primero
		randomNumber = (int)(Math.random() * 101);
		
		descLabel = new Label("Introduce un número del 1 al 100");
		
		numTxt = new TextField("0");
		numTxt.setMaxWidth(100);
		
		comprobarBt = new Button("Comprobar");
		comprobarBt.setDefaultButton(true);
		comprobarBt.setOnAction(evt -> onComprobarBtAction(evt));
		
		VBox box = new VBox();
		box.setSpacing(5);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(descLabel, numTxt, comprobarBt);
		
		Scene scene = new Scene(box, 320, 200);
	
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void onComprobarBtAction(ActionEvent evt) {
		
		try {
			
			int n = Integer.parseInt(numTxt.getText());

			if( n == randomNumber ) {
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has ganado!");
				alert.setContentText(String.format("Solo has necesitado %d intentos.\nVuelve a jugar y hazlo mejor.", 
													intentos));
				alert.showAndWait();		
				
				// Preparamos para volver a jugar
				intentos = 1;
				randomNumber = (int)(Math.random() * 101);
				
			} else {
				
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("¡Has fallado!");
				
				alert.setContentText(String.format("El número a acertar es %s a %d.\nVuelve a intentarlo.",
													 (n > randomNumber) ? "menor" : "mayor",
													 n));
				
				alert.showAndWait();
				
				// Pues nada, otro intento más
				intentos++;
			}
			
		} catch( NumberFormatException  e ) {
			
			// No es nada parecido a un número
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("El número introducido no es válido");
			alert.showAndWait();		
			
		}
	}

	public static void main(String[] args) {	
		launch(args);
	}

}
