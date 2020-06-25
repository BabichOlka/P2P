package myChat;

import myChat.bo.*;
import myChat.constant.C10Constant;
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

public class Client{

   private static final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
   private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
   private static final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
   private static Scanner scan = new Scanner(System.in);
   private static Date dateConnection;
   private static String password;
   private static String name;
   private static ObjectReader objr;
   private List<ConnectMessage> messages;

    public Client(String name, Date dateConnection, String password) {
        this.name = name;
        this.password = password;
        this.dateConnection = new Date();
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + name + ".xml");
    }
    public Client(){}
    public Client(String name ) {
        this.name = name;
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + name + ".xml");
    }
    public static void main(String[] args) {
        System.out.println("Your name: ");
        String name = scan.nextLine();
        System.out.println("Your password: ");
        String password = scan.nextLine();
        if (init(HOST, PORT, TOKEN, name, password)) {
            while (true) {
                connect();
            }
        }
    }

    private static boolean init(String host, int port, String token, String name, String password){
        Packable imsg = new InitMessage(host, port,  token, name, password);
        SerializationUtil.writeObject(imsg, SerializationUtil.getREADER_INIT().getPath());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("There is interruption");
        }
        Packable answer = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
        if(answer != null){
            System.out.println(((InitMessage) answer).getName() + " is accepted");
            Client client = new Client(name, new Date(),password);
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
        ConnectMessage msg = new ConnectMessage(str, name);
        msg.getMessage();
        try {
            new XMLMarshaller().marshall(msg, objr.getPath());
        } catch (UnableToWriteException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
