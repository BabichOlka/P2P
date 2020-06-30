package myChat.bo;

public class InitMessage extends Package {

    private String login;
    private String password;
    private String salt;

    public InitMessage(String host, int port, String token, String login,String password,String salt) {
        super(host, port, token);
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String toString() {
        return String.format("%s:%s", super.toString(), this.login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
