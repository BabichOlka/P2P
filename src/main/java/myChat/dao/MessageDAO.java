package myChat.dao;

import myChat.model.ConnectMessage;

public interface MessageDAO {
    void create(ConnectMessage message);
   // ConnectMessage getById(long id);
    //List<ConnectMessage> get();
    void update(ConnectMessage message);
    //void delete(long id);
}
