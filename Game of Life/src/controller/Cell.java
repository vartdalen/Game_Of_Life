package controller;

public class Cell {
	
	double y;
	double x;
	public int size = 5;
	public byte cellStatus;  //1 = Alive, 0 = Dead.
	public int neighbourCount;
	private byte [][] board;
	
//	public Cell(int newStatus, int newSize, int neighbours) {
//		this.isAlive = newStatus;
//		this.size = newSize;
//		this.neighbourCount = neighbours;
//	}
	
	public int getCellStatus(byte row, byte col) {
		cellStatus = board[row][col]; //setter cellens verdi til verdien på board-cord x,y: Dersom verdien er 1, celle = Alive. Dersom verdien = 0, celle = Dead.
		neighbourCount = 0;
		
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
	
//	public int drawOnMousePosition() {
//		if(isAlive == 1) {
//			//<insert drawFunction>
//		}
//	}
	
}
