package logic;

import model.Cell;
import model.CellValue;



public class PrimeReferee implements Referee {

	private final int[][] scoreBoard = new int[][] { { 2, 3, 5 },
			{ 7, 11, 13 }, { 17, 19, 23 } };

	public GameStatus getGameStatus(ReadOnlyBoard board) {
		
		if (isWinner(board, CellValue.X)) {
			return GameStatus.XISWINNER;
		}
		
		if (isWinner(board, CellValue.O)) {
			return GameStatus.OISWINNER;
		}
		
		if (board.isFull()) {
			return GameStatus.DRAW;
		}
		
		return GameStatus.INPROGRESS;
	}
	
	private boolean isWinner(ReadOnlyBoard board, CellValue value) {
		int score = getScore(board, value);
		return isWinningScore(score);
	}
	
	private int getScore(ReadOnlyBoard board, CellValue value) {
		int product=1;
		for(int i=0; i<board.getSize(); i++) {
			for(int j=0; j<board.getSize(); j++) {
				Cell cell = new Cell(i,j);
				CellValue currentValue = board.getValueAt(new CellWrapped(cell)).toCellValue();
				if (value==currentValue) {
					product*=scoreBoard[cell.x][cell.y];
				}
			}
		}
		return product;
	}

	private boolean isWinningScore(int score) {
		return 	score % (2 * 3 * 5) == 0 || 
				score % (7 * 11 * 13) == 0 || 
				score % (17 * 19 * 23) == 0 ||

				score % (2 * 7 * 17) == 0 || 
				score % (3 * 11 * 19) == 0 || 
				score % (5 * 13 * 23) == 0 ||

				score % (2 * 11 * 23) == 0 || 
				score % (5 * 11 * 17) == 0;
	}
}
