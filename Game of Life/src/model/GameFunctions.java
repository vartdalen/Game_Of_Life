package model;

import controller.gui_controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import model.Cell;


public class GameFunctions {

	

	
	
	
	public byte[][] cloneByteArray(byte[][] orig) {
		byte[][] output = new byte[orig.length][orig[0].length];
		for(int i = 0; i < orig.length; i++) {
			for(int j = 0; j < orig[i].length; j++) {
				output[i][j] = orig[i][j];
			}
		}
		return output;
	}
	
	
//	protected void nextGen(byte row, byte col, byte[][] board) {
//		byte[][] output = new byte[col][row]; //setter cellens verdi til verdien på board-cord x,y: Dersom verdien er 1, celle = Alive. Dersom verdien = 0, celle = Dead.
//		int teller = 0;
//		
//		//Sjekker naboer rundt den gjeldende cellen.
//		for(byte x = 1; x <= col-1; x++) {
//			for(byte y = 1; y <= row-1; y++) {
//				
//				byte neighbourCount = 0;
//				
//				for (byte i = -1; i <= 1; i++){
//					for(byte j = -1; j <= 1; j++){
////						if(board[i][j] == 1) { //Dersom  verdi = 1 OG cellen som sjekkes ikke er seg selv = øk nabo-teller med 1.		|     y[i].isAlive && x[j].isAlive == 1
//							neighbourCount += board[x+i][y+j];
//
//						
//						System.out.println("ny nabocelle");
//						System.out.println(neighbourCount);
//						
//					}
//				}
//				
//				
//				teller ++;
//				System.out.println("NESTE CELLE " + teller);
//				neighbourCount -= board[x][y];
//				
//				
//				if((board[x][y] == 1) && (neighbourCount < 2)){
//					output[x][y] = 0; 
//				}
//				else if ((board[x][y] == 1) && (neighbourCount > 3)){
//					output[x][y] = 0;
//				}
//				else if ((board[x][y] == 0) && (neighbourCount == 3)){
//					output[x][y] = 1;
//				}
//				else {
//					output[x][y] = board[x][y];
//				}
//				
//				
//			}
//			board = output;
//			
//		}
		
		
	public int nextGen(byte row, byte col, byte[][] board) {
		int minRow = Math.max(0, row-1);
		int maxRow = Math.min(board.length, row+1);
		int minCol = Math.min(0, col-1);
		int maxCol = Math.min(board.length, col+1);
		
		int neighbourCount = 0;
		
		for(int i = minRow; i < maxRow; i++) {
			for (int j = minCol; j < maxCol; j++) {
				if (i == row && j == col) {
					continue;
				}
				if(board[i][j] == 1) {
					neighbourCount ++;
				}
				
				if((board[i][j] == 1) && (neighbourCount < 2)){
					board[i][j] = 0; 
				}
				else if ((board[i][j] == 1) && (neighbourCount > 3)){
					board[i][j] = 0;
				}
				else if ((board[i][j] == 0) && (neighbourCount == 3)){
					board[i][j] = 1;
				}
				
			}
		}
		return neighbourCount;
	}
		
		// Celle vekkes til live av 3 naboer - holdes i live ved 2 eller 3 naboer, og dør dersom den har mindre enn 2 eller flere enn 3 naboer.
		
//		if(cellStatus == 0 && neighbourCount == 3) { 										//Dersom Cellen er død, og har 3 naboer så vekkes den til live.
//			cellStatus = 1;
//		} else if(cellStatus == 1 && (neighbourCount == 2 || neighbourCount == 3)) { 		//Dersom cellen er i live OG har 2 eller 3 naboer = holdes i live.
//			cellStatus = 1;
//		} else if (neighbourCount < 2 || neighbourCount > 3) { 								//Dersom cellen har mindre enn 2 eller flere enn 3 naboer = cellen dør.
//			cellStatus = 0;
//		}
//		
//		return cellStatus;
//	}
	
	
	
//	public void nextGeneration(byte[][] board) {
//		
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				board[i][j] = countNeighbours(i,j, board);
//			}
//		}
//	}
	
	
//	public void nextGeneration(byte [][] board) {
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board[i].length; j++) {
//				if (board[i][j] == 1) {
//					
//					board[i][j] = 0;
//					
//				} else if (board[i][j] == 0) {
//					
//					board[i][j] = 1;
//					
//				}
//			}
//		}
//	}

	
//	public void applyRule(byte[][] board) {
//		nextGeneration(board);
////		addBlack();
//	}
	
	
	
	public Timeline createTimeline(float animationTime) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(1);
		
		return timeline;
	}
}
