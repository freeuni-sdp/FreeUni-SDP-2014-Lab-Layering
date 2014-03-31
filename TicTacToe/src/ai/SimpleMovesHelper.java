package ai;

import java.util.ArrayList;
import java.util.List;

import model.Cell;
import model.ReadOnlyBoard;

/**
 * @author Vato Maskhulia
 *
 * Implementation of MovesHelper interface. Is used to compute
 * all the possible moves on board. Uses simple brute-force algorithm
 * to find all such cells.
 */
public class SimpleMovesHelper implements MovesHelper {
	
	// Class needs an active board instance where current game state is stored
	private ReadOnlyBoard board;

	/**
	 * Constructor.
	 * 
	 * @param board
	 */
	public SimpleMovesHelper(ReadOnlyBoard board) {
		this.board = board;
	}

	/**
	 * The implementation of MovesHelper method that returns
	 * a list of all the possible moves of current board state.
	 */
	public List<Cell> getPossibleMoves() {
		List<Cell> possibleMoves = new ArrayList<Cell>();

		if (!board.isFull()) {
			computeByBruteForce(possibleMoves);
		}

		return possibleMoves;
	}

	/*
	 * Checks every cell in board if that cell is empty adds to
	 * possibleMoves list
	 */
	private void computeByBruteForce(List<Cell> possibleMoves) {
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				Cell cell = new Cell(i, j);

				if (board.isEmpty(cell)) {
					possibleMoves.add(cell);
				}
			}
		}
	}
}