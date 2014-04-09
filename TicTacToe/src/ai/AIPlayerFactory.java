package ai;

import java.util.Random;

import logic.Player;
import model.ReadOnlyBoard;

/**
 * @author Vato Maskhulia
 *
 * Factory class that must be used to create AI players.
 */
public class AIPlayerFactory {

	/**
	 * Instantiates and returns new artificial player that chooses
	 * moves randomly.
	 *
	 * Every AI player implementation will need a list of all the possible
	 * moves in order to compute the next one, so the right thing to do is to
	 * have a separate object that takes care of this problem. In this case,
	 * computation of all the possible is abstracted in MovesHelper interface.
	 * It's current implementation - SimpleMovesHelper has a dependency on
	 * ReadOnlyBoard object.
	 */
	public Player getRandom3x3Player(ReadOnlyBoard board) {
		checkBoard(board);
		MovesHelper movesHelper = new SimpleMovesHelper(board);
		Random random = new Random(0);
		return new RandomPlayer(movesHelper, random);
	}

	public Player getInteligent3X3Player(ReadOnlyBoard board) {
		checkBoard(board);
		MovesHelper movesHelper = new SimpleMovesHelper(board);
		return new Inteligent3X3Player(movesHelper);
	}
	
	/**
	 * Factory method for Intelligent32X32Player, first of its kind, but not so dumb, really.
	 */
	public Player getIntelligent32X32Player(ReadOnlyBoard board) {
		checkBoard(board);
		MovesHelper movesHelper = new SimpleMovesHelper(board);
		return new Intelligent32X32Player(movesHelper);
	}
	
	private void checkBoard(ReadOnlyBoard board) {
		if (board.getSize() != 3)
			throw new IllegalArgumentException("Board size must be 3.");
	}
}