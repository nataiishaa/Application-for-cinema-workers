package org.example.util;

import java.io.IOException;

/**
 * The LocalStorageManager interface defines methods for reading and writing data to a local storage.
 * Implementing classes are responsible for the actual implementation of these methods based on specific storage mechanisms.
 **/
public interface LocalStorageManager<T> {

    void writeToFile(T data) throws IOException;

    T readFromFile() throws IOException;

}