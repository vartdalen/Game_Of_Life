package model;

import java.util.ArrayList;
/**
 * V�rt dynamiske board kan visualiseres som en ArrayList
 * som inneholder dynamisk antall ArrayLister (y-indekser),
 * som igjen inneholder dynamisk antall byteverdier (x-indekser).
 */
public class dynamicBoard implements Board {
	/**
	 * int boardSize: Inisiell maks storrelse paa boardet.
	 */
	public int boardSize;
	/**
	 * Deklarerer tomt ArrayList board som er klar til aa motta et dynamisk grid.
	 */
	public ArrayList<ArrayList<Byte>> gameBoard = new ArrayList<ArrayList<Byte>>(boardSize);
	/**
	 * Konstruktor.
	 */
	public dynamicBoard (int size) {
		boardSize = size;
		initBoard();
	}
	/**
	 * Lager todimensionalt ArrayList grid og putter inn i gameBoard.
	 */
	public void initBoard() {
		for (int i = 0; i < boardSize; i++) {	
				gameBoard.add(new ArrayList<Byte>(boardSize));
				for(int j = 0; j < boardSize; j++) {
					gameBoard.get(i).add((byte) 0);
				}
		}
	}

	@Override
	/**
	 * Itererer gjennom arrayet med nostet forloop
	 * Hvis brettet prover � utvide seg utenfor begrensningen, utvider det seg.
	 * Finner antall naboer med getNeighbourCount funksjonen
	 * Bruker denne informasjonen til aa bestemme hvilke celler som skal leve i neste iterasjon.
	 */
	public void nextGen() {
		ArrayList<ArrayList<Byte>> nextGen = new ArrayList<ArrayList<Byte>>(getLengthX());
		for (int i = 0; i < getLengthX(); i++) {	
			nextGen.add(new ArrayList<Byte>(getLengthY()));
			for(int j = 0; j < getLengthY(); j++) {
				nextGen.get(i).add((byte) 0);
			}
		}
		
		for (int x = 0; x < getLengthX(); x++) {
			for (int y = 0; y < getLengthY(); y++) {
				
				if ((x == (getLengthX()-1) || y == (getLengthY()-1)) && getCellState(x, y) == 1 && getLengthX() < 170) {
					int amount = 1;
					amount++;
					nextGen = expand(amount, amount, nextGen);
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
	
	/**
	 * Forstorrer boardet.
	 * @param x lengden på horisontal 
	 * @param y lengden på vertikal 
	 * @param nextGen neste generasjon
	 * @return neste generasjon med forstørret brett
	 */
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
	

	/**
	 * Regner antall naboer for celle med gitt rad og kolonneverdi
	 * 
	 * @param row kolonneverdi for en gitt celle
	 * @param col radverdi for en gitt celle
	 * @return antall naboer for gitt celle
	 */
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

	public void nextGenerationPrintPerformance() {
		long start = System.currentTimeMillis();
		nextGen();
		long elapsed = System.currentTimeMillis() - start; 
		System.out.println("Counting time (ms): " + elapsed);
	}
	
	
	@Override
	/**
	 * Gjor om alle verdiene i boardet til en string.
	 * @return stringverdi av verdien i en celle.
	 */
	public String toString() {
		String returnString = "";
		for(int i = 0; i < getLengthX(); i++) {
			for(int j = 0; j < getLengthY(); j++) {
				returnString = returnString + getCellState(i, j);
			}
		}
		return returnString;
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
