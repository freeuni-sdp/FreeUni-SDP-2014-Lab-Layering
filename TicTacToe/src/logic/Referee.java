package logic;
import model.ReadOnlyBoard;


/**
 * @author Tamar Keshelava. 
 * 		Referee checks who has won the game.
 */
public interface Referee {
	
	GameStatus getGameStatus(ReadOnlyBoard board);

}