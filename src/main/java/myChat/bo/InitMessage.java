package myChat.bo;

public class InitMessage extends Package {

    private String login;


    public InitMessage(String host, int port, String token, String login) {
        super(host, port, token);
        this.login = login;

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


}
