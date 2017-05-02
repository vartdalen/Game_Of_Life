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
	public dynamicBoard board = new dynamicBoard();
//	public staticBoard board = new staticBoard();
	private Cell cell = new Cell();

	
	
	@Override
	/**
	 * @return stringverdi av verdien i en celle.
	 */
	public String toString() {
		String returnString = "";
		for(int i = 0; i < board.getLengthX(); i++) {
			for(int j = 0; j < board.getLengthY(); j++) {
				returnString = returnString + board.getCellState(i, j);
			}
		}
		return returnString;
	}
	
	/**
	 * Forandrer verdien lagret i en celle ut ifra angitte koordinater.
	 * 
	 * @param slider_size Dynamisk verdi for cellestorrelse fra slider i gui.
	 * @param x x verdi for museklikk.
	 * @param y y verdi for museklikk.
	 */
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
	
	/**
	 * Lager en ny Timeline.
	 * 
	 * @return Timeline som kan holde animasjon gaende i ubegrenset tid.
	 */
	public Timeline createTimeline() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
	
	/**
	 * Tommer canvaset for grafikk.
	 */
	public void clearCanvas(Canvas gol_canvas, Slider slider_size) {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.getLengthX() * slider_size.getMax(), board.getLengthY() * slider_size.getMax());
	}
	
	/**
	 * Legger grafikk over utfylt grid ved aa iterere gjennom griden,
	 * sjekker og kjorer draw paa hver individuelle verdi.
	 * 
	 * @param gol_canvas henter canvaset slik at vi kan tegne paa det.
	 * @param slider_size henter cellestorrelse fra gui.
	 */
	public void drawBoard(Canvas gol_canvas, double slider_size) {
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
	
	/**
	 * Tegner et tomt grid.
	 * @param gol_canvas henter canvaset fordi vi trenger hoyden og bredden aa kunne tegne et grid.
	 * @param slider_size kreves for aa vite storrelsen vi skal sette av til hver posisjon i gridden.
	 */
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
	
	/**
	 * Starter en timeline som setter betingelsene til hvor ofte boardet kan forandre seg.
	 * 
	 * @param gol_canvas kreves for aa kunne kjore drawBoard drawGrid.
	 * @param slider_speed henter variabel direkte fra gui som bestemmer oppdateringsfrekvensen til boardet.
	 * @param slider_size kreves i drawBoard og drawGrid.
	 */
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
	
