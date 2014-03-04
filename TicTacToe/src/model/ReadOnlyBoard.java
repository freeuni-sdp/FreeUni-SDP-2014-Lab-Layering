package model;



public interface ReadOnlyBoard {
	
	boolean isFull();
	CellValue getValueAt(Cell cell);
	int getSize();
	boolean isEmpty(Cell cell);

}
