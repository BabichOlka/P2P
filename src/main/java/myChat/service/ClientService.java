package myChat.service;

import myChat.Client;
import myChat.dao.ClientDAO;
import myChat.dao.ClientDAOImpl;

import java.util.List;

public class ClientService {
    ClientDAO clientDAO = new ClientDAOImpl();

    public Client getClientByLogin(String login) {
        return clientDAO.getClientByLogin(login);
    }

    public List<Client> getAllClients() {
        return clientDAO.get();
    }

    public void createClient(Client client) {
        clientDAO.create(client);
    }

    public void deleteClientById(long id) {
        clientDAO.delete(id);
    }

    public void updateClient(Client category) {
        clientDAO.update(category);
    }
}
