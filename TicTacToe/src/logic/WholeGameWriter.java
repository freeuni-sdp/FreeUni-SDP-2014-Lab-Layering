package logic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.Board;

/**
 * This class is responsible for writing whole game process into a file
 * 
 * @author Saba Gogolidzes
 */
public class WholeGameWriter {
	BufferedWriter bufferedWriter;
	GameStateSerializer serializer; // using this class to get status of game
									// (as string) from board
	final long startTime; // start time of game
	long lastTime; // time last move was made

	/**
	 * constructor gets filename where this class will write the process of game
	 * 
	 * @param fileName
	 * @param size
	 */
	public WholeGameWriter(String fileName, int size) {
		startTime = System.nanoTime();
		lastTime = startTime;
		serializer = new GameStateSerializer();
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(
					file.getAbsoluteFile()));
			bufferedWriter.write("game is on " + size + " X " + size + " board\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * write one move into a file.
	 * 
	 * @param board
	 * @param currentColor
	 */
	public void addState(Board board, PlayerColor currentColor) {
		GameState state = new GameState(board, currentColor);
		String gameState = serializer.serialize(state);
		long curTime = System.nanoTime();
		long duration = curTime - lastTime;
		lastTime = curTime;
		long seconds = duration / 1000000000;
		try {
			bufferedWriter.write(gameState);
			bufferedWriter.write("Move Duration: " + (seconds / 60) + "m "
					+ seconds % 60 + "s\n\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * writes in file who won and what was the duration of game
	 * 
	 * @param gameStatus
	 */
	public void endOfGame(GameStatus gameStatus) {
		try {
			if (gameStatus == GameStatus.DRAW) {
				bufferedWriter.write("drow\n");
			} else if (gameStatus == GameStatus.XISWINNER) {
				bufferedWriter.write("X is winner\n");
			} else {
				bufferedWriter.write("O is winner\n");
			}
			long curTime = System.nanoTime();
			long duration = curTime - startTime;
			long seconds = duration / 1000000000;
			bufferedWriter.write("Game Durration: " + seconds / 3600 + "h "
					+ (seconds / 60) % 60 + "m " + seconds % 60 + "s\n");
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
