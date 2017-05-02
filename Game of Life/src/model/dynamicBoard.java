package model;

import java.util.ArrayList;
import java.util.List;

public class dynamicBoard implements Board {
	
	int boardSize = 20;
	public ArrayList<ArrayList<Byte>> gameBoard = new ArrayList<ArrayList<Byte>>(boardSize);
	
	public dynamicBoard () {
		initBoard();
	}
	
	public void initBoard() {
		for (int i = 0; i < boardSize; i++) {	
				gameBoard.add(new ArrayList<Byte>(boardSize));
				for(int j = 0; j < boardSize; j++) {
					gameBoard.get(i).add((byte) 0);
				}
		}
	}
	
	
//	@Override
//	public void nextGen() {
//		System.out.println(getLengthY());
//		System.out.println(getLengthX());
//		System.out.println(boardSize);
//		
//		ArrayList<ArrayList<Byte>> nextGen = new ArrayList<ArrayList<Byte>>(getLengthX());
//		for (int i = 0; i < getLengthX(); i++) {	
//			nextGen.add(new ArrayList<Byte>(getLengthY()+1));
//			for(int j = 0; j < getLengthX(); j++) {
//				nextGen.get(i).add((byte) 0);
//			}
//		}
//		for (int x = 0; x < getLengthX(); x++) {
//			for (int y = 0; y < getLengthY(); y++) {
//				
//				int neighbours = getNeighbourCount(x,y);
//				
//				if(neighbours < 2){
//					nextGen.get(y).set(x, (byte) 0);
//				} else if (neighbours > 3) {
//					nextGen.get(y).set(x, (byte) 0);
//				} else if (neighbours == 3) {
//					nextGen.get(y).set(x, (byte) 1);
//				} else if (getCellState(x, y) == 1){ 
//					if (neighbours == 2){ 
//						nextGen.get(y).set(x, (byte) 1);
//					}			
//				}
//				if ((x == (getLengthX()-1) || y == (getLengthY()-1)) && getCellState(x, y) == 1) {
//					
//				}
//				
//			}
//		}
//		
//		gameBoard = nextGen;
//	}
	
	@Override
	public void nextGen() {
		System.out.println(getLengthY());
		System.out.println(getLengthX());
		
	
		
		ArrayList<ArrayList<Byte>> nextGen = new ArrayList<ArrayList<Byte>>(getLengthX());
		for (int i = 0; i < getLengthX(); i++) {	
			nextGen.add(new ArrayList<Byte>(getLengthY()));
			for(int j = 0; j < getLengthY(); j++) {
				nextGen.get(i).add((byte) 0);
			}
		}
		
		System.out.println("Height: " + nextGen.size() + ", width: " + nextGen.size());
		
		for (int x = 0; x < getLengthX(); x++) {
			for (int y = 0; y < getLengthY(); y++) {
				
				if ((x == (getLengthX()-1) || y == (getLengthY()-1)) && getCellState(x, y) == 1) {
					int amount = 1;
					amount++;
					nextGen = expand(amount, amount, nextGen);
					System.out.println("Height: " + nextGen.size() + ", width: " + nextGen.size());
				}
			}
		}
		
		
		for (int x = 0; x < getLengthX(); x++) {
			for (int y = 0; y < getLengthY(); y++) {

				int neighbours = getNeighbourCount(x,y);
				if(neighbours < 2){
					nextGen.get(y).set(x, (byte) 0);
				} 
				else if (neighbours > 3) {
					nextGen.get(y).set(x, (byte) 0);
				} 
				else if (neighbours == 3) {
					nextGen.get(y).set(x, (byte) 1);
				} 
				else if (getCellState(x, y) == 1){ 
					if (neighbours == 2){ 
						nextGen.get(y).set(x, (byte) 1);
					}			
				}
			}
		}
		gameBoard = nextGen;
	}
	
	private static ArrayList<ArrayList<Byte>> expand(int x, int y, ArrayList<ArrayList<Byte>> nextGen) {
        int newHeight = nextGen.size() + y;
        int newWidth = nextGen.get(0).size() + x;

        ArrayList<ArrayList<Byte>> out = new ArrayList<>();
        for (int i = 0; i < newHeight; i++) {
            out.add(new ArrayList<Byte>());
            for (int j = 0; j < newWidth; j++) {
                out.get(i).add((byte) 0);
            }
        }

        return out;
    }

	
	public int getNeighbourCount(int row, int col) {
		
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
	

	

	@Override
	public int getLengthX() {
		return gameBoard.get(0).size();
	}

	@Override
	public int getLengthY() {
		return gameBoard.size();
	}

	@Override
	public byte getCellState(int x, int y) {
		return gameBoard.get(y).get(x);
	}

	@Override
	public void setCellState(int x, int y, boolean alive) {
		if (alive) {
			gameBoard.get(y).set(x, (byte)1);
		}
		else {
			gameBoard.get(y).set(x, (byte)0);
		}
	}
	


}
