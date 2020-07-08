package myChat.bo;

import java.util.Date;

public class Cookies {

    private static String login;
    private static Date dateConnection;
    private static String key;

    public Cookies() {
            }
    public Cookies(String login,Date dateConnection,String key) {

        this.login = login;
        this.dateConnection = new Date();
        this.key = key;
    }



    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Cookies.login = login;
    }

    public static Date getDateConnection() {
        return dateConnection;
    }

    public static void setDateConnection(Date dateConnection) {
        Cookies.dateConnection = dateConnection;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        Cookies.key = key;
    }
}
