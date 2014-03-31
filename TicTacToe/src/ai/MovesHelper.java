package ai;

import java.util.List;

import model.Cell;

/**
 * @author Vato Maskhulia
 *
 * This interface is responsible for computing all available moves
 * of the player.
 *
 * Every AI player needs this kind of list and must depend on this object.
 */
public interface MovesHelper {

	/**
	 * Returns list of cells that are available for player.
	 */
	public List<Cell> getPossibleMoves();
}