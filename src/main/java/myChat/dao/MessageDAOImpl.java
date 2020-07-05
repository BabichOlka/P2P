package myChat.dao;

import myChat.bo.ConnectMessage;
import myChat.config.SessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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

    }

//    @Override
//    public ConnectMessage getById(long id) {
//        return null;
//    }
//
//    @Override
//    public List<ConnectMessage> get() {
//        return null;
//    }



//    @Override
//    public void delete(long id) {
//
//    }
}
