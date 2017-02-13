import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class gui_controller implements Initializable {

	public void helloEvent(ActionEvent event) {
		
		System.out.println("hello*2");
		
	}

	@Override
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		System.out.println("test");
	}
	
}
