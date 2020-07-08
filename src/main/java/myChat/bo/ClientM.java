package myChat.bo;
import java.util.Date;
import java.util.UUID;
import myChat.io.impl.stream.ObjectReader;

public class ClientM {
    private String login;
    private String salt;
    private String password;
    private Date dateConnection;
    private static ObjectReader objr;

    public ClientM(){}
    public ClientM(String login ) {
        this.login = login;
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");
    }

    public ClientM(String login, Date dateConnection, String password, String salt) {
        this.login = login;
        this.password = password;
        this.salt = UUID.randomUUID().toString();
        this.dateConnection = new Date();
        objr = new ObjectReader(System.getProperty("user.dir") + "/src/main/resources/" + login + ".xml");

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateConnection() {
        return dateConnection;
    }

    public void setDateConnection(Date dateConnection) {
        this.dateConnection = dateConnection;
    }
}
