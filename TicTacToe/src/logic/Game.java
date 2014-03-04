package logic;

import model.*;

public class Game {

	private final Player xPlayer;
	private final Player oPlayer;
	private final Board board;
	private final Referee referee;
	private final Presenter presenter;

	public Game(Board board, Player xPlayer, Player oPlayer, Referee referee,
			Presenter presenter) {
		this.board = board;
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
		this.referee = referee;
		this.presenter = presenter;
	}

	public void play() {
		GameStatus gameStatus;
		PlayerColor currentColor = PlayerColor.X;

		MoveListener xMoveListener = new MoveListener() {
			public void makeMove(Cell cell) {
				board.makeMove(cell, CellValue.X);
			}
		};

		MoveListener oMoveListener = new MoveListener() {
			public void makeMove(Cell cell) {
				board.makeMove(cell, CellValue.O);
			}
		};

		do {
			gameStatus = referee.getGameStatus(board);
			presenter.show(board, gameStatus);
			if (gameStatus != GameStatus.INPROGRESS) {
				break;
			}

			switch (currentColor) {
			case X:
				xPlayer.makeMove(board, currentColor, xMoveListener);
				break;
			case O:
				oPlayer.makeMove(board, currentColor, oMoveListener);
				break;
			}

			currentColor = swapColor(currentColor);

		} while (true);
	}

	private PlayerColor swapColor(PlayerColor playerColor) {
		switch (playerColor) {
		case X:
			return PlayerColor.O;
		case O:
			return PlayerColor.X;
		}
		throw new IllegalArgumentException("There is no color for this value.");
	}
}