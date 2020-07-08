package myChat;

import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLUnmarshaller;
import myChat.model.CheckMessage;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Connection extends Thread{

    private String objr;
    private Logger log = Logger.getLogger("connect");
    private String conName;

    public Connection(String name) {
        super(name);
        this.conName = name;
        this.objr = System.getProperty("user.dir") + "/src/main/resources/checkMessage/" + name +".xml";
    }
    @Override
    public void run() {
        while(true){

            CheckMessage msg = readCheckMessage(objr);

            if (msg!=null && !msg.getMessage().equals("") ) {
                try {
                    new XMLMarshaller().marshall(msg, System.getProperty("user.dir") +"/src/main/resources/checkMessage/"
                            + msg.getClientLogin() + msg.getLogin_to() +".xml");//write
                } catch (UnableToWriteException | JAXBException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
}
