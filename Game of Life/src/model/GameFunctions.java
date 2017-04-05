package model;

import java.awt.MouseInfo;
<<<<<<< HEAD
=======
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
>>>>>>> dag

import controller.gui_controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
<<<<<<< HEAD
import javafx.scene.control.Slider;
=======
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
>>>>>>> dag
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Cell;

<<<<<<< HEAD
/**
 *Alle funksjoner som virker mot board er samlet her.
 */
public class GameFunctions {
	
	
	public byte [][] board = initBoard();
	public Timeline timeline;
	private Color[] colors = new Color[] { Color.WHITE, Color.BLACK };
	
	/**
	 * Todimensionalt array. Virker som mal for spillebrettets aller forste iterasjon.
	 */
	public byte[][] initBoard() {
		byte [][] board = {
//				:::BLOCK:::
//				{0,0,0,0},
//				{0,1,1,0},
//				{0,1,1,0},
//				{0,0,0,0},
//				:::BLINKER:::
//				{0,0,0,0,0},
//				{0,0,0,0,0},
//				{0,1,1,1,0},
//				{0,0,0,0,0},
//				{0,0,0,0,0},
//				:::GLIDER:::
				{0,0,0,1,0,0,0,0,0},
				{0,0,0,0,1,1,0,0,0},
				{0,0,0,1,1,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
//				:::PULSAR:::
//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
//				{0,0,0,1,1,1,0,0,0,1,1,1,0,0}, 
//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0}, 
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,0,0,1,1,1,0,0,0,1,1,1,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,1,1,1,0,0,0,1,1,1,0,0},
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,1,0,0,0,0,1,0,1,0,0,0,1,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,1,1,1,0,0,0,1,1,1,0,0},
//				{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			   };
		return board;
	}
	
	
=======

public class GameFunctions extends java.io.Reader{
	
	
	
	public Timeline timeline;
	private Color[] colors = new Color[] {Color.WHITE, Color.BLACK };
	private final int WIDTH = 100, HEIGHT = 100;
	public byte [][] board = new byte[WIDTH][HEIGHT];
	
	

>>>>>>> dag
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
	
	
<<<<<<< HEAD
	/**
	 * Itererer gjennom arrayet med nostet forloop
	 * Finner antall naboer med getNeighbourCount funksjonen
	 * Bruker denne informasjonen til a bestemme hvilke celler som skal leve i neste iterasjon.
	 * @return board med ferdikalkulerte verdier for neste iterasjon.
	 */
=======
	
>>>>>>> dag
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
	
<<<<<<< HEAD
	/**
	 * Regner antall naboer for celle med gitt rad og kolonneverdi
	 * @param row kolonneverdi for en gitt celle
	 * @param col radverdi for en gitt celle
	 * @param board henter boardet fordi vi trenger lengden pa begge arraydimensjonene.
	 * @return antall naboer for gitt celle
	 */
=======
	
>>>>>>> dag
	private static int getNeighbourCount(int row, int col, byte[][] board) {
		//Passe på at vi ikke går utenfor brettet
        int minRow = Math.max(0, row - 1);
        int maxRow = Math.min(board.length - 1, row + 1); 
<<<<<<< HEAD
        int minCol = Math.max(0, col - 1);  					
=======
        int minCol = Math.max(0, col - 1);
>>>>>>> dag
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
	
<<<<<<< HEAD
	/**
	 * @param slider_size Dynamisk verdi for cellestorrelse fra slider i gui.
	 * @param x x verdi for museklikk
	 * @param y y verdi for museklikk
	 */
	public void blackify (Slider slider_size, double x, double y) {
		double rows = x / slider_size.getValue();
		double cols = y / slider_size.getValue();
		
					
		if (board[(int) cols][(int) rows] == 1) {
			board[(int) cols][(int) rows] = 0;
		
=======
	
	public void blackify (Slider slider_size, double x, double y) {
		double rows = x / slider_size.getValue();
		double cols = y / slider_size.getValue();
		if (board[(int) cols][(int) rows] == 1) {
			board[(int) cols][(int) rows] = 0;
>>>>>>> dag
			} 
		else if (board[(int)cols][(int)rows] == 0) {			
				board[(int) cols][(int) rows] = 1;		
			}
<<<<<<< HEAD
		
		}
	
	/**
	 * Lager en Timeline.
	 * @param animationTime Input verdi for tid mellom hver iterasjon i timelinen.
	 * @return Timeline som kan holde animasjon gaende i ubegrenset tid.
	 */
	public Timeline createTimeline(float animationTime) {
=======
		}
	
	public Timeline createTimeline() {
>>>>>>> dag
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
	
<<<<<<< HEAD
	
=======
>>>>>>> dag
	public void applyRule() {
		board = nextGen();
	}
	
	public void clearCanvas(Canvas gol_canvas, Slider slider_size) { //Cleans up the canvas
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, board.length * slider_size.getMax(), board[0].length * slider_size.getMax());
	}
	
<<<<<<< HEAD
	/**
	 * Henter forh�ndsprodusert canvas i fxml for a kunne tegne grafikk i canvas.
	 * Setter opp et celle objekt som kan motta foretrukne variabler for celleanimasjon.
	 * (for)Looper gjennom hver posisjon i boardet finner koordinater for tegning av celler og tegner dem.
	 * @param gol_canvas Henter canvas fra fxml
	 * @param cellSize Storrelse pa celler
	 */
	public void drawBoard(Canvas gol_canvas, float cellSize) { //Draws the board
		GraphicsContext gc = gol_canvas.getGraphicsContext2D();
		Cell cell = new Cell();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				cell.x = j*cellSize;
				cell.y = i*cellSize;
				byte boardValue = board[i][j];
				cell.draw(gc, cellSize, cellSize, boardValue, colors);
=======
	
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
>>>>>>> dag
			}
		}
	}
	
<<<<<<< HEAD
	/**
	 * 
	 * @param gol_canvas
	 * @param slider_speed
	 * @param slider_size
	 */
	public void startTimeline(Canvas gol_canvas, float slider_speed, float slider_size) {
=======
	public void startTimeline(Canvas gol_canvas, double slider_speed, double slider_size) {
>>>>>>> dag
		/*
		 * Starte generering/forandring av celler
		 * */
		if (timeline != null) {
			timeline.stop();
		}
		
<<<<<<< HEAD
		timeline = createTimeline(slider_speed);
=======
		timeline = createTimeline();
>>>>>>> dag
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
<<<<<<< HEAD
	
	
	
=======



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
	
	public void readGameBoardFromURL(String url) throws IOException, PatternFormatException {
		try {
			URL destination = new URL(url);
			URLConnection conn = destination.openConnection();
			readGameBoard(new InputStreamReader(conn.getInputStream()));
		}
		catch (IOException ex) {
			Alert alertbox = new Alert(AlertType.ERROR);
			alertbox.setTitle("Error");
			alertbox.setHeaderText("URL error!");
			alertbox.setContentText(ex.getMessage());
			alertbox.showAndWait();
		}
	}
	
	public void readGameBoard(Reader r) throws IOException {
		Scanner inFile = new Scanner(r);
//		String testString = "";
//		String pattern = "(\\d+.*?\\d+)";
		inFile.nextLine();
		while (inFile.hasNext()) {
			String testString = inFile.nextLine();
			int x = testString.charAt(0);
			int y = testString.charAt(2);
			x = Character.getNumericValue(x);
			y = Character.getNumericValue(y);
			board[x][y] = 1;
		}
		inFile.close();
	}		
>>>>>>> dag
}