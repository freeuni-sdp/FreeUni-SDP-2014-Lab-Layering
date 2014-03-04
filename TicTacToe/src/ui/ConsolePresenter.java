package ui;



import java.io.PrintStream;

import logic.*;

public class ConsolePresenter implements Presenter {

	private final PrintStream out;

	public ConsolePresenter(PrintStream out) {
		this.out = out;
	}

	public void show(ReadOnlyBoard board, GameStatus gameStatus) {
		presentBoard(board);
		presentGameStatus(gameStatus);
	}

	private void presentGameStatus(GameStatus gameStatus) {
		switch (gameStatus) {
		case XISWINNER:
			out.println("X is the winner!");
			break;

		case OISWINNER:
			out.println("O is the winner!");
			break;

		case DRAW:
			out.println("It is a draw!");
			break;
		default:
			break;
		}
	}

	private void presentBoard(ReadOnlyBoard board) {
		out.println();
		out.println(" |0|1|2");
		out.println("-+-+-+-");

		for (int i = 0; i < board.getSize(); i++) {
			printRow(board, i);
			
			if (i != board.getSize() - 1) {
				out.println("-+-+-+-");
			}
		}
		out.println();
	}

	private void printRow(ReadOnlyBoard board, int i) {
		out.print(i);
		out.print("|");

		for (int j = 0; j < board.getSize(); j++) {
			CellWrapped cell = new CellWrapped(j, i);
			
			printCell(board, cell);

			if (j != board.getSize() - 1) {
				out.print("|");
			}
		}
		out.println();
	}

	private void printCell(ReadOnlyBoard board, CellWrapped cell) {
		CellValueWrapped currentValue = board.getValueAt(cell);
		switch (currentValue) {
		case X:
			out.print("x");
			break;
		case O:
			out.print("o");
			break;
		case EMPTY:
			out.print(" ");
			break;
		}
	}
}