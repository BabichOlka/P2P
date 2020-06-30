package myChat;

import myChat.bo.*;
import myChat.constant.C10Constant;
import myChat.dao.MessageDAOImpl;
import myChat.io.exception.UnableToWriteException;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.interfaces.Packable;
import myChat.marshaller.XMLMarshaller;
import myChat.service.ClientService;
import myChat.util.*;

import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Client{

   private static final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
   private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
   private static final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
   private static Scanner scan = new Scanner(System.in);
   private static Date dateConnection;
   private static String password;
   private static String login;
   private static ObjectReader objr;
   private List<ConnectMessage> messages;
   String salt = UUID.randomUUID().toString();

    public Client(String login, Date dateConnection, String password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = UUID.randomUUID().toString();
        this.dateConnection = new Date();
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");
    }
    public Client(){}
    public Client(String login ) {
        this.login = login;
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");
    }
    public static void main(String[] args) {
        System.out.println("Your login: ");
        String login = scan.nextLine();
        System.out.println("Your password: ");
        String password = scan.nextLine();

        if (init(HOST, PORT, TOKEN, login, password,null)) {
            while (true) {
                connect();
            }
        }
    }

    private static boolean init(String host, int port, String token, String login, String password,String salt){
        Packable imsg = new InitMessage(host, port,  token, login, password,salt);
        SerializationUtil.writeObject(imsg, SerializationUtil.getREADER_INIT().getPath());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("There is interruption");
        }
        Packable answer = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
        if(answer != null){
            System.out.println(((InitMessage) answer).getLogin() + " is accepted");
            Client client = new Client(login, new Date(),password, salt);
            new ClientService().createClient(client);
            return true;
        }
        else {
            System.out.println("No connection");
            return false;
        }
    }

    private static void connect() {

        System.out.print("You: ");
        String str = scan.nextLine();
        String login_to = "v" +
                "asa";
        ConnectMessage msg = new ConnectMessage(str, login,login_to,null);
        //new MessageDAOImpl().create(msg);
        //msg.getMessage();
        try {
            new XMLMarshaller().marshall(msg, objr.getPath());//writeMessage
        } catch (UnableToWriteException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
