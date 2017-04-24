package model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Cell;


public class GameFunctions{
	
	
	
	public Timeline timeline;
	private Color[] colors = new Color[] {Color.WHITE, Color.BLACK };
	public staticBoard board = new staticBoard();
	private Cell cell = new Cell();
	
	

	public byte[][] cloneByteArray() {
		byte[][] output = new byte[board.gameBoard.length][board.gameBoard[0].length];
		for(int i = 0; i < board.gameBoard.length; i++) {
			for(int j = 0; j < board.gameBoard[i].length; j++) {
				output[i][j] = board.gameBoard[i][j];
			}
		}
		return output; 
	}
	
	
	@Override
	public String toString() {
		String returnString = "";
		for(int i = 0; i < board.gameBoard.length; i++) {
			for(int j = 0; j < board.gameBoard[i].length; j++) {
				returnString = returnString + board.gameBoard[i][j];
			}
		}
		return returnString;
	}
	
	public void setBoard(byte [][] gameBoard) {
		this.board.gameBoard = gameBoard;
	}
	
	
	public void blackify (Slider slider_size, double x, double y) {
		double rows = x / slider_size.getValue();
		double cols = y / slider_size.getValue();
		if (board.gameBoard[(int) cols][(int) rows] == 1) {
			board.gameBoard[(int) cols][(int) rows] = 0;
			} 
		else if (board.gameBoard[(int)cols][(int)rows] == 0) {			
				board.gameBoard[(int) cols][(int) rows] = 1;		
			}
		}
	
	public Timeline createTimeline() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
	
	
	public void clearCanvas(Canvas gol_canvas, Slider slider_size) { //Cleans up the canvas
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.gameBoard.length * slider_size.getMax(), board.gameBoard[0].length * slider_size.getMax());
	}
	
	
	public void drawBoard(Canvas gol_canvas, double slider_size) { //Draws the board
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		for(int i = 0; i < board.gameBoard.length; i++) {
			for(int j = 0; j < board.gameBoard[i].length; j++) {
				cell.x = j*slider_size;
				cell.y = i*slider_size;
				byte boardValue = board.gameBoard[i][j];
				cell.draw(gc, slider_size, boardValue, colors);
			}
		}
	}
	

	public void drawGrid(Canvas gol_canvas, double slider_size) {
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		for (double i = 0; i < gol_canvas.getWidth(); i+= slider_size){
			gc.strokeLine(i, 1000, i, 0);
		}
		for (double j = 0; j < gol_canvas.getHeight(); j+= slider_size){
			gc.strokeLine(0, j, 1000, j);
		}
	}
	
	public void applyRule() {
		board.gameBoard = board.nextGen();
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
				applyRule();
				drawBoard(gol_canvas, slider_size);
				drawGrid(gol_canvas, slider_size);
			}});
		timeline.getKeyFrames().add(frame);
		
		drawBoard(gol_canvas, slider_size);
	}

	
	private byte[] getBoundingBox() {
		byte[] boundingBox = new byte[4];
		boundingBox[0] = (byte) board.gameBoard.length;
		boundingBox[1] = 0;
		boundingBox[2] = (byte) board.gameBoard[0].length;
		boundingBox[3] = 0;
		for(byte i = 0; i < board.gameBoard.length; i++) {
			for(byte j = 0; j < board.gameBoard[i].length; j++) {
				if (board.gameBoard[i][j] == 0) continue;
				if (i < boundingBox[0]) {
					boundingBox[0] = i;
				}
				if(i > boundingBox[1]) {
					boundingBox[1] = i;
				}
				if(j < boundingBox[2]) {
					boundingBox[2] = j;
				}
				if(j > boundingBox[3]) {
					boundingBox[3] = j;
				}
			}
		}
		return boundingBox;
	}
	
	
	public String getBoundingBoxPattern() {
		if (board.gameBoard.length == 0) return "";
		
		byte[] boundingBox = getBoundingBox();
		String str = "";
		for(int i = boundingBox[0]; i <= boundingBox[1]; i++) {
			for(int j = boundingBox[2]; j <= boundingBox[3]; j++) {
				if (board.gameBoard[i][j] == 1) {
					str = str + "1";
				}
				else {
					str = str + "0";
				}
			}
		}
		return str;
	}
}
	
