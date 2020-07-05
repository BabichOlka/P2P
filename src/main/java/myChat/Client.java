package myChat;

import myChat.bo.ClientM;
import myChat.bo.ClientReg;
import myChat.bo.ConnectMessage;
import myChat.bo.InitMessage;
import myChat.constant.C10Constant;
import myChat.io.exception.UnableToWriteException;
import myChat.io.impl.stream.ObjectReader;
import myChat.io.interfaces.Packable;
import myChat.marshaller.XMLMarshaller;
import myChat.util.PropertyUtil;
import myChat.util.SerializationUtil;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Scanner;
public class Client {

    private static final String HOST = PropertyUtil.getValueByKey(C10Constant.HOSTNAME);
    private static final int PORT = Integer.valueOf(PropertyUtil.getValueByKey(C10Constant.PORT));
    private static final String TOKEN = PropertyUtil.getValueByKey(C10Constant.TOKEN);
    private static Scanner scan = new Scanner(System.in);
    private static ObjectReader objr;
    private List<ConnectMessage> messages;
    private static String login;


    public Client(String login ) {
        this.login = login;
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");
    }


    public static void main(String[] args) {

        System.out.println("Please enter 1 if you are registered, 2 if you are unregistered");
        String answer1 = scan.nextLine();
        //new ClientReg().askRegistration(answer1);

        while (!answer1.equalsIgnoreCase("1") & !answer1.equalsIgnoreCase("2")) {
            System.out.println("You entered a wrong symbol. Please choose 1 for yes, 2 for no to continue");
            answer1 = scan.nextLine();
        }

        ClientM clientM = new ClientReg().askRegistration(answer1);

        if ((clientM == null)) {
            closeApp();
        } else {
            Client client = new Client(clientM.getLogin());
            if (init(HOST, PORT, TOKEN, login)) {
                while (true) {
                    connect(clientM);
                }
            }
        }
    }


    private static boolean init(String host, int port, String token, String login){

        Packable imsg = new InitMessage(host, port,  token, login);
        SerializationUtil.writeObject(imsg, SerializationUtil.getREADER_INIT().getPath());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.out.println("There is interruption");
        }
        Packable answer = SerializationUtil.readObject(SerializationUtil.getREADER_INIT());
        if(answer != null){
            System.out.println(((InitMessage) answer).getLogin() + " is accepted");

//            Client client = new Client(login, new Date(),password, salt);
//            new ClientService().createClient(client);

            return true;
        }
        else {
            System.out.println("No connection");
            return false;
        }
    }

    private static void connect(ClientM clientM) {

        String login = clientM.getLogin();
        Client client = new Client(login);

        System.out.print("You: ");
        String str = scan.nextLine();
        String login_to = "vasa";
        ConnectMessage msg = new ConnectMessage(str, login,  login_to,null);
        //new MessageDAOImpl().create(msg);
        //msg.getMessage();
        try {
            new XMLMarshaller().marshall(msg, objr.getPath());//writeMessage
        } catch (UnableToWriteException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public static void closeApp (){
        while (true) {
            System.out.println("The application will be closed ! ");
            break;
        }
    }
}

