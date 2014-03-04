package logic;
import model.ReadOnlyBoard;



public interface Referee {
	
	GameStatus getGameStatus(ReadOnlyBoard board);

}