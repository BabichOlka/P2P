package myChat;

import myChat.bo.InitMessage;
import myChat.filter.MessageFilter;
import myChat.filter.messageFilter.*;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.interfaces.Packable;
import myChat.util.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class Server{
    private static final List<String> AVAILABLE_CLIENTS = Arrays.asList("user");
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    private static final List<MessageFilter> filter = new ArrayList<>();
    {   filter.add(new SpaceFilter());
        filter.add(new BadwordsFilter());
        filter.add(new CountryFilter());
        filter.add(new EmojiFilter());
        filter.add(new FirstLetterFilter());
        filter.add(new NameFilter());
    }

    public static void main(String[] args){
        while(true){
            listen();
        }
    }

    private static void listen() {
        Packable imsg = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
        if(imsg != null){
            if (checkClient((InitMessage) imsg)){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Connection conn = new Connection(((InitMessage) imsg).getLogin());
                conn.start();
                clearBuffer(SerializationUtil.getREADER_INIT());
            }
        }
    }

    private static boolean checkClient(InitMessage imsg){
          if (imsg.getHost().equals(HOST) && imsg.getPort() == PORT && AVAILABLE_CLIENTS.contains(imsg.getToken())){
            File client = new File(System.getProperty("user.dir") + "/src/main/resources/" + imsg.getLogin() + ".xml");
            try {
                client.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            clearBuffer(SerializationUtil.getREADER_INIT());
            return false;
        }
    }

    public static void clearBuffer(ObjectReader objr){
        SerializationUtil.writeObject(null, objr.getPath());
    }

    public static String controlMessage(String message){
        for(MessageFilter filt:filter){
            message = filt.apply(message);
        }
        return message;
    }
}