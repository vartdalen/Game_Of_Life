package controller;
import java.awt.MouseInfo;
import java.awt.Point;
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

public class gui_controller implements Initializable {
	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	private List<Cell> plist;

	private byte [][] board = { //Mønster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	
	private double initialSize = 50.0; //Cell-size
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
//		plist = new ArrayList<Cell>();
		drawGrid(initialSize);
	}
	
	private void drawGrid(double size) { //tegner opp mønster
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell p = new Cell();
		p.size = slider_size.getValue();
		gc.clearRect(0, 0, gol_canvas.widthProperty().doubleValue(), gol_canvas.heightProperty().doubleValue());
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++){
				if(board[i][j] == 1) {
				p.initialy = i*p.size;
				p.initialx = j*p.size;
				p.drawInitialCells(gc);
				System.out.println(p.size);
				}
			}
		}
		
//		gc.clearRect(0, 0, gol_canvas.widthProperty().doubleValue(), gol_canvas.heightProperty().doubleValue());
//		for( Cell p : plist) {
//			p.draw(gc, Color.RED,slider_size.getValue());
//		}
	}
	
//	public void blackify2 (double a, double b, double size) {
//		
//		board[i][j] = a + b / size;
//		
//	}
	
	public void blackify (MouseEvent e) {
		
		System.out.println(getMouseColCoordinate());
		System.out.println(getMouseRowCoordinate());
		
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell c = new Cell();

//		
//		for(int i = 0; i<board.length; i++) {
//			for(int j = 0; j<board[i].length; j++){
//			
//			c.x = a.getX();
//			c.y = a.getY();
//			c.drawCell(gc, size);
//		
//			}
//		}
		
		
		
//		double cols, rows;
//		
//		for(int i = 0; i<board.length; i++) {
//			for(int j = 0; j<board[i].length; j++){
//				
////				cols = MouseInfo.getPointerInfo().getLocation().getX();
////				rows = MouseInfo.getPointerInfo().getLocation().getY();
//					
//				if (board[cols][rows] == 1) {
//					
//					board[i][j] = 0;
//					drawGrid(size);
//					
//				} 
//				
//			}
//		}
	}
	
	public double getMouseColCoordinate() {
		
		double ColCo;
		
		ColCo = MouseInfo.getPointerInfo().getLocation().getX();
		
		return ColCo / slider_size.getValue();

	}
	
	public double getMouseRowCoordinate() {
		
		double RowCo;
		
		RowCo = MouseInfo.getPointerInfo().getLocation().getY();
		
		return RowCo / slider_size.getValue();
		
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
		
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, gol_canvas.widthProperty().doubleValue(), gol_canvas.heightProperty().doubleValue());
	
	}
	
	public void changeSpeed(ActionEvent e) {
		/*
		 * Endre hastighet på celle generering
		 * */
	}
	
	@FXML
	public void changeSize(MouseEvent e) {
		/*
		 * Endre størrelse på cellene
		 * */
		double newSize = slider_size.getValue();
		drawGrid(newSize);
	}
	
	public void importBtnClicked(ActionEvent e) {
		/*
		 * Kunne importere andre mønstre
		 * */
	}
	
}
