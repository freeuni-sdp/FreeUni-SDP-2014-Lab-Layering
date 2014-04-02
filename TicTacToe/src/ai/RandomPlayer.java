package ai;

import java.util.List;
import java.util.Random;

import logic.MoveListener;
import logic.Player;
import logic.PlayerColor;
import model.Cell;
import model.ReadOnlyBoard;

/**
 * @author Vato Maskhulia
 *
 * Artificial player that uses a random function to tell next move.
 */
public class RandomPlayer implements Player {
	
	// Is needed to tell all the possible moves
	private MovesHelper movesHelper;

	// Random generator for computing next move
	private Random random;

	/**
	 * Constructor.
	 *
	 * @param movesHelper
	 */
	/*
	 * I have added injection for random
	 * Random shoudn't be created in RandomPlayer 
	 * because then make move randomly can't be tested from outside
	 */
	public RandomPlayer(MovesHelper movesHelper, Random random) {
		this.movesHelper = movesHelper;
		this.random = random;
	}

	/**
	 * Implementation of Player interface method.
	 * Returns next available move of this player. Move must be valid.
	 */
	public void makeMove(ReadOnlyBoard board, PlayerColor playerColor, MoveListener moveListener) {
		Cell nextMove = getNextMove();
		moveListener.makeMove(nextMove);
	}

	/**
	 * Uses movesHelper to get all the cells that are empty.
	 * If there are no such cells throws Exception.
	 * Chooses one cell from that list randomly.
	 */
	private Cell getNextMove() {
		List<Cell> moves = movesHelper.getPossibleMoves();

		if (moves.size() == 0) {
			throw new IllegalStateException("Player can't make move - board is full.");
		}

		int index = random.nextInt(moves.size()); // get move randomly

		return moves.get(index);
	}
}