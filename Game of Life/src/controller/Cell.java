package controller;

public class Cell {
	
	int y = 1;
	int x = 1;
	public int size = 5;
	public int isAlive = 0;
	public int neighbourCount;
	
	private double [][] board = { //Mønster
			{0,0,0,0}, 
			{0,1,0,0}, 
			{0,0,0,0}, 
			{0,0,0,0}
		   };
	
//	public Cell(int newStatus, int newSize, int neighbours) {
//		this.isAlive = newStatus;
//		this.size = newSize;
//		this.neighbourCount = neighbours;
//	}
	
	public int neighbours() {
		neighbourCount = 0;
		
		for(int i = y-1; i <= y+1; i++) {
			for(int j = x-1; j <= x+1; x++) {
				if(board[i][j] == 1) { //y[i].isAlive && x[j].isAlive == 1
					neighbourCount += 1;
				}
			}
		}
		
		if(neighbourCount >= 2 && neighbourCount <= 3) {
			isAlive = 1;
		} else {
			isAlive = 0;
		}
		return isAlive;
	}
	
	public void deadOrAlive() {
		//Hvis Cell har 2 eller flere naboer og det samme som, eller mindre enn 4 naboer = true
		if(neighbourCount >= 2 && neighbourCount <= 3) {
			isAlive = 1;
		} else {
			isAlive = 0;
		}
	}
	
}
