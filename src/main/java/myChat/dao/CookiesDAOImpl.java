package myChat.dao;

import myChat.Client;
import myChat.bo.ClientM;
import myChat.bo.Cookies;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

public class CookiesDAOImpl implements CookiesDAO {
    private final static String namespace = "cookies_mapper";
    String resource = "mybatisconfigServer.xml";

    public void create(Cookies cookies) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", cookies);
        sqlSession.commit();
        sqlSession.close();
    }
    public void update(Cookies cookies) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".update", cookies);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.delete(namespace + ".deleteById", login);
        sqlSession.commit();
        sqlSession.close();
    }
    public Cookies getKeyByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        Cookies cookies = sqlSession.selectOne(namespace + ".getKeyByLogin", login);
        sqlSession.close();
        return cookies;
    }
    public Cookies get(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        Cookies cookies = sqlSession.selectOne(namespace + ".get", login);
        sqlSession.close();
        return cookies;
    }
    public void updateDB() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".updateDB");
        sqlSession.commit();
        sqlSession.close();
    }
}
