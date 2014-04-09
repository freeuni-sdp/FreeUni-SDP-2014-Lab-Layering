package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JLabel;

import model.Board;
import model.Cell;
import model.CellValue;

import ui.ButtonClickListener;
import ui.ConsolePresenter;
import ui.GUIPresenter;

/**
 * This class is responsible for reading game from file and showing on console and swing
 * 
 * @author Salome Tsiramua
 */
public class ReadFromFileAndShow {

	
	private ConsolePresenter consolePresenter;
	private GUIPresenter guiPresenter;
	private Board board;
	private GameStatus gameStatus;
	private int boardWidth;
	private int boardHeight;
	private char[][] currBoard;
	private JLabel header = new JLabel();
	
	public ReadFromFileAndShow(String filename){
		
		consolePresenter = new ConsolePresenter(System.out);
		
		guiPresenter = new GUIPresenter(new ButtonClickListener(), header);
		
		readFileAndSave(filename);
		consolePresenter.show(board, gameStatus);
		guiPresenter.show(board, gameStatus);
		
	}

	private void readFileAndSave(String filename) {
		BufferedReader br = null;
		Board board = new Board();
		
		File file = new File(filename);
		try {
 
			String currLine;
 			
			br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
 
			int i = 0;
			String[] tokens; 
			
			while ((currLine = br.readLine()) != null) {
				System.out.println("shemodis???");
				tokens = currLine.split(" ");
				if(currLine.equals("draw")){
					gameStatus = GameStatus.DRAW;
					break;
				}else if(currLine.equals("X is winner")){
					gameStatus = GameStatus.XISWINNER;
					break;
				}else if(currLine.equals("O is winner")){
					gameStatus = GameStatus.OISWINNER;
					break;
				}else if(i == 0){
					boardHeight = Integer.parseInt(tokens[3]);
					boardWidth = Integer.parseInt(tokens[5]);
					i++;
					currBoard = new char[boardHeight][boardWidth];
					emptyGrid();
				}
				
				header.setText(br.readLine());
				
				for(int j = 0; j < boardHeight; j++){
					currLine = br.readLine();
					for(int k = 0; k < boardWidth; k++){
						if(currBoard[j][k] != currLine.charAt(k)){
							CellValue currCellValue = CellValue.EMPTY;
							if(currBoard[j][k] == 'x'){
								currCellValue = CellValue.X;
								currBoard[j][k] = 'x';
							}
							if(currBoard[j][k] == 'o'){
								currCellValue = CellValue.O;
								currBoard[j][k] = 'o';
							}
							board.makeMove(new Cell(j, k), currCellValue);
							
						}
					}
				}
				currLine = br.readLine();//move duration
				currLine = br.readLine();//space
				consolePresenter.show(board, gameStatus);
				guiPresenter.show(board, gameStatus);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		
		
		}
	}
	
	private void emptyGrid() {
		for(int i = 0; i < currBoard.length; i ++){
			for(int j = 0; j < currBoard[0].length; j++){
				currBoard[i][j] = ' '; 
			}
		}
	}
}
