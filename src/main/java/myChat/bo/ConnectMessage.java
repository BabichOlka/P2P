package myChat.bo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.sql.Date;

@XmlRootElement(name = "Connect_message")
@XmlType(propOrder = {"message", "clientLogin", "login_to", "dateCreate"})
public class ConnectMessage extends Package {

    private String message;
    private String clientLogin;
    private String login_to;
    private Date dateCreate;

    public ConnectMessage(String message, String clientLogin) {
        this.message = message;
        this.clientLogin = clientLogin;
    }
    public ConnectMessage(String message, String clientLogin,String login_to,Date dateCreate) {
        this.message = message;
        this.clientLogin = clientLogin;
        this.login_to = login_to;
        this.dateCreate = null;
    }
    public ConnectMessage(){}
    @XmlElement
    public String getLogin_to() {
        return login_to;
    }

    public void setLogin_to(String login_to) {
        this.login_to = login_to;
    }

    @XmlElement
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @XmlElement
    public String getMessage() {
        return message;
    }

    @XmlElement
    public String getclientLogin() {
        return clientLogin;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setclientLogin(String clientName) { this.clientLogin = clientName; }

    public String toString() {
        return String.format("%s:%s", super.toString(), this.message);
    }
}
