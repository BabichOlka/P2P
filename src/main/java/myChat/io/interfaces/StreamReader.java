package myChat.io.interfaces;

import myChat.io.exception.UnableToReadException;

public interface StreamReader {
    Packable read() throws UnableToReadException;
}
