package model;

public interface Generation {

	
	
	public static void nextGeneration(boolean[][] gameBoard) {
		
		for(int i = 0; i<gameBoard.length; i++) {
			for(int j = 0; j<gameBoard[i].length; j++){
				if (gameBoard[i][j] == true){
					gameBoard[i][j] = false;
				}
			}
		}
	}
}
