package myChat.dao;

import myChat.model.Cookies;
import myChat.model.OnlineUsers;

public interface CookiesDAO {
    void create(Cookies cookies);
    void update(Cookies cookies);
    void delete(String login);
    Cookies getKeyByLogin(String login);
    Cookies get(String login);
    void updateDB();
    OnlineUsers getOnlineUsers();
}

