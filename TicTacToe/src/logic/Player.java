package logic;



public interface Player {

	void makeMove(ReadOnlyBoard board, PlayerColor color, MoveListener moveListener);
}