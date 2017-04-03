package model;

import java.awt.MouseInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import controller.gui_controller;
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


public class GameFunctions extends java.io.Reader{
	
	
	
	public Timeline timeline;
	private Color[] colors = new Color[] {Color.WHITE, Color.BLACK };
	private final int WIDTH = 100, HEIGHT = 100;
	public byte [][] board = new byte[WIDTH][HEIGHT];
	
	

	public byte[][] cloneByteArray() {
		byte[][] output = new byte[board.length][board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				output[i][j] = board[i][j];
			}
		}
		return output; 
	}
	
	
	@Override
	public String toString() {
		String returnString = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				returnString = returnString + board[i][j];
			}
		}
		return returnString;
	}
	
	public void setBoard(byte [][] gameBoard) {
		this.board = gameBoard;
	}
	
	
	
	public byte[][] nextGen() {
		int numberOfRows = board.length;
		int numberOfCols = board[0].length;
		
		byte[][] nextBoard = new byte[numberOfRows][];
		
		for(int row = 0; row < numberOfRows; row++) {
			nextBoard[row] = new byte[numberOfCols];
			for(int col = 0; col < numberOfCols; col++) {
				int neighbourCount = getNeighbourCount(row, col, board);
				
				if(neighbourCount < 2){
					nextBoard[row][col] = 0;
				} else if (neighbourCount > 3) {
					nextBoard[row][col] = 0;
				} else if (neighbourCount == 3) {
					nextBoard[row][col] = 1;
				} else if (board[row][col] == 1){ 
					if (neighbourCount == 2){ 
						nextBoard[row][col] = 1;
					}			
				}
			}
		}
		
		return nextBoard;
	}
	
	
	private static int getNeighbourCount(int row, int col, byte[][] board) {
		//Passe på at vi ikke går utenfor brettet
        int minRow = Math.max(0, row - 1);
        int maxRow = Math.min(board.length - 1, row + 1); 
        int minCol = Math.max(0, col - 1);
        int maxCol = Math.min(board[0].length - 1, col + 1); 	
		
        int neighbourCount = 0;
		
		for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (i == row && j == col){
                    continue;//Må ikke telle seg selv som en nabo til seg selv...
                }
                
                if (board[i][j] == 1) {
                	neighbourCount++;
                }	
            }
		}
        return neighbourCount;
	}
	
	
	public void blackify (Slider slider_size, double x, double y) {
		double rows = x / slider_size.getValue();
		double cols = y / slider_size.getValue();
		if (board[(int) cols][(int) rows] == 1) {
			board[(int) cols][(int) rows] = 0;
			} 
		else if (board[(int)cols][(int)rows] == 0) {			
				board[(int) cols][(int) rows] = 1;		
			}
		}
	
	public Timeline createTimeline() {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
	
	public void applyRule() {
		board = nextGen();
	}
	
	public void clearCanvas(Canvas gol_canvas, Slider slider_size) { //Cleans up the canvas
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.length * slider_size.getMax(), board[0].length * slider_size.getMax());
	}
	
	
	public void drawBoard(Canvas gol_canvas, double slider_size) { //Draws the board
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell cell = new Cell();
		gc.strokeRect(cell.x, cell.y, slider_size, slider_size);
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				cell.x = j*slider_size;
				cell.y = i*slider_size;
				byte boardValue = board[i][j];
				cell.draw(gc, slider_size, slider_size, boardValue, colors);
				gc.strokeRect(cell.x, cell.y, slider_size, slider_size);
			}
		}
	}
	
	public void startTimeline(Canvas gol_canvas, double slider_speed, double slider_size) {
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
			}});
		timeline.getKeyFrames().add(frame);
		timeline.play();
		
		drawBoard(gol_canvas, slider_size);
	}



	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return 0;
	}

	@Override
	public void close() throws IOException {
	}

	
	public void readGameBoardFromDisk(File file) throws IOException, PatternFormatException {
		try {
			readGameBoard(new FileReader(file));
		}
		catch (IOException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("File error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}
	
	public void readGameBoard(Reader r) throws IOException {
		Scanner inFile = new Scanner(r);
//		String testString = "";
		String pattern = "(\\d+.*?\\d+)";
		inFile.nextLine();
		while (inFile.hasNext()) {
			String testString = inFile.nextLine();
			int x = testString.charAt(0);
			int y = testString.charAt(2);
			x = Character.getNumericValue(x);
			y = Character.getNumericValue(y);
			board[x][y] = 1;
		}
	}		
}