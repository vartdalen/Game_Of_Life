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
<<<<<<< HEAD
import javafx.util.Duration;
import model.Cell;
import model.GameFunctions;
=======
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.Cell;
import model.GameFunctions;
import model.PatternFormatException;
import model.gui_main;
>>>>>>> dag
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
<<<<<<< HEAD
/**
 * Herifra kalles funksjonene som styrer boardet.
 */
=======

>>>>>>> dag
public class gui_controller implements Initializable {

	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
<<<<<<< HEAD
	private List<Cell> clist;
	private GameFunctions gol = new GameFunctions();
	
=======
	@FXML private TextField urlField;
	private List<Cell> clist;
	private GameFunctions gol = new GameFunctions();

>>>>>>> dag
	
	
	
	/**
	 * Kjører funksjoner som gjør klar boardet med startverdier.
	 */
	public void initialize(java.net.URL location,
<<<<<<< HEAD
            java.util.ResourceBundle resources) {
		
	slider_speed.setValue((slider_speed.getValue()));
	slider_size.setValue(slider_size.getValue());
	slider_speed.valueProperty().addListener(new ChangeListener<Number>(){
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (gol.timeline == null) {
				return;
			}
			
			gol.timeline.stop();
			gol.timeline = gol.createTimeline(newValue.floatValue());
			KeyFrame frame = new KeyFrame(Duration.millis(getAnimationSpeed()), new EventHandler<ActionEvent>(){
					
					@Override
				public void handle(ActionEvent event) {
					gol.applyRule();
					gol.drawBoard(gol_canvas, getCellSize());
				}});
			
			gol.timeline.getKeyFrames().add(frame);
			gol.timeline.play();	
			}});
		
		clist = new ArrayList<Cell>();
		gol.drawBoard(gol_canvas, getCellSize());
	}
	
	
=======
            java.util.ResourceBundle resources) {	
		clist = new ArrayList<Cell>();
		gol.drawBoard(gol_canvas, slider_size.getValue());
	}
	
	@FXML
	public void changeAnimationSpeed (MouseEvent e) {
		gol.startTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
	}
	
	@FXML
	public void changeCellSize(MouseEvent e) {
		gol.drawBoard(gol_canvas, slider_size.getValue());
	}
	
	@FXML
>>>>>>> dag
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
<<<<<<< HEAD
		gol.startTimeline(gol_canvas, getAnimationSpeed(), getCellSize());
=======
		gol.startTimeline(gol_canvas, slider_speed.getValue(), slider_size.getValue());
>>>>>>> dag
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
<<<<<<< HEAD

		gol.clearCanvas(gol_canvas, slider_size);
		gol.timeline.stop();
		gol.board = gol.initBoard();
		gol.drawBoard(gol_canvas, getCellSize());
	}
	
	@FXML
	private float getAnimationSpeed() {
		return (float) slider_speed.getValue();
=======
		gol.clearCanvas(gol_canvas, slider_size);
		gol.board = new byte[100][100];
>>>>>>> dag
	}
	
	
	@FXML
<<<<<<< HEAD
	public float getCellSize() {
		
		//forandrer lengde og hï¿½yde pï¿½ cellene
		return (float)slider_size.getValue();
		
=======
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre mï¿½nstre
		 * */
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter Filter = new FileChooser.ExtensionFilter("TXT files (*.txt), LIF files (*.lif)", "*.txt, *.lif");
		fileChooser.getExtensionFilters().add(Filter);
		File file = fileChooser.showOpenDialog(btnImport.getScene().getWindow());

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
		gol.drawBoard(gol_canvas, slider_size.getValue());
>>>>>>> dag
	}
	
	@FXML
	public void addURLClicked(ActionEvent e) {
		/*
<<<<<<< HEAD
		 * Kunne importere andre mï¿½nstre
=======
		 * Kunne importere mÃ¸nstre fra URL
>>>>>>> dag
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
	}
	
	@FXML
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
<<<<<<< HEAD
	/**
	 * Ved museklikk blir x og y koordinatverdi(double) hentet og delt pa cellestorrelse.
	 * Gjenvaerende verdier blir castet til (int) og satt inn i board.
	 * Boardet tegnes pa nytt med de nye verdiene.
	 * @param e Bruker MouseEvent for a hente x og y verdier.
	 */
=======
	@FXML
>>>>>>> dag
	public void mouseClick(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		gol.blackify(slider_size, x, y);
<<<<<<< HEAD
		gol.drawBoard(gol_canvas, getCellSize());
=======
		gol.drawBoard(gol_canvas, slider_size.getValue());
>>>>>>> dag
		}
	
}
