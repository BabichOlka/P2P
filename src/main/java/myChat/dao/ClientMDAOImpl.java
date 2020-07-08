package myChat.dao;

import myChat.Client;
import myChat.bo.ClientM;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientMDAOImpl implements ClientMDAO {
    String resource = "mybatisconfig.xml";

    private final static String namespace = "client_mapper";
    
    public void create(ClientM client) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", client);
        sqlSession.commit();
        sqlSession.close();
    }

    public ClientM getClientByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientM cat = sqlSession.selectOne(namespace + ".getClientByLogin", login);
        sqlSession.close();
        return cat;
    }
    public ClientM getSaltByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientM salt = sqlSession.selectOne(namespace + ".getSaltByLogin", login);
        sqlSession.close();
        return salt;
    }
    public List<ClientM> get() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        List<ClientM> cats = sqlSession.selectList(namespace + ".get");
        sqlSession.close();
        return cats;
    }

    public void update(ClientM client) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".update", client);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(long id) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
