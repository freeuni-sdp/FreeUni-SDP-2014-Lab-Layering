package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import logic.GameStatus;
import logic.Presenter;
import model.Cell;
import model.ReadOnlyBoard;


public class GUIPresenter implements Presenter {
	
	private JFrame container;
	
	private JLabel message;
	
	private JPanel messagesPanel;
		
	private JButton[][] arrayOfButtons;
	
	private JPanel buttonsPanel = new JPanel();
	
	private ButtonClickListener listener;

	/**
	 * GUI presenter of TicTacToe game
	 * @param listener - button click listener for interactive GUI
	 * @param header - header label for game status
	 */
	public GUIPresenter(ButtonClickListener listener,JLabel header)
	{
		
		this.listener = listener;
		message = header;
		initContainer();
		initHeader();
	}
	
	/**
	 * initialize JFrame
	 */
	private void initContainer()
	{
		container = new JFrame();
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBounds(50, 50, 400, 500);
		container.setBackground(Color.blue);
	}
	
	/**
	 * initialize header component for window
	 */
	private void initHeader() 
	{
		
		message.setText("Welcome to TicTacToe");

		messagesPanel = new JPanel();
		messagesPanel.setBackground(Color.RED);
		messagesPanel.add(message);
		container.add(messagesPanel, BorderLayout.NORTH);
		container.setVisible(true);
		container.setAlwaysOnTop(true);

	}
	

	@Override
	public void show(ReadOnlyBoard board, GameStatus gameStatus) 
	{
		buttonsPanel.removeAll();
		GridLayout grid = new GridLayout(board.getSize(), board.getSize());
		buttonsPanel.setLayout(grid);
		initButtons(board);
		
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				buttonsPanel.add(arrayOfButtons[i][j]);
			}	
		}
		container.add(buttonsPanel, BorderLayout.CENTER);
		container.invalidate();
		container.validate();
		container.repaint();
		
		if (gameStatus != GameStatus.INPROGRESS) {
			JOptionPane.showMessageDialog(container, getStatusMessage(gameStatus));
			message.setText("Game Finished!");
		}
		
	}
	
	/**
	 * factory method for status message
	 * @param status - GameStatus object
	 * @return status message
	 */
	private String getStatusMessage(GameStatus status)
	{
		switch (status) {
		case INPROGRESS:
			return "In Progress";
		case DRAW:
			return "Draw";
		case OISWINNER:
			return "O is winner";
		case XISWINNER:
			return "X is winner";
		}
		return null;
	}
	

	/**
	 * initialize buttons
	 * @param board
	 */
	private void initButtons(ReadOnlyBoard board) 
	{
		arrayOfButtons = new JButton[board.getSize()][board.getSize()];
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++) {
				String value = board.getValueAt(new Cell(i, j)).name();
				JButton b = new JButton(value);
				b.setFont(new Font("bold", Font.BOLD, 22));
				b.setFocusable(false);
				b.setBackground(Color.LIGHT_GRAY);
				b.setName(""+i+","+j);
				b.addActionListener(listener);
				arrayOfButtons[i][j] = b;
			}
		}
	}

}
