
package model;

@Deprecated
/**
 *Todimensionalt byte array legger grunnen for et statisk board.
 */
public class staticBoard implements Board {
	

	private final int WIDTH = 100, HEIGHT = 100;
	public byte [][] gameBoard;
	
	public staticBoard() {
		this.gameBoard = new byte[WIDTH][HEIGHT];
	}
	
	
	
	@Override
	/**
	 * Itererer gjennom arrayet med nostet forloop
	 * Finner antall naboer med getNeighbourCount funksjonen
	 * Bruker denne informasjonen til aa bestemme hvilke celler som skal leve i neste iterasjon.
	 */
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
	

	
	/**
	 * Regner antall naboer for celle med gitt rad og kolonneverdi
	 * 
	 * @param row kolonneverdi for en gitt celle
	 * @param col radverdi for en gitt celle
	 * @return antall naboer for gitt celle
	 */
	private int getNeighbourCount(int row, int col) {
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
	
	/**
	 * Brukes i testing for � sjekke om brettet fungerer riktig. 
	 * @return minste firkant nodvendig for � tegne boardet.
	 */
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
	
	/**
	 * Brukes i testing for � sjekke om brettet fungerer riktig.
	 * @return hele griddet sine verdier printet i en string.
	 */
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
<<<<<<< HEAD
	

=======
>>>>>>> b0b6fd1af3b01634e1d5fe1b2bd14c895645a1c0

}
