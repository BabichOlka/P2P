package myChat.service;

import myChat.Client;
import myChat.bo.Cookies;
import myChat.dao.ClientMDAO;
import myChat.dao.ClientMDAOImpl;
import myChat.dao.CookiesDAO;
import myChat.dao.CookiesDAOImpl;

import java.util.List;


public class CookiesService {
    CookiesDAO clientDAO = new CookiesDAOImpl();


    public void create(Cookies cookies) {
        clientDAO.create(cookies);
    }

    public void delete(String login) {
        clientDAO.delete(login);
    }

    public void update(Cookies cookies) {
        clientDAO.update(cookies);
    }
}