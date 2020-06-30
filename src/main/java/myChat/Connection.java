package myChat;

import myChat.bo.ConnectMessage;
import myChat.io.exception.UnableToWriteException;
import myChat.io.impl.stream.ObjectReader;
import myChat.marshaller.*;
import myChat.service.MessageService;
import myChat.util.SerializationUtil;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Connection extends Thread{
    ConnectMessage no = new ConnectMessage("", "");
    private ObjectReader objr;
    private Logger log = Logger.getLogger("connect");
    private String conName;

    public Connection(String name) {
        super(name);
        this.conName = name;
        this.objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + name +".xml");
    }

    @Override
    public void run() {
        while(true){
            ConnectMessage msg = null;
            try {
                msg = new XMLunMarshaller().unmarshall(objr.getPath());//readMessage
            } catch (JAXBException ignored) {}//  do nothing
              catch (IOException e) { e.printStackTrace(); }

            if (msg!=null && !msg.getMessage().equals("") ) {
                new MessageService().createMessage(msg);
                try {
                    new XMLMarshaller().marshall(no, objr.getPath());
                } catch (UnableToWriteException | JAXBException e) { e.printStackTrace(); }

                msg.setMessage(Server.controlMessage(msg.getMessage()));    //filtering message
                log.info(msg.getMessage());

                try {
                    new XMLMarshaller().marshall(msg, SerializationUtil.getCHAT().getPath());
                } catch (UnableToWriteException | JAXBException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
