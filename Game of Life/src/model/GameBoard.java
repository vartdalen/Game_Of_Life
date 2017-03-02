package model;

public class GameBoard {

	 private boolean [][] board = { //Mønster
			{true,false,false,true}, 
			{false,true,true,false}, 
			{false,true,true,false}, 
			{true,false,false,true}
		   };

	
	


	public boolean[][] getBoard() {
		
		return board;
	}
	
	public void nextGeneration() {
		
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++){
				if (board[i][j] == true){
					board[i][j] = false;
				}
			}
		}
	}
	
	
}
