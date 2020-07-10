package myChat.service;

import myChat.message.ConnectMessage;
import myChat.dao.MessageDAO;
import myChat.dao.MessageDAOImpl;

public class MessageService {
    MessageDAO messageDAO = new MessageDAOImpl();


    public void createMessage(ConnectMessage msg) {
        messageDAO.create(msg);
    }


    public void updateMessage(ConnectMessage msg) {
        messageDAO.update(msg);
    }
}
