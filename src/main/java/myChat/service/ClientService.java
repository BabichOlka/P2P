package myChat.service;

import myChat.message.ConnectMessage;
import myChat.model.ClientModel;
import myChat.dao.ClientDAO;
import myChat.dao.ClientDAOImpl;

import java.util.List;

public class ClientService {
    ClientDAO clientDAO = new ClientDAOImpl();

    public ClientModel getClientByLogin(String login) {
        return clientDAO.getClientByLogin(login);
    }

    public ClientModel getSaltByLogin(String login) {
        return clientDAO.getSaltByLogin(login);
    }

    public List<ClientModel> getAllClients() {
        return clientDAO.get();
    }

    public void createClient(ClientModel client) {
        clientDAO.create(client);
    }

    public void deleteClientById(long id) {
        clientDAO.delete(id);
    }

    public void updateClient(ClientModel clientModel) {
        clientDAO.update(clientModel);
    }
    public List<ConnectMessage> getAllMessages() {
        return clientDAO.getMessageByLogin();
    }
}