//package myChat;
//
//import myChat.bo.ConnectMessage;
//import myChat.bo.InitMessage;
//import myChat.constant.C10Constant;
//import myChat.dao.ClientDAOImpl;
//import myChat.io.exception.UnableToWriteException;
//import myChat.io.impl.stream.ObjectReader;
//import myChat.io.interfaces.Packable;
//import myChat.marshaller.XMLMarshaller;
//import myChat.util.PropertyUtil;
//import myChat.util.SerializationUtil;
//
//import javax.xml.bind.JAXBException;
//import java.util.Date;
//import java.util.List;
//import java.util.Scanner;
//import java.util.UUID;
//
//public class ClientActivity {
//
//    private static final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
//    private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
//    private static final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
//    private static Scanner scan = new Scanner(System.in);
//    private static Date dateConnection;
//    private static String password;
//    private static String name;
//    private static ObjectReader objr;
//    private static String salt;
//    private List<ConnectMessage> messages;
//    private static String login;
//
//
//
//    public static void main(String[] args) {
//        askRegistration();
//
//        //       replyYesNo();
////        System.out.println("Your login: ");
////        String login = scan.nextLine();
////        System.out.println("Your password: ");
////        String password = scan.nextLine();
//        //  checkPassword(login);
//        if (init(HOST, PORT, TOKEN, login, password, null)) {
//
//            while (true) {
//                connect();
//            }
//        }
//    }
//
//
//    private static boolean init(String host, int port, String token, String login, String password,String salt){
//
//        Packable imsg = new InitMessage(host, port,  token, login, password,salt);
//        SerializationUtil.writeObject(imsg, SerializationUtil.getREADER_INIT().getPath());
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            System.out.println("There is interruption");
//        }
//        Packable answer = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
//        if(answer != null){
//            System.out.println(((InitMessage) answer).getLogin() + " is accepted");
////            Client client = new Client(login, new Date(),password, salt);
////            new ClientService().createClient(client);
//            return true;
//        }
//        else {
//            System.out.println("No connection");
//            return false;
//        }
//    }
//
//
//
//    private static void connect() {
//        Client client = new Client(login);
//        System.out.print("You: ");
//        String str = scan.nextLine();
//        String login_to = "vasa";
//        ConnectMessage msg = new ConnectMessage(str, login,login_to,null);
//        //new MessageDAOImpl().create(msg);
//        //msg.getMessage();
//        try {
//            new XMLMarshaller().marshall(msg, objr.getPath());//writeMessage
//        } catch (UnableToWriteException | JAXBException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void askRegistration() {
//        System.out.println("Please enter 1 if you are registered, 2 if you are unregistered");
//        String answer1 = scan.nextLine();
//        while (!answer1.equals("1") & !answer1.equals("2")) {
//            System.out.println("You entered a wrong symbol. Please enter 1 if you are registered, 2 if you are unregistered");
//            answer1 = scan.nextLine();
//        }
//        if (answer1.equals("1")) {
//            System.out.println("Please enter your login");
//            login = scan.nextLine();
//            if (new ClientDAOImpl().getClientByLogin(login) != null) {
//                System.out.println("Please enter your password");
//                String password = scan.nextLine();
//                Client client = new Client(login, dateConnection, password, salt);
//
//                salt = UUID.randomUUID().toString();
//                new ClientDAOImpl().update(client);
//                //  checkPassword(login);
//            } else System.out.println("This login does not exist! Please try again");{
//
//            }
//
//        } else if (answer1.equals("2")) {
//            String answer2 = replyYesNo();
//
//            if (answer2.equalsIgnoreCase("Y")) {
//                //System.out.println("Please create login and press Enter");
//                System.out.println("Your login: ");
//                String login = scan.nextLine();
//                System.out.println("Your password: ");
//                String password = scan.nextLine();
//                salt = UUID.randomUUID().toString();
//                Client client = new Client(login, dateConnection, password, salt);
//                new ClientDAOImpl().create(client);
//            } else if (answer2.equalsIgnoreCase("N")) {
//                System.out.println("See you next time!");
//            }
//        }
//
//
////    public static void checkPassword(String login) {
////        Client client = clientDAO.getSaltByLogin(login);
////        String password = Integer.toHexString((client.getSalt().concat(scan.nextLine())).hashCode());
////        Client clientDB = clientDAO.getHashBySalt(client.getSalt());
////        String hashDB = clientDB.getHash();
////
////        while (!password.equals(hashDB)) {
////            System.out.println(" Wrong password! Please try again");
////            password = Integer.toHexString((client.getSalt().concat(scan.nextLine())).hashCode());
////        }
////        System.out.println("Success entry!");
////    }
//
//    }
//
//
//
//    public static String replyYesNo () {
//        System.out.println("Do you want to register? Enter Y - yes or N - no");
//        String answer2 = scan.nextLine();
//        while (!answer2.equalsIgnoreCase("Y") & !answer2.equalsIgnoreCase("N")) {
//            System.out.println("You entered a wrong symbol. Please choose Y for yes, N for no to continue");
//            answer2 = scan.nextLine();
//        }
//        return answer2;
//    }
//}
//
//
