package myChat.service;

import myChat.dao.CookiesDAO;
import myChat.dao.CookiesDAOImpl;
import myChat.model.Cookies;
import myChat.model.OnlineUsers;

public class CookiesService {

    CookiesDAO cookiesDAO = new CookiesDAOImpl();

    public void createCookies(Cookies cookies) {
        cookiesDAO.create(cookies);
    }

    public void updateCookies(Cookies cookies) {
        cookiesDAO.update(cookies);
    }

    public Cookies getKeyByLogin(String login) {
        return cookiesDAO.getKeyByLogin(login);
    }

    public Cookies getCookies(String login) {
      return cookiesDAO.get(login);
    }

    public void updateCookiesDB() {
        cookiesDAO.updateDB();
    }

    public OnlineUsers getOnlineUsers(){
       return cookiesDAO.getOnlineUsers();
    }


}




