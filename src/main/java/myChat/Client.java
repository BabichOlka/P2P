package myChat;

import myChat.bo.ConnectMessage;
import myChat.bo.InitMessage;
import myChat.dao.ClientDAO;
import myChat.dao.ClientDAOImpl;
import myChat.io.exception.UnableToWriteException;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.interfaces.Packable;
import myChat.marshaller.XMLMarshaller;
import myChat.service.ClientService;
import myChat.util.SerializationUtil;

import javax.xml.bind.JAXBException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Client{

    private static Scanner scan = new Scanner(System.in);
    private static Date dateConnection;
    private static String password;
    private static String name;
    private static ObjectReader objr;
    private static String salt;
    private List<ConnectMessage> messages;

    private static String login;

    public Client(String login, Date dateConnection, String password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = UUID.randomUUID().toString();
        this.dateConnection = new Date();
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");

    }

        public static void main(String[] args) {
            askRegistration();
            replyYesNo();
            System.out.println("Your login: ");
            String login = scan.nextLine();
            System.out.println("Your password: ");
            String password = scan.nextLine();
            checkPassword(login);
            if (init( login, password)) {
                while (true) {
                    connect();
                }
            }
        }


    private static boolean init( String login, String password){
            Packable imsg = new InitMessage( login, password);
            SerializationUtil.writeObject(imsg, SerializationUtil.getREADER_INIT().getPath());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                System.out.println("There is interruption");
            }
            Packable answer = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
            if(answer != null){
                System.out.println(((InitMessage) answer).getLogin() + " is accepted");
                Client client = new Client(login, salt, password);
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
            ConnectMessage msg = new ConnectMessage(str, login);
            try {
                new XMLMarshaller().marshall(msg, objr.getPath());
            } catch (UnableToWriteException | JAXBException e) {
                e.printStackTrace();
            }

        }

ClientDAO clientDAO = new ClientDAOImpl();

    public Client (String login, String salt, String confirmedPassword) {
        String answer1 = askRegistration();
        if (answer1.equals("1")) {
            System.out.println("Please enter your login");
            login = scan.nextLine();
            if (clientDAO.getClientByLogin(login) != null) {
                System.out.println("Please enter your password");
                checkPassword(login);
            }
            else System.out.println("This login does not exist! Please try again");
        }
        else if (answer1.equals("2")) {
            String answer2 = replyYesNo();

            if (answer2.equalsIgnoreCase("Y")) {
                System.out.println("Please create login and press Enter");
                salt = UUID.randomUUID().toString();
                Client client = new Client(login, salt, confirmedPassword);
                clientDAO.create(client);
            }
            else if (answer2.equalsIgnoreCase("N")) {
                System.out.println("See you next time!");
            }
        }
    }

    public static String askRegistration() {
        System.out.println("Please enter 1 if you are registered, 2 if you are unregistered");
        String answer1 = scan.nextLine();
        while (!answer1.equals("1") & !answer1.equals("2")) {
            System.out.println("You entered a wrong symbol. Please enter 1 if you are registered, 2 if you are unregistered");
            answer1 = scan.nextLine();
        }
        return answer1;
    }

    public static String replyYesNo() {
        System.out.println("Do you want to register? Enter Y - yes or N - no");
        String answer2 = scan.nextLine();
        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
            answer2 = scan.nextLine();
        }
        return answer2;
    }

    public static void checkPassword(String login) {
        Client client = clientDAO.getSaltByLogin(login);
        String password = Integer.toHexString((client.getSalt().concat(scan.nextLine())).hashCode());
        Client clientDB = clientDAO.getHashBySalt(client.getSalt());
        String hashDB = clientDB.getHash();

        while (!password.equals(hashDB)) {
            System.out.println(" Wrong password! Please try again");
            password = Integer.toHexString((client.getSalt().concat(scan.nextLine())).hashCode());
        }
        System.out.println("Success entry!");
    }

}
