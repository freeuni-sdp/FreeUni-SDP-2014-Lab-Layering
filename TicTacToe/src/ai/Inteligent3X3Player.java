package ai;

import logic.MoveListener;
import logic.Player;
import logic.PlayerColor;
import model.Cell;
import model.ReadOnlyBoard;

public class Inteligent3X3Player implements Player{

	private MovesHelper movesHelper;
	
	public Inteligent3X3Player(MovesHelper movesHelper){
		this.movesHelper = movesHelper;
	}
	
	@Override
	public void makeMove(ReadOnlyBoard board, PlayerColor color, MoveListener moveListener) {
		Cell bestMove = getBestMove();
		moveListener.makeMove(bestMove);
	}

	private Cell getBestMove() {
		//AI algorithm will be implemented here
		return null;
	}

}
