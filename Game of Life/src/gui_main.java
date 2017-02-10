
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class gui_main extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("gol_gui2.fxml"));
		
		Scene scene = new Scene(root, 800, 600);
		
		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}