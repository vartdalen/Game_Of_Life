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
/**
 * Herifra kalles funksjonene som styrer boardet.
 */
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

	/**
	 * Kjorer funksjoner som gjor klar boardet med startverdier.
	 */
	public void initialize(java.net.URL location,
        java.util.ResourceBundle resources) {	
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	/**
	 * Slider som regulerer tiden mellom hver nye generasjon.
	 */
	public void changeAnimationSpeed (MouseEvent e) {
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue(), generationCount);
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	/**
	 * Slider som regulerer storrelsen paa cellene.
	 */
	public void changeCellSize(MouseEvent e) {
		functions.clearCanvas(gol_canvas, slider_size);
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue(), generationCount);
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	
	@FXML
	/**
	* Starte generering/forandring av celler
	*/
	public void startBtnClicked(ActionEvent e) {
		
		functions.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue(), generationCount);
		functions.drawGrid(gol_canvas, slider_size.getValue());
		functions.timeline.play();
	}
	
	
	@FXML
	/**
	* Stoppe generering/forandring av celler
	*/
	public void stopBtnClicked(ActionEvent e) {
		
		if (functions.timeline != null) {
			functions.timeline.stop();
		}
	}
	
	
	@FXML
	/**
	* fjerne eksisterende celler -> blanke ark.
	*/
	public void clearBtnClicked(ActionEvent e) {
		
		if(functions.timeline != null) {
			functions.timeline.stop();
		}
		functions.clearCanvas(gol_canvas, slider_size);
		functions.drawGrid(gol_canvas, slider_size.getValue());
		functions = new GameFunctions();
	
	}
	
	@FXML
	/**
	* Importerer forhaandsdefinert board state fra fil. Bruker formatet .lif.
	*/
	public void importBtnClicked(ActionEvent e) {
		
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
	/**
	* Importerer forhaandsdefinert board state fra url. Bruker formatet .lif.
	*/
	public void addURLClicked(ActionEvent e) {
		
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
	/**
	* Knappetrykk innenfor gridden forandrer verdien i cellen man trykker paa,
	* og boardet blir oppdatert.
	*/
	public void mouseClick(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		functions.blackify(slider_size, x, y);
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeColor(ActionEvent e) {
		functions.colors[1] = colorPicker.getValue();
		functions.drawBoard(gol_canvas, slider_size.getValue());
		functions.drawGrid(gol_canvas, slider_size.getValue());
	}
	
}
