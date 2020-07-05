package myChat.dao;


import myChat.bo.ClientM;

import java.util.List;

public interface ClientDAO {
    void create(ClientM clientM);
    ClientM getClientByLogin(String login);
    List<ClientM> get();
    void update(ClientM clientM);
    void delete(long id);
    ClientM getSaltByLogin(String login);
}
