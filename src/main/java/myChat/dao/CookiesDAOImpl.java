package myChat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import myChat.model.Cookies;
import myChat.model.OnlineUsers;
import myChat.factory.SessionFactory;

public class CookiesDAOImpl implements CookiesDAO {
    private final static String namespace = "cookies_mapper";
    String resource = "mybatisconfigServer.xml";

    @Override
    public void create(Cookies cookies) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", cookies);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Cookies cookies) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".update", cookies);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.delete(namespace + ".deleteById", login);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Cookies getKeyByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        Cookies key = sqlSession.selectOne(namespace + ".getKeyByLogin", login);
        sqlSession.close();
        return key;
    }

    @Override
    public Cookies get(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        Cookies cookies = sqlSession.selectOne(namespace + ".get", login);
        sqlSession.close();
        return cookies;
    }

    @Override
    public void updateDB() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".updateDB");
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public OnlineUsers getOnlineUsers() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        List<Cookies> cookies = sqlSession.selectList(namespace + ".selectOnlineUsers");
        OnlineUsers onlineUsers = new OnlineUsers();
        onlineUsers.setUserOnline(cookies);
        sqlSession.close();
        return onlineUsers;
    }
}
