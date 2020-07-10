package myChat.dao;

import myChat.message.ConnectMessage;
import myChat.model.ClientModel;
import myChat.factory.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    String resource = "mybatisconfig.xml";

    private final static String namespace = "client_mapper";

    @Override
    public void create(ClientModel clientModel) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", clientModel);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public ClientModel getClientByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientModel clientModel = sqlSession.selectOne(namespace + ".getClientByLogin", login);
        sqlSession.close();
        return clientModel;
    }

    @Override
    public List<ClientModel> get() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        List<ClientModel> clientsM = sqlSession.selectList(namespace + ".get");
        sqlSession.close();
        return clientsM;
    }

    @Override
    public void update(ClientModel clientModel) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.update(namespace + ".update", clientModel);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(long id) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.delete(namespace + ".deleteById", id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public ClientModel getSaltByLogin(String login) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        ClientModel salt = sqlSession.selectOne(namespace + ".getSaltByLogin", login);
        sqlSession.close();
        return salt;
    }
    @Override
    public List<ConnectMessage> getMessageByLogin() {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        List<ConnectMessage> ConnectMessages = sqlSession.selectList(namespace + ".getMessageByLogin");
        sqlSession.close();
        return ConnectMessages;
    }

}