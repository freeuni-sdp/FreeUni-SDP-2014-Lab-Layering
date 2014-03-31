package ai;

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
		if (board.getSize() != 3)
			throw new IllegalArgumentException("Board size must be 3.");

		MovesHelper movesHelper = new SimpleMovesHelper(board);
		return new RandomPlayer(movesHelper);
	}
}