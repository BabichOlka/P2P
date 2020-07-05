package myChat.dao;

import myChat.bo.ClientM;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    String resource = "mybatisconfig.xml";

    private final static String namespace = "client_mapper";

    public void create(ClientM clientM) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", clientM);
        sqlSession.commit();
        sqlSession.close();
    }

    public ClientM getClientByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientM clientM = sqlSession.selectOne(namespace + ".getClientByLogin", login);
        sqlSession.close();
        return clientM;
    }

    public List<ClientM> get() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        List<ClientM> clientsM = sqlSession.selectList(namespace + ".get");
        sqlSession.close();
        return clientsM;
    }

    public void update(ClientM clientM) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".update", clientM);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(long id) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    public ClientM getSaltByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientM salt = sqlSession.selectOne(namespace + ".getSaltByLogin", login);
        sqlSession.close();
        return salt;
    }

}