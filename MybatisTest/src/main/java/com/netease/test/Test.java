package com.netease.test;

import com.netease.mapper.IUserMapper;
import com.netease.model.Article;
import com.netease.model.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

public class Test {
  private static SqlSessionFactory sqlSessionFactory;
  private static Reader reader;

  static {
    try {
      reader = Resources.getResourceAsReader("Configuration.xml");
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static SqlSessionFactory getSession() {
    return sqlSessionFactory;
  }

  public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
//        User user = session.selectOne("com.netease.model.UserMapper.selectUserByID", 1);

            IUserMapper mapper = session.getMapper(IUserMapper.class);
            User user = mapper.selectUserByID(1);
            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        } finally {
            session.close();
        }

    Test test = new Test();
    test.addUser();
    test.getUserList("summer");
  }

  public void getUserList(String userName) {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      IUserMapper mapper = session.getMapper(IUserMapper.class);
      List<User> users = mapper.selectUsers(userName);
      for (User user : users) {
        System.out.println(user.getId() + ":" + user.getUserName() + ":" + user.getUserAddress());
      }

    } finally {
      session.close();
    }
  }

  /**
   * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
   */
  public void addUser() {
    User user = new User();
    user.setUserAddress("人民广场");
    user.setUserName("飞鸟");
    user.setUserAge("80");
    SqlSession session = sqlSessionFactory.openSession();
    try {
      IUserMapper mapper = session.getMapper(IUserMapper.class);
      mapper.addUser(user);
      session.commit();
      System.out.println("当前增加的用户 id为:" + user.getId());
    } finally {
      session.close();
    }
  }

  public void getUserArticles(int userid){
    SqlSession session = sqlSessionFactory.openSession();
    try {
      IUserMapper mapper = session.getMapper(IUserMapper.class);
      List<Article> articles = mapper.getUserArticles(userid);
      for(Article article:articles){
        System.out.println(article.getTitle()+":"+article.getContent()+
            ":作者是:"+article.getUser().getUserName()+":地址:"+
            article.getUser().getUserAddress());
      }
    } finally {
      session.close();
    }
  }
}