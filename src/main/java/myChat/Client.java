package myChat;

import myChat.constant.C10Constant;
import myChat.dao.MessageDAOImpl;
import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLUnmarshaller;
import myChat.model.*;
import myChat.util.PropertyUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Client {

    private static final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
    private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
    private static final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
    private static Scanner scan = new Scanner(System.in);
    private static String objr;
    private static String login;
    private static String pathTo = "src/main/resources/initMessage.xml";
    private static String USERS_ONLINE_FILE = "src/main/resources/users_online.xml";

    public Client(String login) {
        this.login = login;
        objr = System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml";
    }

    public static void main(String[] args) {

        System.out.println("Please enter 1 if you are registered, 2 if you are unregistered");
        String answer1 = scan.nextLine();

        while (!answer1.equalsIgnoreCase("1") & !answer1.equalsIgnoreCase("2")) {
            System.out.println("You entered a wrong symbol. Please choose 1 for yes, 2 for no to continue");
            answer1 = scan.nextLine();
        }

        ClientM clientM = new ClientReg().askRegistration(answer1);

        if ((clientM == null)) {
            System.exit(0);
        } else {
            Client client = new Client(clientM.getLogin());
            if (init(HOST, PORT, TOKEN, login)) {
                while (true) {
                    connect(clientM);
                }
            }
        }
    }

    private static boolean init(String host, int port, String token, String login) {

        InitMessage initMessage = new InitMessage(host, port, token, login);
        writeMessage(initMessage, pathTo);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("There is interruption");
        }

        InitMessage answer1 = readMessage(pathTo);

        if (answer1 != null) {
            System.out.println(answer1.getLogin() + " is accepted");
            return true;
        } else {
            System.out.println("No connection");
            return false;
        }
    }

    private static void connect(ClientM clientM) {

        ConnectMessage messageForMe = readConnectMessage(objr);

        if (messageForMe != null) {

            CheckMessage checkMessage = readCheckMessage(System.getProperty("user.dir") + "/src/main/resources/checkMessage/"
                    + messageForMe.getClientLogin() + messageForMe.getLogin_to() + ".xml");

            if (checkMessage != null && String.valueOf((messageForMe.getClientLogin() + messageForMe.getLogin_to()
                    + messageForMe.getMessage()).hashCode()).equalsIgnoreCase(checkMessage.getMessage())) {
                System.out.println("You have a message from: " + messageForMe.getClientLogin());
                System.out.println("Date: " + messageForMe.getDateCreate());
                System.out.println("Message: " + messageForMe.getMessage());
            }
        }
        if (messageForMe == null) {
            System.out.println("You don't have a message");
        }
        String login = clientM.getLogin();
        System.out.print("Send Message To: ");
        String login_to = scan.nextLine();

        OnlineUsers onlineUsers = readOnlineUsers(USERS_ONLINE_FILE);
        boolean isUserOnline = false;
        for (Cookies cookies : onlineUsers.getUserOnline()) {
            if (cookies.getLogin().equals(login_to)) {
                isUserOnline = true;
                break;
            }
        }
        if (!isUserOnline) {
            System.out.println("User " + login_to + " is offline");
        } else {
            System.out.println("Write a message");
            String message = scan.nextLine();
            String chmessage = String.valueOf((login + login_to + message).hashCode());
            CheckMessage cmsg = new CheckMessage(chmessage, login, login_to, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
            ConnectMessage msg = new ConnectMessage(message, login, login_to, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));

            new MessageDAOImpl().create(msg);
            try {
                new XMLMarshaller().marshall(msg, "src/main/resources/" + login_to + ".xml");//writeMessage
            } catch (UnableToWriteException | JAXBException e) {
                e.printStackTrace();
            }
            try {
                new XMLMarshaller().marshall(cmsg, "src/main/resources/checkMessage/" + login_to + ".xml");//writeMessage
            } catch (UnableToWriteException | JAXBException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Let's check if you have new message! Press any key");
        String answer = scan.nextLine();
        if (answer.equals("N")) {
            System.exit(-1);
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

    private static OnlineUsers readOnlineUsers(String pathTo) {
        try {
            return new XMLUnmarshaller().unmarshallOnlineUsers(pathTo);
        } catch (IOException | JAXBException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException("Something went wrong while unmarshalling!");
        }
    }

    private static ConnectMessage readConnectMessage(String messagePath) {
        try {
            ConnectMessage connectMessage = new XMLUnmarshaller().unmarshallConnectMessage(messagePath);
            File messageFile = new File(messagePath);
            messageFile.delete();
            return connectMessage;
        } catch (IOException | JAXBException ioe) {
            return null;
        }
    }

    private static CheckMessage readCheckMessage(String messagePath) {
        try {
            CheckMessage checkMessage = new XMLUnmarshaller().unmarshallCheckMessage(messagePath);
            File messageFile = new File(messagePath);
            // messageFile.delete();
            return checkMessage;
        } catch (IOException | JAXBException ioe) {
            return null;
        }
    }

    private static void writeMessage(InitMessage b, String pathTo) {
        try {
            new XMLMarshaller().marshall(b, pathTo);
        } catch (UnableToWriteException uwe) {
            uwe.printStackTrace();
            throw new RuntimeException("Bad object type!");
        } catch (JAXBException jaxe) {
            jaxe.printStackTrace();
            throw new RuntimeException("Something went wrong while marshalling!");
        }
    }

}

