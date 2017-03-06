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

public class gui_controller implements Initializable {

	private byte [][] board = { //Mønster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	
	private double size = 70.0; //Cell-size
	
	private static class Cell {
		
		public double x,y;
		public boolean isAlive = false;
		public int aliveNeighbours;
		public void draw(GraphicsContext gc, double size) {
			gc.setFill(Color.GREEN);
			gc.fillRect(x, y, size, size);
		}
	}
	
	@FXML private Button btnStart;
	@FXML private Button btnStop;
	@FXML private Button btnClear;
	@FXML private Button btnImport;
	@FXML private Slider slider_size;
	@FXML private Slider slider_speed;
	@FXML private Canvas gol_canvas;
	private List<Cell> clist;
	
	public void initialize(java.net.URL location,
            java.util.ResourceBundle resources) {
		slider_size.setValue(5.0);
		slider_speed.setValue(5.0);
		clist = new ArrayList<Cell>();
		draw(size);
	}
	
	private void draw(double size) { //tegner opp mønster
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell cell = new Cell();
		gc.clearRect(0, 0, gol_canvas.widthProperty().doubleValue(), gol_canvas.heightProperty().doubleValue());
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++){
				if(board[i][j] == 1) {
				cell.y = i*size;
				cell.x = j*size;
				cell.draw(gc, size);
				}
			}
		}
	}
	
	public void drawCell(MouseEvent event) {
		Cell newCell = new Cell();
		newCell.y = event.getY();
		newCell.x = event.getX();
		clist.add(newCell);
		draw(size);
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
	
	@FXML
	public void changeSize(MouseEvent e) {
		/*
		 * Endre størrelse på cellene
		 * */
		double newSize = slider_size.getValue();
		draw(newSize);
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
