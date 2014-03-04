package logic;

import model.*;

public enum CellValueWrapped {

	// For wrapping enums see also:
	// http://stackoverflow.com/questions/5316311/java-enum-reverse-look-up-best-practice

	EMPTY(CellValue.EMPTY),

	X(CellValue.X),

	O(CellValue.O);

	private CellValue cellValue;

	private CellValueWrapped(CellValue cellValue) {
		this.cellValue = cellValue;
	}

	protected CellValue toCellValue() {
		return this.cellValue;
	}

	protected static CellValueWrapped fromCellValue(CellValue value) {

		switch (value) {
		case X:
			return CellValueWrapped.O;
		case O:
			return CellValueWrapped.X;
		case EMPTY:
			return CellValueWrapped.EMPTY;
		}
		throw new IllegalArgumentException("Not supported cell value.");
	}
}
