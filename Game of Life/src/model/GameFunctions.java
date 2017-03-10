package model;

import controller.gui_controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.Cell;


public class GameFunctions {

	
	public byte [][] board = { //Mønster
			{1,0,0,1}, 
			{0,1,1,0}, 
			{0,1,1,0}, 
			{1,0,0,1}
		   };
	
	
	
	public byte[][] cloneByteArray(byte[][] orig) {
		byte[][] output = new byte[orig.length][orig[0].length];
		for(int i = 0; i < orig.length; i++) {
			for(int j = 0; j < orig[i].length; j++) {
				output[i][j] = orig[i][j];
			}
		}
		return output;
	}
	
	
	public int countNeighbours(int row, int col, byte [][]board) {
		byte cellStatus = board[row][col]; //setter cellens verdi til verdien på board-cord x,y: Dersom verdien er 1, celle = Alive. Dersom verdien = 0, celle = Dead.
		int neighbourCount = 0;
		
		//Sjekker naboer rundt den gjeldende cellen.
		for(int i = col-1; i <= col+1; i++) {
			for(int j = row-1; j <= row+1; j++) {
				if(board[i][j] == 1 && (i != col && j != row)) { //Dersom  verdi = 1 OG cellen som sjekkes ikke er seg selv = øk nabo-teller med 1.		|     y[i].isAlive && x[j].isAlive == 1
					neighbourCount += 1;
				}
			}
		}
		
		// Celle vekkes til live av 3 naboer - holdes i live ved 2 eller 3 naboer, og dør dersom den har mindre enn 2 eller flere enn 3 naboer.
		
		if(cellStatus == 0 && neighbourCount == 3) { 										//Dersom Cellen er død, og har 3 naboer så vekkes den til live.
			cellStatus = 1;
		} else if(cellStatus == 1 && (neighbourCount == 2 || neighbourCount == 3)) { 		//Dersom cellen er i live OG har 2 eller 3 naboer = holdes i live.
			cellStatus = 1;
		} else if (neighbourCount < 2 || neighbourCount > 3) { 								//Dersom cellen har mindre enn 2 eller flere enn 3 naboer = cellen dør.
			cellStatus = 0;
		}
		
		return cellStatus;
	}
	
	
	
	public void nextGeneration(byte [][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) {
					
					board[i][j] = 0;
					
				} else if (board[i][j] == 0) {
					
					board[i][j] = 1;
					
				}
			}
		}
	}
	
	
//	public void applyRule(byte[][] board) {
////		nextGeneration(board);
////		countNeighbours(4,4,board);
////		addBlack();
//	}
	
	
	
	public Timeline createTimeline(float animationTime) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		return timeline;
	}
}
