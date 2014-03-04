package logic;

import model.*;


public class CellWrapped {

	private Cell cell;
	
	public CellWrapped(int x, int y) {
		this(new Cell(x, y));
	}

	protected CellWrapped(Cell cell) {
		this.cell = cell;
	}
	
	protected Cell getCell() {
		return cell;
	}
	
	public int getX() {
		return cell.x;
	}
	
	public int getY() {
		return cell.y;
	}
}