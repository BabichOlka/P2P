package myChat.dao;

import myChat.message.ConnectMessage;
import myChat.factory.SessionFactory;
import org.apache.ibatis.session.SqlSession;

public class MessageDAOImpl implements MessageDAO {

    private final static String namespace = "message_mapper";
    String resource = "mybatisconfig.xml";

    @Override
    public void create(ConnectMessage msg) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".create", msg);
        sqlSession.commit();
        sqlSession.close();
    }
    @Override
    public void update(ConnectMessage msg) {
        SqlSession sqlSession = SessionFactory.getSession(resource);
        sqlSession.insert(namespace + ".update", msg);
        sqlSession.commit();
        sqlSession.close();

    }
}
