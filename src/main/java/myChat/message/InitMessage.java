package myChat.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InitMessage")
@XmlType(propOrder = {"host", "port", "token", "login"})
public class InitMessage {

    private String login;
    private String host;
    private int port;
    private String token;

    public InitMessage() {
    }

    public InitMessage(String host, int port, String token, String login) {
        this.host = host;
        this.port = port;
        this.token = token;
        this.login = login;

    }

    @XmlElement
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String toString() {
        return String.format("%s:%s", super.toString(), this.login);
    }

    @XmlElement
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @XmlElement
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @XmlElement
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
