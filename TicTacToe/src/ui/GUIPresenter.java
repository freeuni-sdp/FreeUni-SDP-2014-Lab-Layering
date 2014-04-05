package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import logic.GameStatus;
import logic.Presenter;
import model.ReadOnlyBoard;


public class GUIPresenter implements Presenter {
	
	private JFrame container;
	
	private JLabel message;
	
	{
		container = new JFrame();
		message = new JLabel();
	}
	
	public GUIPresenter() {
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBounds(50, 50, 300, 400);
		container.setBackground(Color.blue);
		message.setText("Welcome to TicTacToe");
		container.add(message);
		container.setVisible(true);
	}

	@Override
	public void show(ReadOnlyBoard board, GameStatus gameStatus) {
		
	}

}
