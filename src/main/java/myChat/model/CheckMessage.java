package myChat.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "Check_message")
public class CheckMessage {
    private String message;
    private String clientLogin;
    private String login_to;
    private Date dateCreate;

    public CheckMessage(String message, String clientLogin) {
        this.message = message;
        this.clientLogin = clientLogin;
    }

    public CheckMessage(String message, String clientLogin, String login_to, Date dateCreate) {
        this.message = message;
        this.clientLogin = clientLogin;
        this.login_to = login_to;
        this.dateCreate = dateCreate;
    }

    public CheckMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getLogin_to() {
        return login_to;
    }

    public void setLogin_to(String login_to) {
        this.login_to = login_to;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
