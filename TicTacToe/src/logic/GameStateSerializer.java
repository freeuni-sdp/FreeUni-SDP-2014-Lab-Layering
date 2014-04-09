package logic;

import model.Board;
import model.Cell;
import model.CellValue;
import model.ReadOnlyBoard;

/**
 * @author Sandro Dolidze
 *
 * This class is responsible for serializing and deserializing GameState into string format.
 */
public class GameStateSerializer {	
    /**
     * returns serialized string representation of given game state
     */
    public String serialize(GameState gameState) {
    	String currentPlayer = (gameState.getCurrentPlayer() == null) ? "-" : gameState.getCurrentPlayer().toString().toLowerCase();
    	ReadOnlyBoard board = gameState.getBoard();
    	String res = String.format("Current: %s\n", currentPlayer);
    	
    	for (int i=0; i<board.getSize(); i++) {
    		for (int j=0; j<board.getSize(); j++) {
    			res += getCharFromCellValue(board.getValueAt(new Cell(i, j)));
    		}
    		res += "\n";
    	}
    	
    	return res;
    }

    /**
     * Deserializes GameState from string representation.
     * Beware: playerColor will be null if current player is '-'
     */
    public GameState deserialize(String serializedGameState) {
    	try {
    		Board board = new Board();
			String[] lines = serializedGameState.split("\n");
			PlayerColor currentPlayer = getCurrentPlayer(lines[0]);
			
			for (int i=1; i<lines.length; i++) {
				for (int j=0; j<lines.length-1; j++) {
					makeMove(board, lines[i].charAt(j), i-1, j);
				}
			}
			
			return new GameState(board, currentPlayer);
    	} catch (Exception e) {
    		throw new IllegalArgumentException("String is not properly formatted", e);
    	}
    }
    
    private PlayerColor getCurrentPlayer(String line) {
    	char lastChar = line.charAt(line.length()-1);
    	return getPlayerColorFromChar(lastChar);
    }
    
    private void makeMove(Board board, char ch, int i, int j) {
    	switch(ch) {
			case 'x':
			case 'o': board.makeMove(new Cell(i, j), getCellValueFromChar(ch)); break;
			case '-': break;
			default : throw new IllegalArgumentException("cannot make such move: " + ch);
    	}
    }
    
    private PlayerColor getPlayerColorFromChar(char ch) {
    	switch(ch) {
			case 'x': return PlayerColor.X;
			case 'o': return PlayerColor.O;
			case '-': return null;
			default : throw new IllegalArgumentException("cannot convert from character to PlayerColor");
    	}
    }
    
    private CellValue getCellValueFromChar(char ch) {
    	switch(ch) {
			case 'x': return CellValue.X;
			case 'o': return CellValue.O;
			case '-': return CellValue.EMPTY;
			default : throw new IllegalArgumentException("cannot convert from character to CellValue");
    	}
    }
    
    private char getCharFromCellValue(CellValue cv) {
    	switch (cv) {
    		case X:     return 'x';
    		case O:     return 'o';
    		case EMPTY: return '-';
    		default:    return 0; // not necessary, only used to shut up IDE
    	}
    }
}