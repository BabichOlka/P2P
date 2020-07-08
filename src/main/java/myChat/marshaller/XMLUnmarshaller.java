package myChat.marshaller;

import myChat.model.CheckMessage;
import myChat.model.ConnectMessage;
import myChat.model.InitMessage;
import myChat.model.OnlineUsers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class XMLUnmarshaller {
    public InitMessage unmarshallMessage(String pathTo) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(InitMessage.class);
        return (InitMessage) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }

    public OnlineUsers unmarshallOnlineUsers(String pathTo) throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(OnlineUsers.class);
        return (OnlineUsers) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }

    public ConnectMessage unmarshallConnectMessage(String pathTo) throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(ConnectMessage.class);
        return (ConnectMessage) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }
    public CheckMessage unmarshallCheckMessage(String pathTo) throws JAXBException, FileNotFoundException
    {
        JAXBContext context = JAXBContext.newInstance(CheckMessage.class);
        return (CheckMessage) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }
}
