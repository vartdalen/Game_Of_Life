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
	
	
	public void applyRule(byte[][] board) {
		nextGeneration(board);
//		addBlack();
	}
	
	
	
	public Timeline createTimeline(float animationTime) {
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		
		return timeline;
	}
}
