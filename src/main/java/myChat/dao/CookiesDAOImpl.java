package myChat.dao;

import myChat.Client;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

public class CookiesDAOImpl {
    private final static String namespace = "cookies_mapper";

    public void create(Client login) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".create", login);
        sqlSession.commit();
        sqlSession.close();
    }
    public void update(Client login) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.update(namespace + ".update", login);
        sqlSession.commit();
        sqlSession.close();
    }
}
