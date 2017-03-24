package controller;
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
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
//import javafx.scene.control.ColorPicker;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Cell;
import model.GameFunctions;
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
	private List<Cell> clist;
	private GameFunctions gol = new GameFunctions();
	
	
	
	
	
	public void initialize(java.net.URL location,
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
	
	
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
		gol.startTimeline(gol_canvas, getAnimationSpeed(), getCellSize());
	}
	
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

		gol.clearCanvas(gol_canvas, slider_size);
		gol.timeline.stop();
		gol.board = gol.initBoard();
		gol.drawBoard(gol_canvas, getCellSize());
	}
	
	@FXML
	private float getAnimationSpeed() {
		return (float) slider_speed.getValue();
	}
	
	
	@FXML
	public float getCellSize() {
		
		//forandrer lengde og h�yde p� cellene
		return (float)slider_size.getValue();
		
	}
	
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre m�nstre
		 * */
	}
	
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	public void mouseClick(MouseEvent e) {
		gol.blackify(slider_size);
		gol.drawBoard(gol_canvas, getCellSize());
		}
	
}
