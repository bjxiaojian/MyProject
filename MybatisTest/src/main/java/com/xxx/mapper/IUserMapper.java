package com.xxx.mapper;


import com.xxx.model.Article;
import com.xxx.model.User;
import com.xxx.util.PageInfo;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper {
    public User selectUserByID(int id);

    public List<User> selectUsers(String userName);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(int id);

    public List<Article> getUserArticles(int id);

    public List<Article> selectArticleListPage(@Param("page") PageInfo page, @Param("userid") int userid);
}