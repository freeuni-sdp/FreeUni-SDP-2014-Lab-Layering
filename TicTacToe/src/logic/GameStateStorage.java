package logic;

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
		fileStorage.save(key, gameStateSerializer.serialize(gameState));
	}
	
	public GameState load(String key) {
		return gameStateSerializer.deserialize(fileStorage.load(key));
	}
}