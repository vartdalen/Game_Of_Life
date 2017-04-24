package model;

import java.util.ArrayList;

public class dynamicBoard implements Board {
	
	public ArrayList<ArrayList<Byte>> gameBoard;
	
	public dynamicBoard () {
		createBoard();
	}
	
	public void createBoard() {
		for (int i = 0; i < 100; i++) {
				gameBoard.add(new ArrayList<Byte>(0));
		}
	}

	@Override
	public int getLengthX() {
		return gameBoard.get(0).size();
	}

	@Override
	public int getLengthY() {
		return gameBoard.get(0).size();
	}

	@Override
	public byte getCellState(int x, int y) {
		return 0;
	}

	@Override
	public void setCellState(int x, int y, boolean alive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyRule() {
		
	}
	


}
