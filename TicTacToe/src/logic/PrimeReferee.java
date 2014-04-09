package logic;
import model.Cell;
import model.CellValue;
import model.ReadOnlyBoard;


/**
 * @author Tamar Keshelava. 
 * 		PrimeReferee class implements Referee interface. 
 */
public class PrimeReferee implements Referee {

	/**
	 *	Create grid, which is filled with prime numbers.
	 */
	private final int[][] scoreBoard = new int[][] { { 2, 3, 5 },
			{ 7, 11, 13 }, { 17, 19, 23 } };

	/**
	 * 	Here is overloaded getGameStatus(ReadOnlyBoard board) method. 
	 *  this method return who won the game.
	 */
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
	
	/**
	 *	This method decides if value type (X or O) is winner.
	 */
	private boolean isWinner(ReadOnlyBoard board, CellValue value) {
		int score = getScore(board, value);
		return isWinningScore(score);
	}
	
	/**
	 *	This method returns cell's last score. if current cell's value type (for example X)
	 *  equals previous value type (it was also X) the current cell's score will be - 
	 *  current cell's appropriate value from scoreBoard  * previous cell's appropriate 
	 *  value from scoreBoard
	 */
	private int getScore(ReadOnlyBoard board, CellValue value) {
		int product=1;
		for(int i=0; i<board.getSize(); i++) {
			for(int j=0; j<board.getSize(); j++) {
				Cell cell = new Cell(i,j);
				CellValue currentValue = board.getValueAt(cell);
				if (value==currentValue) {
					product*=scoreBoard[cell.x][cell.y];
				}
			}
		}
		return product;
	}

	/**
	 *	This method check's if cell's last score divided by the multiplication 
	 *  of numbers aligned horizontal vertical or diagonal.
	 */
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
