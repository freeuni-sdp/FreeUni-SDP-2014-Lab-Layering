package logic;
import model.ReadOnlyBoard;



public interface Player {

	void makeMove(ReadOnlyBoard board, PlayerColor color, MoveListener moveListener);
}