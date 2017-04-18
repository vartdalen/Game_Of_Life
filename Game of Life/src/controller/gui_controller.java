package controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.Cell;
import model.GameFunctions;
import model.PatternFormatException;
import model.gui_main;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
	private List<Cell> clist;
	private GameFunctions gol = new GameFunctions();

	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {	
		clist = new ArrayList<Cell>();
		gol.drawBoard(gol_canvas, slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeAnimationSpeed (MouseEvent e) {
		gol.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeCellSize(MouseEvent e) {
		gol.clearCanvas(gol_canvas, slider_size);
		gol.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		gol.drawBoard(gol_canvas, slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
		gol.newTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void stopBtnClicked(ActionEvent e) {
		/*
		 * Stoppe generering/forandring av celler
		 * */
		if (gol.timeline != null) {
			gol.timeline.stop();
		}
	}
	
	@FXML
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
		if(gol.timeline != null) {
			gol.timeline.stop();
		}
		gol.clearCanvas(gol_canvas, slider_size);
		gol.drawGrid(gol_canvas, slider_size.getValue());
		gol.board = new byte[100][100];
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
				gol.readGameBoardFromDisk(file);
			}
			catch (IOException | PatternFormatException ioe) {
				Alert alertbox = new Alert(AlertType.ERROR);
				alertbox.setTitle("Error");
				alertbox.setHeaderText("File error!");
				alertbox.setContentText(ioe.getMessage());
				alertbox.showAndWait();
			}
		}
		gol.drawBoard(gol_canvas, slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void addURLClicked(ActionEvent e) {
		/*
		 * Kunne importere mønstre fra URL
		 * */
		try {
			gol.readGameBoardFromURL(urlField.getText());
		}
		catch (IOException | PatternFormatException ioe) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ioe.getMessage());
			alertbox.showAndWait();
		}
		gol.drawBoard(gol_canvas, slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	@FXML
	public void mouseClick(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		gol.blackify(slider_size, x, y);
		gol.drawBoard(gol_canvas, slider_size.getValue());
		gol.drawGrid(gol_canvas, slider_size.getValue());
	}
}
