package myChat.io.interfaces;

import myChat.io.exception.UnableToCloseExcepton;
import myChat.io.exception.UnableToReadException;

public interface Reader {
    String read() throws UnableToReadException, UnableToCloseExcepton;
}
