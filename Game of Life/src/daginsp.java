package controller;
//import java.net.URL;
import java.util.ArrayList;
import java.util.List;
//import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import model.Cell;
import model.GameFunctions;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public class daginsp implements Initializable {

	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	private List<Cell> clist;
	private GameFunctions functions;
	private byte [][] initialboard = { //Mønster
				{1,0,0,1}, 
				{0,1,1,0}, 
				{0,1,1,0}, 
				{1,0,0,1}
			   };
	private byte [][] board = functions.cloneByteArray(initialboard);
	private Color[] colors = new Color[] { Color.WHITE, Color.BLACK }; 
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		slider_size.setValue(slider_size.getValue());
		slider_speed.setValue(5.0);
		clist = new ArrayList<Cell>();
		drawBoard();
	}
	
	private void drawBoard() {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				double x = j*getCellSize();
				double y = i*getCellSize();
				byte boardValue = board[i][j];
				gc.setFill(colors[boardValue]); 
				gc.fillRect(x, y, getCellSize(), getCellSize());
			}
		}
	}
	
	public void startBtnClicked(ActionEvent e) {
		/*
		 * Starte generering/forandring av celler
		 * */
	}
	
	public void stopBtnClicked(ActionEvent e) {
		/*
		 * Stoppe generering/forandring av celler
		 * */
	}
	
	public void clearBtnClicked(ActionEvent e) {
		/*
		 * fjerne eksisterende celler -> blanke ark.
		 * */
	}
	
	public void changeSpeed(ActionEvent e) {
		/*
		 * Endre hastighet på celle generering
		 * */
	}
	
	//@FXML
	//public void changeSize(MouseEvent e) {
		/*
		 * Endre størrelse på cellene
		 * */
	//	double newSize = slider_size.getValue();
	//	draw(newSize);
	//}
	
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
	
	public void heyEvent(ActionEvent event) {
		System.out.println("helloooooo");
	}
	
	
}
