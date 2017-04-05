package model;

import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.*;
/**
 * 
 * Inneholder standardmetoder som launcher applikasjonen,
 * henter data fra fxml, 
 * og bruker disse dataene til aa sette opp vindu og grafisk brukergrensesnitt.
 *
 */
public class gui_main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("/view/gol_gui3.fxml"));
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}