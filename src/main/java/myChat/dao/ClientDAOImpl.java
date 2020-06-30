package myChat.dao;

import myChat.Client;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    private final static String namespace = "client_mapper";
    
    public void create(Client client) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.insert(namespace + ".create", client);
        sqlSession.commit();
        sqlSession.close();
    }

    public Client getClientByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession();
        Client cat = sqlSession.selectOne(namespace + ".getClientByLogin", login);
        sqlSession.close();
        return cat;
    }

    public List<Client> get() {
        SqlSession sqlSession = SessionFactory.getSession();
        List<Client> cats = sqlSession.selectList(namespace + ".get");
        sqlSession.close();
        return cats;
    }

    public void update(Client category) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.update(namespace + ".update", category);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(long id) {
        SqlSession sqlSession = SessionFactory.getSession();
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }
}
