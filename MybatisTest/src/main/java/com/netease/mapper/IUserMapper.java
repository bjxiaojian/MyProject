package com.netease.mapper;


import com.netease.model.Article;
import com.netease.model.User;
import com.netease.util.PageInfo;

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