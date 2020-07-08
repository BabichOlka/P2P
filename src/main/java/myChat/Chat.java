package myChat;
import myChat.bo.ConnectMessage;
import myChat.io.exception.UnableToWriteException;
import myChat.marshaller.XMLMarshaller;
import myChat.marshaller.XMLunMarshaller;
import myChat.util.SerializationUtil;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Chat {
    public static void main(String[] args){
        ConnectMessage no = new ConnectMessage("", "");
        Logger log = Logger.getLogger("APP1");
        ConnectMessage msg = null;
        while (true) {
            try {
                msg = new XMLunMarshaller().unmarshall(SerializationUtil.getCHAT().getPath());
            } catch (JAXBException e) {}
              catch (IOException e) {
                e.printStackTrace();
            }
            if (!msg.getMessage().equals("")) {
                log.info("client : " + msg.getclientLogin()+":  "+ msg.getMessage() );
                msg.getclientLogin();
                msg.getMessage();
                //no.getMessage();
                //no.getClientName();
                try {
                    new XMLMarshaller().marshall(no, SerializationUtil.getCHAT().getPath());//writeMessage
                } catch (UnableToWriteException e) {
                    e.printStackTrace();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
