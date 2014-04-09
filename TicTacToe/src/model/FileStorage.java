package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Sandro Dolidze
 * 
 * This class is responsible for saving and retrieving data into/from file system.
 */
public class FileStorage {
	private String folder;
	
	public FileStorage(String folder) {
		this.folder = folder;
	}
	
	/**
	 * saves given data in a file.
	 * will overwrite if file exits.
	 */
	public void save(String filename, String data) throws IOException {				
		Files.write(Paths.get(folder + "/" + filename), data.getBytes());
	}

	/**
	 * reads data from a given file/
	 */
	public String load(String filename) throws IOException {
		return new String(Files.readAllBytes(Paths.get(folder + "/" + filename)));
	}
}