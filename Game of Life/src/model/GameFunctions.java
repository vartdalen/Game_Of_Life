package model;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Cell;


public class GameFunctions{
	
	
	
	public Timeline timeline;
	private Color[] colors = new Color[] {Color.WHITE, Color.BLACK };
	public staticBoard board = new staticBoard();
	private Cell cell = new Cell();

	
	
	@Override
	public String toString() {
		String returnString = "";
		for(int i = 0; i < board.getLengthX(); i++) {
			for(int j = 0; j < board.getLengthY(); j++) {
				returnString = returnString + board.getCellState(i, j);
			}
		}
		return returnString;
	}
	
	
	public void blackify (Slider slider_size, double x, double y) {
		double rows = x / slider_size.getValue();
		double cols = y / slider_size.getValue();
		if (board.getCellState((int)cols, (int)rows) == 1) {
			board.setCellState((int)cols, (int)rows, false);
			} 
		else if (board.getCellState((int)cols, (int)rows) == 0) {			
				board.setCellState((int)cols, (int)rows, true);		
			}
		}
	
	public Timeline createTimeline() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
	
	
	public void clearCanvas(Canvas gol_canvas, Slider slider_size) { //Cleans up the canvas
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.getLengthX() * slider_size.getMax(), board.getLengthY() * slider_size.getMax());
	}
	
	
	public void drawBoard(Canvas gol_canvas, double slider_size) { //Draws the board
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		for(int i = 0; i < board.getLengthX(); i++) {
			for(int j = 0; j < board.getLengthY(); j++) {
				cell.x = j*slider_size;
				cell.y = i*slider_size;
				byte boardValue = board.getCellState(i, j);
				cell.draw(gc, slider_size, boardValue, colors);
			}
		}
	}
	

	public void drawGrid(Canvas gol_canvas, double slider_size) {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		for (double j = 0; j < gol_canvas.getHeight(); j+= slider_size){
			gc.strokeLine(0, j, 1000, j);
		}
		for (double i = 0; i < gol_canvas.getWidth(); i+= slider_size){
			gc.strokeLine(i, 1000, i, 0);
		}
	}
	
	
	public void newTimeline(Canvas gol_canvas, double slider_speed, double slider_size) {
		/*
		 * Starte generering/forandring av celler
		 * */
		if (timeline != null) {
			timeline.stop();
		}
		
		timeline = createTimeline();
		KeyFrame frame = new KeyFrame(Duration.millis(slider_speed), new EventHandler<ActionEvent>(){
			
			@Override
			public void handle(ActionEvent event) {
				board.nextGen();
				drawBoard(gol_canvas, slider_size);
				drawGrid(gol_canvas, slider_size);
			}});
		timeline.getKeyFrames().add(frame);
		
		drawBoard(gol_canvas, slider_size);
	}
}
	
