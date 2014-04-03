package model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
	 */
	public void save(String filename, String data) {
		throw new NotImplementedException();
	}

	/**
	 * reads data from a given file
	 */
	public String load(String filename) {
		throw new NotImplementedException();
	}
}
