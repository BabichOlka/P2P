package myChat;

import myChat.bo.Cookies;
import myChat.bo.InitMessage;
import myChat.dao.CookiesDAOImpl;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.interfaces.Packable;
import myChat.util.SerializationUtil;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class Server{

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args)throws InterruptedException {
        new CookiesDAOImpl().updateDB();
        while(true){
            listen();
        }
    }

    private static void listen() throws InterruptedException {
        Packable imsg = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
        if(imsg != null){
            if (checkClient((InitMessage) imsg)){
                Thread.sleep(2000);

                Connection conn = new Connection(((InitMessage) imsg).getLogin());
                conn.start();
                String key = UUID.randomUUID().toString();
                Cookies cookies = new Cookies((((InitMessage) imsg).getLogin()), new Date(),key);
                if (new CookiesDAOImpl().get(((InitMessage) imsg).getLogin()) != null) {

                    new CookiesDAOImpl().update(new CookiesDAOImpl().get(((InitMessage) imsg).getLogin()));

                } else   new CookiesDAOImpl().create(cookies);
                String log = ((InitMessage) imsg).getLogin();
                //String key1 = (new CookiesDAOImpl().getKeyByLogin(log)).toString();
               // System.out.println(key1);
                clearBuffer(SerializationUtil.getREADER_INIT());
            }
        }
    }

    private static boolean checkClient(InitMessage imsg){
          if (imsg.getHost().equals(HOST) && imsg.getPort() == PORT ){
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


}