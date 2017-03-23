package model;

import java.awt.MouseInfo;

import controller.gui_controller;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.util.Duration;
import model.Cell;


public class GameFunctions {
	
	
	public byte [][] board = initBoard();
	
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
	
	
	public void blackify (Slider slider_size) {
		double cols, rows;
		rows = getMouseXCoordinate(slider_size);
		cols = getMouseYCoordinate(slider_size);
					
		if (board[(int) cols][(int) rows] == 1) {
			board[(int) cols][(int) rows] = 0;
		
			} 
		else if (board[(int)cols][(int)rows] == 0) {			
				board[(int) cols][(int) rows] = 1;		
			}
		
		}
	
	public double getMouseXCoordinate(Slider slider_size) {
		double RowCo;
		RowCo = MouseInfo.getPointerInfo().getLocation().getX();
		return RowCo / slider_size.getValue();

	}
	
	public double getMouseYCoordinate(Slider slider_size) {
		double ColCo;
		ColCo = MouseInfo.getPointerInfo().getLocation().getY();
		return ColCo / slider_size.getValue();
		
	}
	
	
	public Timeline createTimeline(float animationTime) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		
		return timeline;
	}
}
