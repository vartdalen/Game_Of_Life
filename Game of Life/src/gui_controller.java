import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class gui_controller implements Initializable {

	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	public void heyEvent(ActionEvent event) {
		System.out.println("helloooooo");
	}
	
	/*@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas graphics;*/
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		
	}
	
	
}
