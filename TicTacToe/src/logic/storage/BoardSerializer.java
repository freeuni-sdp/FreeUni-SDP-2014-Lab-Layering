package logic.storage;

import model.ReadOnlyBoard;

/**
 * @author Sandro Dolidze
 *
 * This interface is responsible for serializing and deserializing ReadOnlyBoard.
 *
 * This interface is currently unstable, please do not use it yet
 */
public interface BoardSerializer {

    /**
     * returns serialized string representation of given board
     */
    String serialize(ReadOnlyBoard board);

    /**
     * returns ReadOnlyBoard from given string representation.
     * If input is not correct RuntimeException is thrown.s
     */
    ReadOnlyBoard deserialize(String serializedBoard);
}