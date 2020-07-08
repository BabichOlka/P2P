package myChat.util;

import myChat.io.exception.UnableToReadException;
import myChat.io.impl.file.StreamTextFileReader;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterUtil {
    private static final String PATH_TO_BADWORD = "src/main/resources/bad_word";
    private static final String PATH_TO_COUNTRY = "src/main/resources/country";
    private static final String PATH_TO_NAME = "src/main/resources/name";

    public static final Set<String> badWord = setWords(PATH_TO_BADWORD);
    public static final Set<String> country = setWords(PATH_TO_COUNTRY);
    public static final Set<String> name = setWords(PATH_TO_NAME);

    public static Set<String> setWords(String p){
        StreamTextFileReader txt = new StreamTextFileReader(p);
        String[] words = new String[0];
        try {
            words = txt.read().split(",");
        } catch (UnableToReadException e) {
            e.printStackTrace();
        }
        return Arrays.stream(words).collect(Collectors.toSet());
    }

    public static String capitalize(String str){
        String[] letter = str.split("");
        letter[0] = letter[0].toUpperCase();
        str = "";
        for(String let: letter){
            str = str.concat(let);
        }
        return str;
    }
}
