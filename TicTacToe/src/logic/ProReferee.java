package logic;

import model.Cell;
import model.CellValue;
import model.ReadOnlyBoard;

public class ProReferee implements Referee{

	@Override
	public GameStatus getGameStatus(ReadOnlyBoard board) {
		if(isWinner(board, CellValue.X))
			return GameStatus.XISWINNER;
		if(isWinner(board, CellValue.O))
			return GameStatus.OISWINNER;
		if(board.isFull())
			return GameStatus.DRAW;
		return GameStatus.INPROGRESS;
	}
	boolean isWinner(ReadOnlyBoard board, CellValue value){
		//Create board for dynamic programming slightly bigger then original
		Helper[][] dp = new Helper[board.getSize() + 1][board.getSize() + 1];
		for(int i = 0; i < dp.length; i++){
			for(int j = 0; j < dp[0].length; j++){
				dp[i][j] = new Helper(0, 0, 0); //initialize all cells to zeroes
			}
		}
		for(int i = 1; i < dp.length; i++){
			for(int j = 1; j < dp[0].length; j++){
				//because of larger Helper board we omit checks for -1
				CellValue cur = board.getValueAt(new Cell(i - 1, j - 1));
				if(cur != value) continue;
				dp[i][j].d = dp[i - 1][j - 1].d + 1;
				dp[i][j].h = dp[i][j - 1].h + 1;
				dp[i][j].v = dp[i - 1][j].v + 1;
				if(dp[i][j].isWinning())
					return true;
			}
		}
		return false;
	}
	private class Helper{
		int h;
		int d;
		int v;
		public Helper(int h, int d, int v){
			this.d = d;
			this.h = h;
			this.v = v;
		}
		public boolean isWinning(){
			return (h >= 5 || d >= 5 || v >= 5);
		}
	}
}
