package myChat.factory;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;


public class SessionFactory {

    public static final Logger LOGGER = Logger.getLogger(SessionFactory.class);

    private static SqlSessionFactory sqlSessionFactory = null;



    public static SqlSession getSession(String resource){
        try {

            InputStream is  = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return sqlSessionFactory.openSession();
    }
}
