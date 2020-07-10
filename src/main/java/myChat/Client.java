package myChat;

import myChat.authorization.ClientReg;
import myChat.constant.Constant;
import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLUnmarshaller;
import myChat.message.CheckMessage;
import myChat.message.ConnectMessage;
import myChat.message.InitMessage;
import myChat.model.ClientModel;
import myChat.model.Cookies;
import myChat.model.OnlineUsers;
import myChat.service.MessageService;
import myChat.util.PropertyUtil;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Scanner;

public class Client {

    private static final String HOST = PropertyUtil.getValueByKey(Constant.HOSTNAME);
    private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(Constant.PORT));
    private static final String TOKEN = PropertyUtil.getValueByKey(Constant.TOKEN);
    private static Scanner scan = new Scanner(System.in);
    private static String pathForConnect;
    private static String login;
    private static String pathTo = "src/main/resources/initMessage.xml";
    private static String USERS_ONLINE_FILE = "src/main/resources/users_online.xml";

    public Client(String login) {
        this.login = login;
        pathForConnect = System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml";
    }

    public static void main(String[] args) {

        String answer = askRegistration();

        ClientModel clientModel = new ClientReg().registration(answer);

        if ((clientModel == null)) {
            System.exit(0);
        } else {
            Client client = new Client(clientModel.getLogin());
            if (init(HOST, PORT, TOKEN, login)) {
                while (true) {
                    connect(clientModel);
                }
            }
        }
    }

    public static String askRegistration() {
        System.out.println("Please enter 1 if you are registered, 2 if you are unregistered");
        String answer = scan.nextLine();

        while (!answer.equalsIgnoreCase("1") & !answer.equalsIgnoreCase("2")) {
            System.out.println("You entered a wrong symbol. Please choose 1 for yes, 2 for no to continue");
            answer = scan.nextLine();
        }
        return answer;
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

    private static void connect(ClientModel clientModel) {

        ConnectMessage messageForMe = readConnectMessage(pathForConnect);
        checkMessageForMe(messageForMe);

        System.out.print("Send Message To: ");
        String login_to = scan.nextLine();

        if (!checkReceiverIsOnline(login_to)) {
            System.out.println("User " + login_to + " is offline");
        } else {

            System.out.println("Write a message");
            String message = scan.nextLine();
            writeMessageForMe(message, login_to);

        }
        System.out.println("Do  you want to read new message? Enter any key if yes or N if no");
        String answer = scan.nextLine();
        if (answer.equalsIgnoreCase("n")) {
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
            return checkMessage;
        } catch (IOException | JAXBException ioe) {
            return null;
        }
    }

    private static void writeMessage(Object b, String pathTo) {
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

    private static void checkMessageForMe(ConnectMessage messageForMe) {
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

    }
    public static boolean checkReceiverIsOnline(String login_to) {
        OnlineUsers onlineUsers = readOnlineUsers(USERS_ONLINE_FILE);
        boolean isUserOnline = false;
        for (Cookies cookies : onlineUsers.getUserOnline()) {
            if (cookies.getLogin().equals(login_to)) {
                isUserOnline = true;
                break;
            }
        }
        return isUserOnline;
    }
    private static void writeMessageForMe(String message, String login_to) {
        String cheskMessage = String.valueOf((login + login_to + message).hashCode());
        CheckMessage cmsg = new CheckMessage(cheskMessage, login, login_to, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
        ConnectMessage msg = new ConnectMessage(message, login, login_to, Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));

        new MessageService().createMessage(msg);
        writeMessage(msg, "src/main/resources/" + login_to + ".xml");
        writeMessage(cmsg, "src/main/resources/checkMessage/" + login_to + ".xml");

    }
}

