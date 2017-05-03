package controller;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.GameFunctions;
import model.PatternFormatException;
import model.fileReader;

public class gui_controller implements Initializable {

	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	@FXML private TextField urlField;
	@FXML private Text generationCount;
	@FXML private ColorPicker colorPicker;
	private GameFunctions functions = new GameFunctions();
	private fileReader reader = new fileReader();

	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {	
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeAnimationSpeed (MouseEvent e) {
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeCellSize(MouseEvent e) {
		functions.clearCanvas(gol_canvas, slider_size);
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
		functions.timeline.play();
	}
	
	@FXML
	public void stopBtnClicked(ActionEvent e) {
		/*
		 * Stoppe generering/forandring av celler
		 * */
		if (functions.timeline != null) {
			functions.timeline.stop();
		}
	}
	
	@FXML
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
		if(functions.timeline != null) {
			functions.timeline.stop();
		}
		functions.clearCanvas(gol_canvas, slider_size);
		functions.drawGrid(gol_canvas, slider_size.getValue());
		functions = new GameFunctions();
	}
	
	@FXML
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre m�nstre
		 * */
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter Filter = new FileChooser.ExtensionFilter("TXT files (*.txt), LIF files (*.lif)", "*.txt, *.lif");
		fileChooser.getExtensionFilters().add(Filter);
		File file = fileChooser.showOpenDialog(btnImport.getScene().getWindow());
		if (file != null) {
			try {
				reader.readGameBoardFromDisk(file, functions.board);
			}
			catch (IOException | PatternFormatException ioe) {
				Alert alertbox = new Alert(AlertType.ERROR);
				alertbox.setTitle("Error");
				alertbox.setHeaderText("File error!");
				alertbox.setContentText(ioe.getMessage());
				alertbox.showAndWait();
			}
		}
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void addURLClicked(ActionEvent e) {
		/*
		 * Kunne importere mønstre fra URL
		 * */
		try {
			reader.readGameBoardFromURL(urlField.getText(), functions.board);
		}
		catch (IOException | PatternFormatException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	public void changeColor(ActionEvent e) {
		functions.colors[1] = colorPicker.getValue();
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void mouseClick(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		functions.blackify(slider_size, x, y);
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
}
