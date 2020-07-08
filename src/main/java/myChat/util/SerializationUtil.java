package myChat.util;


import myChat.io.exception.UnableToReadException;
import myChat.io.exception.UnableToWriteException;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.impl.stream.ObjectWriter;
import myChat.io.interfaces.Packable;

public class SerializationUtil {
    private static final String DATA_FILE_PATH_CHAT = System.getProperty("user.dir") + "/src/main/resources/chat.xml";
    private static final String DATA_INIT_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/init.xml";

    private static final ObjectReader CHAT = new ObjectReader(DATA_FILE_PATH_CHAT);
    private static final ObjectReader READER_INIT = new ObjectReader(DATA_INIT_FILE_PATH);

    public static ObjectReader getCHAT() { return CHAT; }
    public static ObjectReader getREADER_INIT() { return READER_INIT; }


    public static void writeObject(Packable obj, String path) {
        try {
            new ObjectWriter().write(path, obj);
        } catch (UnableToWriteException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to write!", path));
        }
    }

    public static Packable readObject(ObjectReader objr) {
        try {
            return objr.read();
        } catch (UnableToReadException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("%s is unable to read!", objr.getPath()));
        }
    }
}
