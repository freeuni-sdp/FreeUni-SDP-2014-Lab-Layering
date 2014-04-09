package logic;

import java.io.IOException;

import model.FileStorage;

/**
 * @author Sandro Dolidze
 * 
 * This class is responsible for saving/loading GameState from/to persistent storage.
 */
public class GameStateStorage {
	private FileStorage fileStorage;
	private GameStateSerializer gameStateSerializer;
	
	public GameStateStorage(FileStorage fileStorage, GameStateSerializer gameStateSerializer) {
		this.fileStorage         = fileStorage;
		this.gameStateSerializer = gameStateSerializer;
	}
	
	public void save(String key, GameState gameState) {
		try {
			fileStorage.save(key, gameStateSerializer.serialize(gameState));
		} catch (IOException e){
			throw new RuntimeException("error with io", e);
		}
	}
	
	public GameState load(String key) {
		try {
			return gameStateSerializer.deserialize(fileStorage.load(key));
		} catch (IOException e){
			throw new RuntimeException("error with io", e);
		}
	}
}