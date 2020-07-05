package myChat.marshaller;

import myChat.bo.ConnectMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;

public class XMLunMarshaller {
    public ConnectMessage unmarshall(String pathTo) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(ConnectMessage.class);
        return (ConnectMessage) context.createUnmarshaller()
                .unmarshal(new FileReader(pathTo));
    }
}
