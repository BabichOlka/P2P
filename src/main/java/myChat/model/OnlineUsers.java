package myChat.model;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users_online")
public class OnlineUsers {
    private List<Cookies> userOnline;

    public List<Cookies> getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(List<Cookies> userOnline) {
        this.userOnline = userOnline;
    }
}
