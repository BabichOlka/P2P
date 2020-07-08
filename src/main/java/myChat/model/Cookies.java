package myChat.model;

import java.util.Date;

public class Cookies {

    private String login;
    private Date dateConnection;
    private String key;

    public Cookies() {
    }

    public Cookies(String login, Date dateConnection, String key) {

        this.login = login;
        this.dateConnection = dateConnection;
        this.key = key;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
