package myChat.model;

import myChat.message.ConnectMessage;
import java.util.Date;
import java.util.List;

public class ClientModel {

    private String login;
    private String salt;
    private String password;
    private Date dateConnection;
    private List<ConnectMessage> messages;

    public ClientModel() {
    }

    public ClientModel(String login, String salt, String password, Date dateConnection) {
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.dateConnection = dateConnection;
    }
    public ClientModel(String login, String salt, String password, Date dateConnection,List<ConnectMessage> messages) {
        this.login = login;
        this.salt = salt;
        this.password = password;
        this.dateConnection = dateConnection;
        this.messages = messages;

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