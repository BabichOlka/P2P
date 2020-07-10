package myChat.dao;

import myChat.message.ConnectMessage;
import myChat.model.ClientModel;

import java.util.List;

public interface ClientDAO {
    void create(ClientModel clientModel);

    ClientModel getClientByLogin(String login);

    List<ClientModel> get();

    void update(ClientModel clientModel);

    void delete(long id);

    ClientModel getSaltByLogin(String login);
    List<ConnectMessage> getMessageByLogin();
}
