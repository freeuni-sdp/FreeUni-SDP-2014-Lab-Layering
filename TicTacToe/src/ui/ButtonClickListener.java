package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import logic.Game;
import logic.Player;
import logic.PlayerColor;
import model.Cell;

public class ButtonClickListener implements ActionListener{

	
	

	private Cell nextMove;

	private boolean clicked = false;
	public ButtonClickListener( ) {
		
	
		//this.game = game;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clickedButton = (JButton)e.getSource();
		String pos = clickedButton.getName();
		int x = Integer.parseInt(pos.substring(0,1));
		int y =  Integer.parseInt(pos.substring(2,3));
		System.out.println(x+"  " +y);
		nextMove = new Cell(x, y);
	
	
		clicked=true;
		
	}


	public  Cell getNextMove() {
		return nextMove;
	}


	public void setNextMove(Cell nextMove) {
		this.nextMove = nextMove;
	}


	public boolean isClicked() {
		return clicked;
	}


	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}


	




}
