package ui;

import logic.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintStream;

public class ConsolePlayer implements Player {

	private final Scanner scanner;
	private final PrintStream out;

	public ConsolePlayer(Scanner scanner, PrintStream out) {
		this.scanner = scanner;
		this.out = out;
	}

	public void makeMove(ReadOnlyBoard board, PlayerColor playerColor, MoveListener moveListener) {
		
		printWhoIsOnTurn(playerColor);
		CellWrapped nextMove = nextMoveUntilLegal(board);
		moveListener.makeMove(nextMove);
	
	}

	private void printWhoIsOnTurn(PlayerColor playerColor) {
		switch (playerColor) {
		case X:
			out.println("Player X is on turn");
			break;
		case O:
			out.println("Player O is on turn");
			break;
		}
	}

	private CellWrapped nextMoveUntilLegal(ReadOnlyBoard board) {
		while (true) {
			try {
				int x = nextIntUntilOk("X=? ");
				int y = nextIntUntilOk("Y=? ");
				CellWrapped moveCandidate = new CellWrapped(x, y);
				Boolean isEmpty = board.isEmpty(moveCandidate);
				if (isEmpty) {
					return moveCandidate;
				} else {
					out.println("Cell is not empty.");
				}

			} catch (IllegalArgumentException e) {
				out.println(e.getMessage());
			}
		}
	}

	private int nextIntUntilOk(String text) {
		while (true) {
			out.print(text);
			try {
				int value = scanner.nextInt();
				return value;
			} catch (InputMismatchException e) {
				out.println(e.getMessage());
			}
		}
	}
}