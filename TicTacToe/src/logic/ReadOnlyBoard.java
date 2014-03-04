package logic;

import model.Board;
import model.Cell;
import model.CellValue;



public class ReadOnlyBoard {
	
	private final Board board;
	
	public ReadOnlyBoard(Board board) {
		this.board=board;
	}
	
	public boolean isFull() {
		return board.isFull();
	}
	
	public CellValueWrapped getValueAt(CellWrapped cellWrapped) {
		Cell cell  = cellWrapped.getCell();
		CellValue cellValue = board.getValueAt(cell);
		return CellValueWrapped.fromCellValue(cellValue); 
	}
	
	public int getSize() {
		return board.getSize();
	}
	
	public boolean isEmpty(CellWrapped cellWrapped) {
		return board.isEmpty(cellWrapped.getCell());
	}

}
