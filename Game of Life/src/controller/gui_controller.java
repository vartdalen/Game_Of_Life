package controller;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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
import model.Board;
import model.GameFunctions;
import model.dynamicBoard;
import model.staticBoard;
import model.fileReader;
/**
 * Herifra kalles funksjonene som styrer boardet.
 */
public class gui_controller implements Initializable {

	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider zoom;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	@FXML private TextField urlField;
	@FXML private Text generationCount;
	@FXML private ColorPicker colorPicker;
	@FXML private TextField boardSizeField;
	@FXML private Button confirmBoardSize;
	private GameFunctions functions = new GameFunctions();
	private fileReader reader = new fileReader();

	/**
	 * Kjorer funksjoner som gjor klar boardet med startverdier.
	 */
	public void initialize(java.net.URL location,
        java.util.ResourceBundle resources) {	
		functions.drawBoard(gol_canvas, zoom.getValue());
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	@FXML
	/**
	 * Slider som regulerer tiden mellom hver nye generasjon.
	 */
	public void changeAnimationSpeed (MouseEvent e) {
		functions.newTimeline(gol_canvas, slider_speed.getValue(), zoom.getValue(), generationCount);
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	@FXML
	/**
	 * Slider som regulerer storrelsen paa cellene.
	 */
	public void changeCellSize(MouseEvent e) {
		functions.clearCanvas(gol_canvas, zoom);
		functions.newTimeline(gol_canvas, slider_speed.getValue(), zoom.getValue(), generationCount);
		functions.drawBoard(gol_canvas, zoom.getValue());
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	
	@FXML
	/**
	* Starte generering/forandring av celler
	*/
	public void startBtnClicked(ActionEvent e) {
		
		functions.newTimeline(gol_canvas, slider_speed.getValue(), zoom.getValue(), generationCount);
		functions.drawGrid(gol_canvas, zoom.getValue());
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
		functions.clearCanvas(gol_canvas, zoom);
		functions.drawGrid(gol_canvas, zoom.getValue());
		functions = new GameFunctions();
	
	}
	
	@FXML
	/**
	* Importerer forhaandsdefinert board state fra fil. Bruker formatet .lif.
	*/
	public void importBtnClicked(ActionEvent e) {
		
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter Filter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
		FileChooser.ExtensionFilter Filter2 = new FileChooser.ExtensionFilter("LIF files (*.lif)", "*.lif");
		fileChooser.getExtensionFilters().addAll(Filter, Filter2);
		File file = fileChooser.showOpenDialog(btnImport.getScene().getWindow());
		if (file != null) {
			try {
				reader.readGameBoardFromDisk(file, functions.board);
			}
			catch (IOException ioe) {
				Alert alertbox = new Alert(AlertType.ERROR);
				alertbox.setTitle("Error");
				alertbox.setHeaderText("File error!");
				alertbox.setContentText(ioe.getMessage());
				alertbox.showAndWait();
			}
			catch(IndexOutOfBoundsException ex) {
				Alert alertbox = new Alert(AlertType.ERROR);
				alertbox.setTitle("Error");
				alertbox.setHeaderText("File error!");
				alertbox.setContentText("Du lastet inn et brett som er større en det nåværende spillbrettet!");
				alertbox.showAndWait();
			}
		}
		functions.drawBoard(gol_canvas, zoom.getValue());
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	@FXML
	/**
	* Importerer forhaandsdefinert board state fra url. Bruker formatet .lif.
	*/
	public void addURLClicked(ActionEvent e) {
		
		try {
			reader.readGameBoardFromURL(urlField.getText(), functions.board);
		}
		catch (IOException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		catch(IndexOutOfBoundsException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText("Du lastet inn et brett som er større en det nåværende spillbrettet!");
			alertbox.showAndWait();
		}
		functions.drawBoard(gol_canvas, zoom.getValue());
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	@FXML
	/**
	* Knappetrykk innenfor gridden forandrer verdien i cellen man trykker paa,
	* og boardet blir oppdatert.
	*/
	public void mouseClick(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		try {
			functions.blackify(zoom, x, y);
			functions.drawBoard(gol_canvas, zoom.getValue());
			functions.drawGrid(gol_canvas, zoom.getValue());
		}
		catch (IndexOutOfBoundsException error) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("Error");
			alertbox.setContentText("Du trykket utenfor det nåværende brettet!");
			alertbox.showAndWait();
		}
	}
	
	@FXML
	public void changeColor(ActionEvent e) {
		functions.colors[1] = colorPicker.getValue();
		functions.drawBoard(gol_canvas, zoom.getValue());
		functions.drawGrid(gol_canvas, zoom.getValue());
	}
	
	/**
	 * Denne metoden endrer størrelsen på brettet, forutsatt at brett-typen er av dynamicBoard
	 * @param e knappetrykk
	 */
	@FXML
	public void changeBoardSize(ActionEvent e) {
		if (boardSizeField.getText().matches("[0-9]{1,3}")) {
			int inputSize = Integer.parseInt(boardSizeField.getText());
			if (inputSize <= 200) {
				functions.board = new dynamicBoard(inputSize);
				Alert alertbox = new Alert(AlertType.INFORMATION);
				alertbox.setTitle("Size Changed");
				alertbox.setHeaderText("Size Changed");
				alertbox.setContentText("Størrelsen på brettet er nå endret til "+inputSize);
				alertbox.showAndWait();
			}
			else if (inputSize > 200) {
				Alert alertbox = new Alert(AlertType.ERROR);
				alertbox.setTitle("Error");
				alertbox.setHeaderText("Error");
				alertbox.setContentText("Du må skrive inn et tall under 201");
				alertbox.showAndWait();
			}
		}
		else if (!boardSizeField.getText().matches("[0-9]{1,3}")) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("Error");
			alertbox.setContentText("Du må srkive inn et tall som er under 201");
			alertbox.showAndWait();
		}
	}
	
}
