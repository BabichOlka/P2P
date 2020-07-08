package myChat.dao;

import myChat.Client;
import myChat.bo.ClientM;

import java.util.List;

public interface ClientMDAO {
    void create(ClientM client);
    ClientM getClientByLogin(String login);
    List<ClientM> get();
    void update(ClientM client);
    void delete(long id);
    ClientM getSaltByLogin(String login);
}
