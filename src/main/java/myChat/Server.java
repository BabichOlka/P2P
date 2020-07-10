package myChat;

import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLUnmarshaller;
import myChat.message.InitMessage;
import myChat.model.Cookies;
import myChat.model.OnlineUsers;
import myChat.service.CookiesService;
import org.apache.log4j.Logger;

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

    public static final Logger LOGGER = Logger.getLogger(Server.class);

    public static void main(String[] args) throws InterruptedException, JAXBException, UnableToWriteException {
        new CookiesService().updateCookiesDB();
        LOGGER.info("Server is updated!");
        while (true) {
            listen();
        }
    }

    private static void listen() throws InterruptedException, JAXBException, UnableToWriteException {

        InitMessage imsg = readMessage(pathTo);

        if (imsg != null) {
            Thread.sleep(2000);

            if (checkClient(imsg)) {
                LOGGER.info(imsg.getLogin() + " is accepted");
                ServerConnection connection = new ServerConnection(imsg.getLogin());
                connection.start();
                createCookies(imsg.getLogin());
            }
            OnlineUsers onlineUsers = new CookiesService().getOnlineUsers();
            new XMLMarshaller().marshall(onlineUsers, USERS_ONLINE_FILE);
        }
    }

    public static void createCookies(String login){
        String key = UUID.randomUUID().toString();
        Cookies cookies = new Cookies(login, new Date(), key);

        if (new CookiesService().getCookies(login) != null) {
            new CookiesService().updateCookies(new CookiesService().getCookies(login));
        } else new CookiesService().createCookies(cookies);
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