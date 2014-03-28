package logic.storage;

import model.ReadOnlyBoard;

/**
 * @author Sandro Dolidze
 *
 * This interface is responsible for saving and restoring Board from persistent storage.
 *
 * This interface will change in near future, do not use it yet
 *
 * Personal Comments
 * -----------------
 * I also need current player, but board does not have that information.
 * I could save whole Game class in a storage, but I cannot retries board
 * and current current player information from class (no appropriate getters).
 * Should This class be part of model layer or logic?
 */
public interface BoardStorage {

    /**
     * Saves given board in a persistent storage with a given key.
     */
    void save(String key, ReadOnlyBoard board);

    /**
     * Retrieves board from persistent storage with a given key.
     */
    ReadOnlyBoard find(String key);
}