

package model;

@Deprecated
public class staticBoard implements Board {
	

	private final int WIDTH = 100, HEIGHT = 100;
	public byte [][] gameBoard;
	
	public staticBoard() {
		this.gameBoard = new byte[WIDTH][HEIGHT];
	}
	
	
	
	@Override
	public void nextGen() {
		byte[][] nextGen = new byte[getLengthX()][getLengthY()];
		for(int x = 0; x < getLengthX(); x++) {
			for(int y = 0; y < getLengthY(); y++) {
				int neighbourCount = getNeighbourCount(x, y);
				
				if(neighbourCount < 2){
					nextGen[x][y] = 0;
				} else if (neighbourCount > 3) {
					nextGen[x][y] = 0;
				} else if (neighbourCount == 3) {
					nextGen[x][y] = 1;
				} else if (getCellState(x, y) == 1){ 
					if (neighbourCount == 2){ 
						nextGen[x][y] = 1;
					}			
				}
			}
		}
		gameBoard = nextGen;
	}
	

	
	
	private int getNeighbourCount(int row, int col) {
		//Passe på at vi ikke går utenfor brettet
        int minRow = Math.max(0, row - 1);
        int maxRow = Math.min(getLengthX() - 1, row + 1); 
        int minCol = Math.max(0, col - 1);
        int maxCol = Math.min(getLengthY() - 1, col + 1); 	
		
        int neighbourCount = 0;
		
		for (int i = minRow; i <= maxRow; i++) {
            for (int j = minCol; j <= maxCol; j++) {
                if (i == row && j == col){
                    continue;//Må ikke telle seg selv som en nabo til seg selv...
                }
                
                if (getCellState(i, j) == 1) {
                	neighbourCount++;
                }	
            }
		}
        return neighbourCount;
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
