package myChat.model;

import java.util.Date;

public class ClientM {

    private String login;
    private String salt;
    private String password;
    private Date dateConnection;


    public ClientM() {
    }

    public ClientM(String login, String salt, String password, Date dateConnection) {
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.dateConnection = dateConnection;
    }

    public String getLogin() {
        return login;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPasword(String hash) {
        this.password = password;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }
}