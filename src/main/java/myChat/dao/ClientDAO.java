package myChat.dao;

import myChat.Client;

import java.util.List;

public interface ClientDAO {
    void create(Client client);
    Client getById(long id);
    List<Client> get();
    void update(Client client);
    void delete(long id);
}
