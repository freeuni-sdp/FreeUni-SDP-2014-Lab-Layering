package ai;

import java.util.List;

import logic.MoveListener;
import logic.Player;
import logic.PlayerColor;
import model.Cell;
import model.CellValue;
import model.ReadOnlyBoard;


/**
 * @author Zura Gventsadze
 *
 * Intelligent, but not perfect player.
 */
public class Intelligent32X32Player implements Player {
	
	private static final int SIZE = 32;
	
	private MovesHelper movesHelper;
	
	public Intelligent32X32Player(MovesHelper movesHelper) {
		this.movesHelper = movesHelper;
	}
	
	@Override
	public void makeMove(ReadOnlyBoard board, PlayerColor color, MoveListener moveListener) {
		moveListener.makeMove(getBestMove(board, color.toString()));
	}
	
	/**
	 * This method returns the next computed move's corresponding cell.
	 */
	private Cell getBestMove(ReadOnlyBoard board, String color) {
		List<Cell> possibleMoves = movesHelper.getPossibleMoves();

		if (possibleMoves.isEmpty()) {
			throw new IllegalStateException("Player can't make move - board is full.");
		}
		
		if (possibleMoves.size() == 1) {
			return possibleMoves.get(0);
		}
		
		return getBestCell(generateBoard(board), possibleMoves, color);
	}
	
	/**
	 * This method determines the best move by the following algorithm:
	 * For each possible move it finds out horizontal, vertical and diagonal
	 * lengthes of consecutive colors and chooses the maximum out of these three.
	 * If it's greater than maximum length found so far, this maximum's
	 * corresponding cell is saved as a global maximum.
	 */
	private Cell getBestCell(CellValue [][] board, List<Cell> possibleMoves, String color) {
		Cell bestCell = null;
		int maxRun = 0;
		
		for (Cell currentCell : possibleMoves) {
			int currentMax = findGreaterOutOfThree(getHorizontalRun(board, currentCell, color),
					getVerticalRun(board, currentCell, color), getDiagonalRun(board, currentCell, color));
			
			if (currentMax >= maxRun) {
				bestCell = currentCell;
				maxRun = currentMax;
			}
		}
		
		return bestCell;
	}
	
	/**
	 * Helper method for finding the horizontal length of consecutive colors.
	 */
	private int getHorizontalRun(CellValue [][] board, Cell cell, String color) {
		int run = 1;
		
		for (int i = cell.x - 1; i >= 0; i--) {
			if (board[i][cell.y].toString().equals(color)) {
				run++;
			} else {
				break;
			}
		}
		
		for (int i = cell.x + 1; i < SIZE; i++) {
			if (board[i][cell.y].toString().equals(color)) {
				run++;
			} else {
				break;
			}
		}
		
		return run;
	}
	
	/**
	 * Helper method for finding the vertical length of consecutive colors.
	 */
	private int getVerticalRun(CellValue [][] board, Cell cell, String color) {
		int run = 1;
		
		for (int i = cell.y - 1; i >= 0; i--) {
			if (board[cell.x][i].toString().equals(color)) {
				run++;
			} else {
				break;
			}
		}
		
		for (int i = cell.y + 1; i < SIZE; i++) {
			if (board[cell.x][i].toString().equals(color)) {
				run++;
			} else {
				break;
			}
		}
		
		return run;
	}
	
	/**
	 * Helper method for finding the diagonal length of consecutive colors.
	 */
	private int getDiagonalRun(CellValue [][] board, Cell cell, String color) {
		int run = 1;
		
		int x = cell.x - 1;
		int y = cell.y - 1;
		while (true) {
			if (!isPointInBounds(x, y) || !board[x][y].toString().equals(color)) {
				break;
			}
			run++;
			x--; y--;
		}
		
		x = cell.x + 1;
		y = cell.y + 1;
		while (true) {
			if (!isPointInBounds(x, y) || !board[x][y].toString().equals(color)) {
				break;
			}
			run++;
			x++; y++;
		}
		
		return run;
	}
	
	/**
	 * Helper method for finding out wether specified
	 * coordinates are in bounds of the board.
	 */
	private boolean isPointInBounds(int x, int y) {
		return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
	}
	
	private CellValue [][] generateBoard(ReadOnlyBoard board) {
		CellValue [][] boardArray = new CellValue[SIZE][SIZE];
		
		Cell cell;
		for (int x = 0; x < SIZE; x++) {
			for (int y = 0; y < SIZE; y++) {
				cell = new Cell(x, y);
				boardArray[x][y] = board.getValueAt(cell);
			}
		}
		
		return boardArray;
	}
	
	/**
	 * Helper method for finding the greater out of three integers.
	 */
	private static int findGreaterOutOfThree(int a, int b, int c) {
		if (a >= b && a >= c) {
			return a;
		} else if (b >= a && b >= c) {
			return b;
		}
		return c;
	}
}
