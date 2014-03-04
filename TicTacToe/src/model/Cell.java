package model;


public class Cell {
	
	public int x;
	public int y;

	public Cell(int x, int y) {
		if (x < 0)
			throw new IllegalArgumentException("X is out of range.");
		if (y < 0)
			throw new IllegalArgumentException("Y is out of range.");

		this.x = x;
		this.y = y;
	}
}