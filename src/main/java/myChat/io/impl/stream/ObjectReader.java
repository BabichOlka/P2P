package myChat.io.impl.stream;

import myChat.io.base.BaseReader;
import myChat.io.exception.UnableToReadException;
import myChat.io.interfaces.Packable;
import myChat.io.interfaces.StreamReader;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ObjectReader extends BaseReader implements StreamReader, Packable {
    public ObjectReader(String path) {
        super(path);
    }

    @Override
    public Packable read() throws UnableToReadException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file.getAbsoluteFile()))) {
            return (Packable) ois.readObject();
        } catch (EOFException e) {
            // do nothing :)
            return null;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new UnableToReadException("Could not read object!");
        }
    }
}
