
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class gui_main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		Group root = new Group();
		Parent root = FXMLLoader.load(getClass().getResource("gol_gui3.fxml"));
		
		Scene scene = new Scene(root, 800, 600);
		
//		Canvas canvas = new Canvas(700, 600);
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//		drawShapes(gc);
//		root.getChildren().add(canvas);
//		Heyhey
		
		primaryStage.setTitle("Game of Life");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
//	private void drawShapes(GraphicsContext gc) {
//		gc.setFill(Color.GREEN);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}