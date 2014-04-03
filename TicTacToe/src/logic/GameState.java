package logic;

import model.ReadOnlyBoard;

/**
 * @author Sandro Dolidze
 * 
 * A simple class for wrapping two objects (ReadOnlyBoard, PlayerColor) together.
 */
public class GameState {
	private final ReadOnlyBoard board;
	private final PlayerColor currentPlayer;
	
	public GameState(ReadOnlyBoard board, PlayerColor currentPlayer){
		this.board         = board;
		this.currentPlayer = currentPlayer;
	}

	public ReadOnlyBoard getBoard() {
		return board;
	}

	public PlayerColor getCurrentPlayer() {
		return currentPlayer;
	}
}