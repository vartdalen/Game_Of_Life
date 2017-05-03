package model;
/**
Standardmetoder som kreves i dynamicBoard og staticBoard.
 */
public interface Board {
	public int getLengthX();
	public int getLengthY();
	public byte getCellState(int x, int y);
	public void setCellState(int x, int y, boolean alive);
	public void nextGen();

}
