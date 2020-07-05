package myChat.dao;

import myChat.Client;
import myChat.bo.ClientM;
import myChat.bo.Cookies;

public interface CookiesDAO {
    void create(Cookies cookies);
    void update(Cookies cookies);
    void delete(String login);
    Cookies getKeyByLogin(String login);
    Cookies get(String login);
    void updateDB();
}

