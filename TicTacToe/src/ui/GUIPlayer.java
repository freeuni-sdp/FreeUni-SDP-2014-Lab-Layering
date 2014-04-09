package ui;

import java.util.InputMismatchException;

import javax.swing.JLabel;

import logic.MoveListener;
import logic.Player;
import logic.PlayerColor;
import model.Cell;
import model.ReadOnlyBoard;

public class GUIPlayer implements Player {

	ButtonClickListener listener;
	private JLabel header;

	public GUIPlayer(ButtonClickListener listener, JLabel header) {
		this.listener = listener;
		this.header = header;
	}

	@Override
	public void makeMove(ReadOnlyBoard board, PlayerColor color,
			MoveListener moveListener) {
		
		
		while (true) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(listener.isClicked()){
				
				Cell clickedCell = listener.getNextMove();
				if(nextMoveIsLegal(board,clickedCell)){
					showWhoIsOnTurn(color);
					moveListener.makeMove(clickedCell);
					listener.setClicked(false);
					break;
				}
				listener.setClicked(false);
			}

		}
		
		
			
	}
	
	private void showWhoIsOnTurn(PlayerColor playerColor) {
		
		switch (playerColor) {
		case X:
			header.setText("Player O  is on turn");
			break;
		case O:
			header.setText("Player X is on turn");
			break;
		}
	}

	
	
	private boolean nextMoveIsLegal(ReadOnlyBoard board,Cell clickedCell) {

		try {

			Boolean isEmpty = board.isEmpty(clickedCell);
			if (isEmpty) return true;
			
			header.setText("Cell is not empty.");
				
			return false;
			

		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

}
