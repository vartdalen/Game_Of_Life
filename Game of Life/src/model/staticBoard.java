package model;


public class staticBoard implements Board {
	

	private final int WIDTH = 100, HEIGHT = 100;
	public byte [][] gameBoard;
	
	public staticBoard() {
		this.gameBoard = new byte[WIDTH][HEIGHT];
	}
	
	
//	public byte[][] nextGen() {
//		int numberOfRows = gameBoard.length;
//		int numberOfCols = gameBoard[0].length;
//		
//		byte[][] nextBoard = new byte[numberOfRows][];
//		
//		for(int row = 0; row < numberOfRows; row++) {
//			nextBoard[row] = new byte[numberOfCols];
//			for(int col = 0; col < numberOfCols; col++) {
//				int neighbourCount = getNeighbourCount(row, col, gameBoard);
//				
//				if(neighbourCount < 2){
//					nextBoard[row][col] = 0;
//				} else if (neighbourCount > 3) {
//					nextBoard[row][col] = 0;
//				} else if (neighbourCount == 3) {
//					nextBoard[row][col] = 1;
//				} else if (gameBoard[row][col] == 1){ 
//					if (neighbourCount == 2){ 
//						nextBoard[row][col] = 1;
//					}			
//				}
//			}
//		}
//		
//		return nextBoard;
//	}
	
	public void nextGen() {
		int numberOfRows = gameBoard.length;
		int numberOfCols = gameBoard[0].length;
		
		byte[][] nextBoard = new byte[numberOfRows][];
		
		for(int row = 0; row < numberOfRows; row++) {
			nextBoard[row] = new byte[numberOfCols];
			for(int col = 0; col < numberOfCols; col++) {
				int neighbourCount = getNeighbourCount(row, col, gameBoard);
				
				if(neighbourCount < 2){
					setCellState(row, col, false);
				} else if (neighbourCount > 3) {
					setCellState(row, col, false);
				} else if (neighbourCount == 3) {
					setCellState(row, col, true);
				} else if (getCellState(row, col) == 1){ 
					if (neighbourCount == 2){ 
						setCellState(row, col, true);
					}			
				}
			}
		}
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
	
	public byte [][] getStaticBoard() {
		return gameBoard;
	}
	
	private byte[] getBoundingBox() {
		byte[] boundingBox = new byte[4];
		boundingBox[0] = (byte) gameBoard.length;
		boundingBox[1] = 0;
		boundingBox[2] = (byte) gameBoard[0].length;
		boundingBox[3] = 0;
		for(byte i = 0; i < gameBoard.length; i++) {
			for(byte j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == 0) continue;
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
		if (gameBoard.length == 0) return "";
		
		byte[] boundingBox = getBoundingBox();
		String str = "";
		for(int i = boundingBox[0]; i <= boundingBox[1]; i++) {
			for(int j = boundingBox[2]; j <= boundingBox[3]; j++) {
				if (gameBoard[i][j] == 1) {
					str = str + "1";
				}
				else {
					str = str + "0";
				}
			}
		}
		return str;
	}
	
	
	public void setBoard(byte [][] innGameBoard) {
		gameBoard = innGameBoard;
	}


	@Override
	public int getLengthX() {
		return gameBoard.length;
	}
	
	@Override
	public int getLengthY() {
		return gameBoard[0].length;
	}


	@Override
	public byte getCellState(int x, int y) {
		if (gameBoard[x][y] == 1) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public void setCellState(int x, int y, boolean alive) {
		if(alive) {
			gameBoard[x][y] = 1;
		}
		else{
			gameBoard[x][y] = 0;
		}
	}
	
	
//	public byte[][] cloneByteArray() {
//		byte[][] output = new byte[gameBoard.length][gameBoard[0].length];
//		for(int i = 0; i < gameBoard.length; i++) {
//			for(int j = 0; j < gameBoard[i].length; j++) {
//				output[i][j] = gameBoard[i][j];
//			}
//		}
//		return output; 
//	}


}
