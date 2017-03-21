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

public class gui_controller extends GameFunctions implements Initializable {

	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	private List<Cell> clist;
	private byte [][] initialboard = { //Mønster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	private byte [][] board = super.cloneByteArray(initialboard);
	private Color[] colors = new Color[] { Color.WHITE, Color.BLACK };
	private Timeline timeline;
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		
		slider_speed.setValue((slider_speed.getValue()));
		slider_size.setValue(slider_size.getValue());
		slider_speed.valueProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (timeline == null) {
					return;
				}
				
				timeline.stop();
				timeline = createTimeline(newValue.floatValue());
				KeyFrame frame = new KeyFrame(Duration.millis(getAnimationSpeed()), new EventHandler<ActionEvent>(){
					
					@Override
					public void handle(ActionEvent event) {
						applyRule(board);
						drawBoard();
						
					}});
				timeline.getKeyFrames().add(frame);
				timeline.play();
				
			}});
		clist = new ArrayList<Cell>();
		drawBoard();
	}
	
	
	public void drawBoard() {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell cell = new Cell();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				cell.x = j*getCellSize();
				cell.y = i*getCellSize();
				byte boardValue = board[i][j];
				cell.draw(gc, getCellSize(), getCellSize(), boardValue, colors);
			}
		}
	}
	
//	private byte[][] cloneArray(byte[][] innBoard) {
//		return super.cloneByteArray(innBoard);
//	}
		
		
	void clearCanvas() {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.length * slider_size.getMax(), board[0].length * slider_size.getMax());
	}
	
	public void applyRule(byte[][] board) {
		super.nextGen((byte)board.length, (byte)board.length, board);
		clearCanvas();
	}
	
	
	
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
		if (timeline != null) {
			timeline.stop();
		}
		timeline = super.createTimeline(getAnimationSpeed());
		KeyFrame frame = new KeyFrame(Duration.millis(getAnimationSpeed()), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				applyRule(board);
				drawBoard();
				
				System.out.println("ny frame");
			}});
		timeline.getKeyFrames().add(frame);
		timeline.play();
		
		drawBoard();
	}
	
	public void stopBtnClicked(ActionEvent e) {
		/*
		 * Stoppe generering/forandring av celler
		 * */
		if (timeline != null) {
			timeline.stop();
		}
	}
	
	@FXML
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
//		board = super.cloneByteArray(initialboard);
		clearCanvas();
		timeline.stop();
		board = super.cloneByteArray(initialboard);
		drawBoard();
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
		 * Kunne importere andre mønstre
		 * */
	}
	
	public void exitEvent(ActionEvent event) {
		System.exit(0);
	}
	
	public void mouseClick(MouseEvent e) {
	//	GraphicsContext gc = gol_canvas.getGraphicsContext2D();
	//	Cell cell = new Cell();
	//	for(int i = 0; i < board.length; i++) {
	//		for(int j = 0; j < board[i].length; j++) {
	//			cell.x = e.getX();
	//			cell.y = e.getY();
	//			byte boardValue = board[i][j];
	//			if ()
	//			cell.draw(gc, getCellSize(), getCellSize(), boardValue, colors);
	//		}
		}
			
		
	//a}
}
