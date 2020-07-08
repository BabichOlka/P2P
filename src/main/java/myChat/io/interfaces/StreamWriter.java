package myChat.io.interfaces;

import myChat.io.exception.UnableToWriteException;

public interface StreamWriter {
    void write(String path, Packable pkg) throws UnableToWriteException;
}
