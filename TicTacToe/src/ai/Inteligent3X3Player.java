package ai;

import java.util.List;

import logic.MoveListener;
import logic.Player;
import logic.PlayerColor;
import model.Cell;
import model.CellValue;
import model.ReadOnlyBoard;

public class Inteligent3X3Player implements Player{

	private MovesHelper movesHelper;
	
	public Inteligent3X3Player(MovesHelper movesHelper){
		this.movesHelper = movesHelper;
	}
	
	@Override
	public void makeMove(ReadOnlyBoard board, PlayerColor color, MoveListener moveListener) {
		Cell bestMove = getBestMove(CreateBoard(board), color.toString().charAt(0));
		moveListener.makeMove(bestMove);
	}
	
	/*
	 * copy current grid into char array
	 * because board should be updated 
	 * during algorithm calculation that is troublesome
	 * so board is safe by damaging it's State
	 */
	private char[] CreateBoard(ReadOnlyBoard board) {
		char[] arr = new char[board.getSize()*board.getSize()];
		Cell cell = new Cell(0,0);
		for (int i = 0; i < board.getSize(); i++) {
			for (int j = 0; j < board.getSize(); j++){ 
				cell.x = i;
				cell.y = j;
				arr[board.getSize()*i+j] = ' ';
				if(board.getValueAt(cell) == CellValue.X){
					arr[board.getSize()*i+j] = 'X';
				} else if (board.getValueAt(cell) == CellValue.O){
					arr[board.getSize()*i+j] = 'O';
				}
			}
		}
		return arr;
	}
	
	/*
	 * examine all my and my rival's
	 * moves for one depth and decide which 
	 * move is the best considering weights 
	 * of moves that has been calculated by 
	 * function evaluateMove
	 */
	private Cell getBestMove(char[] arr, char player) {
		List<Cell> myMoves = movesHelper.getPossibleMoves();
		if (myMoves.isEmpty())
			throw new IllegalStateException("You can't make move - board is full");
		if (myMoves.size() == 1)
			return myMoves.get(0);
		char rival = (player == 'X') ? 'O' : 'X';
		int max = Integer.MIN_VALUE;
		Cell bestCell = new Cell(0,0);
		for (Cell cell : myMoves) {
			arr[3*cell.x+cell.y] = player;
			int tmp1 = evaluateMove(arr, player);
			int worst = Integer.MIN_VALUE;
			for (Cell rivalCell : myMoves) {
				if(cell.x == rivalCell.x && cell.y == rivalCell.y)continue;
				arr[3*rivalCell.x+rivalCell.y] = rival;
				int tmp2 = evaluateMove(arr, rival);
				if (tmp2 > worst){
					worst = tmp2;
				}
				arr[3*rivalCell.x+rivalCell.y] = ' ';
			}
			tmp1 -= worst;
			if (max < tmp1){
				max = tmp1;
				bestCell = cell;
			}
			arr[3*cell.x+cell.y] = ' ';
		}
		return bestCell;
	}
	
	/*
	 * count fitness of move 
	 * that has been performed
	 * by player
	 */
	private int evaluateMove(char[] arr, char player){
		char opponent = (player == 'X')  ? 'O' : 'X';
		char piece;
		int tmp1, tmp2, heursitic = 0;
		for(int i=0; i<PossibleWins.length; i++)  {
	        tmp1 = tmp2 = 0;
	        for(int j=0; j<PossibleWins[i].length; j++)  {
	            piece = arr[PossibleWins[i][j]];
	            if (piece == player) 
	            	tmp1++;
	            else if (piece == opponent) 
	            	tmp2++;
	        }
	        heursitic += heuristic[tmp1][tmp2];
	    }
	    return heursitic;
	}
	
	// static data structures essential for algorithm

	private static int[][] PossibleWins = {
	    { 0, 1, 2 },
	    { 3, 4, 5 },
	    { 6, 7, 8 },
	    { 0, 3, 6 },
	    { 1, 4, 7 },
	    { 2, 5, 8 },
	    { 0, 4, 8 },
	    { 2, 4, 6 }
	};
	
	private static int[][] heuristic = {
		{     0,   -10,  -100, -1000 },
	    {    10,     0,     0,     0 },
	    {   100,     0,     0,     0 },
	    {  1000,     0,     0,     0 }
	};
}
