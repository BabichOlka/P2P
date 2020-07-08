package myChat;

import myChat.model.Cookies;
import myChat.model.InitMessage;
import myChat.model.OnlineUsers;
import myChat.dao.CookiesDAOImpl;
import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLUnmarshaller;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class Server {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;
    private static String pathTo = "src/main/resources/initMessage.xml";
    private static String USERS_ONLINE_FILE = "src/main/resources/users_online.xml";

    public static void main(String[] args) throws InterruptedException, JAXBException, IOException, UnableToWriteException {
        new CookiesDAOImpl().updateDB();
        System.out.println("Server is updated!");
        while (true) {
            listen();
        }
    }

    private static void listen() throws InterruptedException, JAXBException, IOException, UnableToWriteException {
        InitMessage imsg = readMessage(pathTo);
        if (imsg != null) {
            Thread.sleep(2000);
            if (checkClient(imsg)) {

                Connection conn = new Connection(imsg.getLogin());
                conn.start();
                String key = UUID.randomUUID().toString();
                Cookies cookies = new Cookies((imsg.getLogin()), new Date(), key);
                if (new CookiesDAOImpl().get(imsg.getLogin()) != null) {

                    new CookiesDAOImpl().update(new CookiesDAOImpl().get(imsg.getLogin()));

                } else new CookiesDAOImpl().create(cookies);
            }
            OnlineUsers onlineUsers = new CookiesDAOImpl().getOnlineUsers();
            new XMLMarshaller().marshall(onlineUsers, USERS_ONLINE_FILE);
        }
    }

    private static boolean checkClient(InitMessage imsg) {
        if (imsg.getHost().equals(HOST) && imsg.getPort() == PORT) {
            File client = new File(System.getProperty("user.dir") + "/src/main/resources/" + imsg.getLogin() + ".xml");
            try {
                client.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {

            return false;
        }
    }

    private static InitMessage readMessage(String pathTo) {
        try {
            return new XMLUnmarshaller().unmarshallMessage(pathTo);
        } catch (IOException | JAXBException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Something went wrong while unmarshalling!");
        }
    }

}